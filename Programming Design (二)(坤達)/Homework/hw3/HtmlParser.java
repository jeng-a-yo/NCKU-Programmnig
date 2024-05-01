import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Collections;

import java.util.Objects;


// import javax.lang.model.util.Elements;


class HtmlParser {
    public static void main(String[] args) {

        String dataFileName = "data.csv";

        // System.out.println(Calculator.PerfectRound(28.05));

        try{

            int mode = Integer.parseInt(args[0]);
            int task = Integer.parseInt(args[1]);


            if (mode == 0){

                ArrayList<Double> stocksPrices = GetStocksPrices();

                // FileOperator.NewDataFile(dataFileName);
                FileOperator.WriteCSV(dataFileName, stocksPrices);

            }else if (mode == 1){

                if (task == 0){
                    FileOperator.WriteCSV_0();
                }else{

                    String stock = args[2];
                    int start = Integer.parseInt(args[3]), end = Integer.parseInt(args[4]);

                    ArrayList<ArrayList<Double>> datas = FileOperator.ReadCSV(dataFileName);

                    switch (task) {
                        case 1:
                            FileOperator.WriteCSV_1(stock, start, end, datas);
                            break;
                        case 2:
                            FileOperator.WriteCSV_2(stock, start, end, datas);
                            break;
                        case 3:
                            FileOperator.WriteCSV_3(start, end, datas);
                            break;
                        case 4:
                            FileOperator.WriteCSV_4(stock, start, end, datas);
                            break;
                        default:
                            System.out.println("Invalid task number");
                    }
                }
                
            }else{
                System.out.println("[Info] Error Input");
            }

        } catch (java.lang.ArrayIndexOutOfBoundsException e){
            System.out.println("Error: Index out of bounds. Please provide a valid index.");
            e.printStackTrace();        
        }

    }

