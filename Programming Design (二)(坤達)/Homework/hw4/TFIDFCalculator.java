import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

import java.io.IOException;


import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
// import java.util.HashMap;
// import java.util.Map;

import java.util.Set;
import java.util.Iterator;

import java.util.Collections;

import java.util.Objects;


public class TFIDFCalculator {

    public static void main(String[] args) {

        // String docsPath = args[0], testCasePath = args[1];

        String docsPath = "docs.txt", testCasePath = "tc0.txt";
        ArrayList<ArrayList<String>> docs = FileOperator.ReadDocs(docsPath);

        ArrayList<ArrayList<String>> testData = FileOperator.ReadTestData(testCasePath);
        
        ArrayList<String> terms = testData.get(0);
        ArrayList<String> docIndexes = testData.get(1);

        StringBuilder tfIdfAns = new StringBuilder();

        // System.out.println(terms);
        // System.out.println(docIndexes);

        for (int i = 0; i < terms.size(); i++) {
            String term = terms.get(i).toLowerCase();
            int docIndex = Integer.parseInt(docIndexes.get(i));

            // double tf = tf(docs.get(docIndex), term);
            // double idf = idf(docs, term);
            double tfIdf = tfIdfCalculate(docs.get(docIndex), docs, term);
            tfIdfAns.append(String.format("%.5f", tfIdf) + " ");
            

            // System.out.println(tf);
            // System.out.println(idf);
            // System.out.println(tfIdf);
            // System.out.print(String.format("%.5f", tfIdf) + " ");
        }


        tfIdfAns = tfIdfAns.delete(tfIdfAns.length() - 1, tfIdfAns.length());
        FileOperator.WriteOutput(tfIdfAns.toString());
        // System.out.println(tfIdfAns);
        // System.out.println("doc 18546: " + docs.get(18546));

    }

    public static double tf(ArrayList<String> doc, String term) {

        int numberTermInDoc = 0;
        for (String word : doc) {
            if (word.equals(term)) {
                numberTermInDoc++;
            }
        }
    
        return (double) numberTermInDoc / doc.size();
    }
    public static double idf(ArrayList<ArrayList<String>> docs, String term) {

        int numberDocContainTerm = 0;

        for (ArrayList<String> doc : docs){
            if (doc.contains(term)){
                numberDocContainTerm++;
            }
        }
        return Math.log((double) docs.size() / numberDocContainTerm);
    }

    public static double tfIdfCalculate(ArrayList<String> doc, ArrayList<ArrayList<String>> docs, String term) {
      return tf(doc, term) * idf(docs, term);
    }
}

class FileOperator{

    public static ArrayList<ArrayList<String>> ReadTestData(String fileName){
        
        ArrayList<ArrayList<String>> arrays = new ArrayList<>();
        ArrayList<String> terms = new ArrayList<>();
        ArrayList<String> docIndex = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            line = reader.readLine();
            String[] termArray1 = line.split(" ");
            terms = new ArrayList<>(Arrays.asList(termArray1));

            line = reader.readLine();
            String[] termArray2 = line.split(" ");
            docIndex = new ArrayList<>(Arrays.asList(termArray2));

        } catch (IOException e) {
            System.err.println("An error occurred while reading the terms.");
            e.printStackTrace();
        }

        arrays.add(terms);
        arrays.add(docIndex);

        return arrays;
    }

    public static ArrayList<ArrayList<String>> ReadDocs(String fileName){

        ArrayList<ArrayList<String>> docs = new ArrayList<>();  

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            StringBuilder doc = new StringBuilder();
            while ((line = reader.readLine()) != null) {

                int lineIdx = Integer.parseInt(line.substring(0, line.indexOf("\t")));

                String processedLine = line.substring(line.indexOf("\t")+1);
                doc.append(processedLine);

                if (lineIdx % 5 == 0) {

                    String[] wordsList = Parser.ProcessedString(doc.toString()).split(" ");
                    ArrayList<String> wordsArray= new ArrayList<>(Arrays.asList(wordsList));

                    docs.add(wordsArray);
                    doc.setLength(0);
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the docs.");
            e.printStackTrace();
        }

        return docs;

    }

    public static void WriteOutput(String tfIdfAns){

        // Write to files
        try {
            String fileName = "output.txt";
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(tfIdfAns);
            bw.close();
            // System.out.println("Java class has been generated: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}

class Parser{
    public static String ProcessedString(String str){
        return str.toLowerCase().replaceAll("[^a-zA-Z]", " ").replaceAll("\\s+", " ").trim();
    }
}

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEndOfWord = false;
}

class Trie {
    
    TrieNode root = new TrieNode();

    // 插入一個單詞到 Trie
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.isEndOfWord = true;
    }

    // 搜尋 Trie 中是否存在該單詞
    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.children[c - 'a'];
            if (node == null) {
                return false;
            }
        }
        return node.isEndOfWord;
    }
}