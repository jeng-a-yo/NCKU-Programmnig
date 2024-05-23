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
import java.util.HashMap;
import java.util.Map;

import java.util.Set;
import java.util.Iterator;

import java.util.Collections;

import java.util.Objects;


public class TFIDFCalculatorHash {

    public static void main(String[] args) {

        // String docsPath = args[0], testCasePath = args[1];
        String docsPath = "docs.txt", testCasePath = "tc0.txt";
        
        FileOperator operator = new FileOperator();
        operator.ReadTestData(testCasePath);
        operator.ReadDocs(docsPath);
        
        ArrayList<String> terms = operator.terms;
        ArrayList<String> docIndexes = operator.docIndexes;

        StringBuilder tfIdfAns = new StringBuilder();


        for (int i = 0; i < terms.size(); i++) {

            String term = terms.get(i).toLowerCase();
            int docIndex = Integer.parseInt(docIndexes.get(i));

            double tfIdf = tfIdfCalculate(operator.docs.get(docIndex), operator.docs, term, operator.lengthOfDocs.get(docIndex));
            tfIdfAns.append(String.format("%.5f", tfIdf) + " ");

        }


        tfIdfAns = tfIdfAns.delete(tfIdfAns.length() - 1, tfIdfAns.length());
        FileOperator.WriteOutput(tfIdfAns.toString());
    }

    public static double tf(HashMap<String, Integer> doc, String term, int lengthOfDoc) {

        return doc.containsKey(term) ? (double) doc.get(term) / lengthOfDoc : 0;
    }

    public static double idf(ArrayList<HashMap<String, Integer>> docs, String term) {

        int numberDocContainTerm = 0;

        for (HashMap<String, Integer> doc : docs){
            if (doc.containsKey(term)){
                numberDocContainTerm++;
            }
        }

        return Math.log((double) docs.size() / numberDocContainTerm);
    }

    public static double tfIdfCalculate(HashMap<String, Integer> doc, ArrayList<HashMap<String, Integer>> docs, String term, int docLength) {
      return tf(doc, term, docLength) * idf(docs, term);
    }
}

class FileOperator{


    ArrayList<String> terms = new ArrayList<>();
    ArrayList<String> docIndexes = new ArrayList<>();
    ArrayList<HashMap<String, Integer>> docs = new ArrayList<>();
    ArrayList<Integer> lengthOfDocs = new ArrayList<>();


    public void ReadTestData(String fileName){
        
        ArrayList<String> terms = new ArrayList<>();
        ArrayList<String> docIndexes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            line = reader.readLine();
            String[] termArray1 = line.split(" ");
            terms = new ArrayList<>(Arrays.asList(termArray1));

            line = reader.readLine();
            String[] termArray2 = line.split(" ");
            docIndexes = new ArrayList<>(Arrays.asList(termArray2));

        } catch (IOException e) {
            System.err.println("An error occurred while reading the terms.");
            e.printStackTrace();
        }

        this.terms = terms;
        this.docIndexes = docIndexes;
    }
    

    public void ReadDocs(String fileName){

        ArrayList<HashMap<String, Integer>> docs = new ArrayList<>();  
        ArrayList<Integer> lengthOfDocs = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            StringBuilder doc = new StringBuilder();
            while ((line = reader.readLine()) != null) {

                int lineIdx = Integer.parseInt(line.substring(0, line.indexOf("\t")));

                String processedLine = line.substring(line.indexOf("\t")+1);
                doc.append(processedLine);

                if (lineIdx % 5 == 0) {

                    String[] wordsList = Parser.ProcessedString(doc.toString()).split(" ");
                    HashMap<String, Integer> docHashMap = new HashMap<String, Integer>();

                    int lengthOfDoc = 0;

                    for (String word : wordsList) {
                        lengthOfDoc += 1;
                        if (!docHashMap.containsKey(word)) {
                            docHashMap.put(word, 1);
                        } else {
                            docHashMap.put(word, docHashMap.get(word) + 1);
                        }
                    }

                    lengthOfDocs.add(lengthOfDoc);
                    docs.add(docHashMap);
                    doc.setLength(0);
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the docs.");
            e.printStackTrace();
        }
        
        this.lengthOfDocs = lengthOfDocs;
        this.docs = docs;

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


// ==================================================================================================================================


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