    public static ArrayList<Double> GetStocksPrices(){
        try {
            Document doc = Jsoup.connect("https://pd2-hw3.netdb.csie.ncku.edu.tw").get();
            // System.out.println(doc.title());

            double day = Double.parseDouble(doc.title().substring(3));

            // System.out.println(day);

            ArrayList<Double> stocksPrices = new ArrayList<Double>();
            stocksPrices.add(day);

            Element table = doc.select("table").first();
            Elements data = table.select("tr");

            Elements stocks = data.select("th");
            Elements prices = data.select("td");


            for (int i = 0; i < stocks.size(); i++) {

                Double price = Double.parseDouble(prices.get(i).text());
                stocksPrices.add(price);
            }

            return stocksPrices;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<String> GetStocksName(){

        try {
            Document doc = Jsoup.connect("https://pd2-hw3.netdb.csie.ncku.edu.tw").get();

            ArrayList<String> stocksName = new ArrayList<String>();

            Element table = doc.select("table").first();
            Elements data = table.select("tr");
            Elements stocks = data.select("th");


            for (int i = 0; i < stocks.size(); i++) {
                String stock = stocks.get(i).text();
                stocksName.add(stock);
            }

            return stocksName;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

class FileOperator{

    public static ArrayList<Double> ConvertListToArrayList(String[] list){
        ArrayList<Double> arrayList = new ArrayList<Double>();
        for (int i = 1; i < list.length; i++){
            arrayList.add(Double.parseDouble(list[i]));
        }
        return arrayList;
    }

    public static void WriteCSV(String dataFileName, ArrayList<Double> content){

        try {

            File file = new File(dataFileName);
            BufferedWriter writer = new BufferedWriter(new FileWriter(dataFileName, true));
            double tempDay = content.get(0);
            int day = (int) tempDay;
            writer.write(day + ",");

            for (int i = 1; i < content.size(); i++){
                writer.write(content.get(i) + ",");
            }

            writer.newLine();
            writer.close();

        } catch (IOException e) {
            System.err.println("Error appending to the file: " + e.getMessage());
        }
    }

    

    public static ArrayList<ArrayList<Double>> ReadCSV(String dataFileName_){

        String dataFileName = "fulldata.csv";
        ArrayList<ArrayList<Double>> datas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(dataFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                
                ArrayList<Double> data = ConvertListToArrayList(line.split(","));
                datas.add(data);
                
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file.");
            e.printStackTrace();
        }

        return datas;

    }

    public static void WriteCSV_0(){

        String outputFileName = "output.csv", dataFileName = "data.csv";

        ArrayList<ArrayList<Double>> datas = ReadCSV(dataFileName);
        ArrayList<String> stocksName = HtmlParser.GetStocksName();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName, true))){

            StringBuilder stockNameDynamicString = new StringBuilder();
            StringBuilder stockPriceDynamicString = new StringBuilder();

            for (String stockName : stocksName){
                stockNameDynamicString.append(stockName + ",");
            }

            stockNameDynamicString.delete(stockNameDynamicString.length() - 1, stockNameDynamicString.length());
            String stockNameResult = stockNameDynamicString.toString();
            writer.write(stockNameResult + "\n");

            
            for (ArrayList<Double> data : datas){
                for (int i = 0; i < data.size(); i++){
                    stockPriceDynamicString.append(data.get(i) + ",");
                }

                stockPriceDynamicString.delete(stockPriceDynamicString.length() - 1, stockPriceDynamicString.length());
                String stockPriceResult = stockPriceDynamicString.toString();
                writer.write(stockPriceResult + "\n");
            }

            writer.close();

        }catch (IOException e) {
            System.out.println("An error occurred while writing the CSV file.");
            e.printStackTrace();
        }
    }


    public static void WriteCSV_1(String stock, int start, int end, ArrayList<ArrayList<Double>> datas){

        String outputFileName = "output.csv";

        ArrayList<String> stocksName = HtmlParser.GetStocksName();

        int stockIndex = stocksName.indexOf(stock);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName, true))){

            writer.write(stock + ",");
            writer.write(start + ",");
            writer.write(end + "\n");

            StringBuilder dynamicString = new StringBuilder();
            
            for (int i = start-1; i < end-4; i++){
                double[] values = new double[5];
                for (int j = 0; j < 5; j++){
                    values[j] = datas.get(i+j).get(stockIndex);
                }
                
                double average = Calculator.Round(Calculator.GetAverage(values));
                String averageString = String.valueOf(average);
                dynamicString.append(averageString + ",");
            }

            dynamicString.delete(dynamicString.length() - 1, dynamicString.length());
            String result = dynamicString.toString();
            writer.write(result + "\n");
            writer.close();

        }catch (IOException e) {
            System.err.println("An error occurred while writing the CSV file.");
            e.printStackTrace();
        }

    }
    public static void WriteCSV_2(String stock, int start, int end, ArrayList<ArrayList<Double>> datas){

        String outputFileName = "output.csv";

        ArrayList<String> stocksName = HtmlParser.GetStocksName();

        int stockIndex = stocksName.indexOf(stock);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName, true))){

            writer.write(stock + ",");
            writer.write(start + ",");
            writer.write(end + "\n");

            double[] values = new double[end - start + 1];

            for (int i = start-1; i < end; i++){
                values[i - start + 1] = datas.get(i).get(stockIndex);
            }

            double standardDeviation = Calculator.Round(Calculator.GetStandardDeviation(values));
            String standardDeviationString = String.valueOf(standardDeviation);
            writer.write(standardDeviationString + "\n");

            writer.close();

        }catch (IOException e) {
            System.err.println("An error occurred while writing the CSV file.");
            e.printStackTrace();
        }

    }
    public static void WriteCSV_3(int start, int end, ArrayList<ArrayList<Double>> datas){

        String outputFileName = "output.csv";

        ArrayList<String> stocksName = HtmlParser.GetStocksName();
        HashMap<String, Double> standardDeviationMap = new HashMap<String, Double>();

        for (int i = 0; i < stocksName.size(); i++) {
            double[] values = new double[end - start + 1];
            for (int j = start - 1; j < end; j++) {
                values[j - start + 1] = datas.get(j).get(i);
            }

            double standardDeviation = Calculator.Round(Calculator.GetStandardDeviation(values));
            standardDeviationMap.put(stocksName.get(i), standardDeviation);
        }

        ArrayList<String> topThree = Calculator.GetTopThree(standardDeviationMap);


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName, true))){

            writer.write(topThree.get(0) + ",");
            writer.write(topThree.get(1) + ",");
            writer.write(topThree.get(2) + ",");
            writer.write(start + ",");
            writer.write(end + "\n");

            writer.write(Calculator.Round(standardDeviationMap.get(topThree.get(0))) + ",");
            writer.write(Calculator.Round(standardDeviationMap.get(topThree.get(1))) + ",");
            writer.write(Calculator.Round(standardDeviationMap.get(topThree.get(2))) + "\n");

            writer.close();

        }catch (IOException e) {
            System.err.println("An error occurred while writing the CSV file.");
            e.printStackTrace();
        }
    }

    public static void WriteCSV_4(String stock, int start, int end, ArrayList<ArrayList<Double>> datas){

        String outputFileName = "output.csv";

        ArrayList<String> stocksName = HtmlParser.GetStocksName();

        int stockIndex = stocksName.indexOf(stock);

        double[] values = new double[end - start + 1];
        double[] days = new double[end - start + 1];

        for (int i = start - 1; i < end; i++){
            values[i - start + 1] = datas.get(i).get(stockIndex);
            days[i - start + 1] = i + 1;
        }

        double linearRegression[] = Calculator.GetLinearRegression(days, values);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName, true))){

            writer.write(stock + ",");
            writer.write(start + ",");
            writer.write(end + "\n");

            writer.write(String.valueOf(linearRegression[0]) + ",");
            writer.write(String.valueOf(linearRegression[1]) + '\n');

            writer.close();

        }catch (IOException e) {
            System.err.println("An error occurred while writing the CSV file.");
            e.printStackTrace();
        }
    }
}


class Calculator{

    public static double Abs(double number){
        return number < 0 ? -number : number;
    }

    public static double RoundPositiveNumber(double value){
        double multipliedValue = value * 100;
        int intValue = (int) multipliedValue;
        double fractionalPart = multipliedValue - intValue;
        double roundedFraction = (fractionalPart >= 0.5) ? 1 : 0;
        double roundedValue = intValue + roundedFraction;
        return roundedValue / 100;
    }
    

    public static double Round(double value) {

        if (value < 0) {
            return -RoundPositiveNumber(-value);
        }else{
            return RoundPositiveNumber(value);
        }
        
    }


    // public static String PerfectRound(double value){

    //     if ((value * 100) -  ((int) value * 100) == 0.0){
    //         return String.valueOf((int) value);
    //     }else{
    //         return String.valueOf(value);
    //     }
        

    // }


    public static double Power(double base, int exponent){

        double result = 1.0;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;

    }


    public static double Sqrt(double x) {

        if (x < 0) {
            throw new IllegalArgumentException("Square root of negative numbers is not supported.");
        }

        double guess = x / 2.0;
        double epsilon = 1e-15;

        while (Abs(guess * guess - x) > epsilon * x) {
            guess = (guess + x / guess) / 2.0;
        }

        return guess;
    }


    public static double InnerProduct(double[] x, double[] y){
        int dataSize = x.length;
        double sum = 0.0;

        for (int i = 0; i < dataSize; i++) {
            sum += (x[i] * y[i]);
        }
        return sum;
    }


    public static double GetAverage(double[] values){
        double sum = 0;
        for (double value : values){
            sum += value;
        }
        return sum / values.length;
    }


    public static double GetStandardDeviation(double[] values){
        double average = GetAverage(values);
        double sum = 0;
        for (double value : values){
            sum += Power(value - average, 2);
        }
        return Sqrt(sum / (values.length - 1));
    }


    public static double[] GetLinearRegression(double[] x, double[] y) {
        int dataSize = x.length;

        // Calculate the means of x and y
        double sumX = 0;
        double sumY = 0;
        for (int i = 0; i < dataSize; i++) {
            sumX += x[i];
            sumY += y[i];
        }
        double meanX = sumX / dataSize;
        double meanY = sumY / dataSize;

        double numerator = 0;
        double denominator = 0;
        for (int i = 0; i < dataSize; i++) {
            numerator += (x[i] - meanX) * (y[i] - meanY);
            denominator += (x[i] - meanX) * (x[i] - meanX);
        }
        double slope = numerator / denominator;
        double intercept = meanY - slope * meanX;

        return new double[]{Round(slope), Round(intercept)};
    }

    
    public static ArrayList<String> GetTopThree(HashMap<String, Double> map){

        ArrayList<Double> standardDeviationArrayList = new ArrayList<>(map.values());

        ArrayList<Double> standardDeviationArrayListCpy = new ArrayList<>();
        standardDeviationArrayListCpy.addAll(standardDeviationArrayList);

        Collections.sort(standardDeviationArrayListCpy, Collections.reverseOrder());

        ArrayList<String> topThree = new ArrayList<String>();

        for (int i = 0; i < 3; i++) {
            Double standardDeviation = standardDeviationArrayListCpy.get(i);
            String key = GetKeyByValue(map, standardDeviation);
            topThree.add(key);
        }

        return topThree;
    }

    public static <K, V> K GetKeyByValue(Map<K, V> map, V value) {

        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }

        return null;
    }
}
