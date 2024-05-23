import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class TFIDFCalculator {

    public static void main(String[] args) {
        // Validate command line arguments
        if (args.length != 2) {
            System.err.println("Usage: java TFIDFCalculator <docsFilePath> <testCasesFilePath>");
            return;
        }

        String docsFilePath = args[0], testCasesFilePath = args[1];

        // Initialize FileOperator to handle file reading and processing
        FileOperator fileOperator = new FileOperator();
        fileOperator.readTestData(testCasesFilePath);  // Read search terms and document indexes
        fileOperator.readDocs(docsFilePath);           // Read and process documents
        
        // Get data from FileOperator
        List<String> searchTerms = fileOperator.getSearchTerms();
        List<String> documentIndexes = fileOperator.getDocumentIndexes();
        List<Map<String, Integer>> documents = fileOperator.getDocuments();
        List<Integer> documentLengths = fileOperator.getDocumentLengths();

        StringBuilder tfIdfResults = new StringBuilder();
        Map<String, Double> idfCache = new HashMap<>();

        // Calculate TF-IDF for each search term in the corresponding document
        for (int i = 0; i < searchTerms.size(); i++) {
            String term = searchTerms.get(i).toLowerCase();  // Convert term to lowercase
            int docIndex = Integer.parseInt(documentIndexes.get(i));  // Parse document index
            int docLength = documentLengths.get(docIndex);  // Get document length

            // Calculate IDF if not already cached
            if (!idfCache.containsKey(term)) {
                idfCache.put(term, calculateIDF(documents, term));
            }

            // Calculate TF-IDF and append to results
            double tfIdf = calculateTF(documents.get(docIndex), term, docLength) * idfCache.get(term);
            tfIdfResults.append(String.format("%.5f", tfIdf)).append(" ");
        }

        tfIdfResults.setLength(tfIdfResults.length() - 1);  // Remove trailing space
        FileOperator.writeOutput(tfIdfResults.toString());  // Write results to output file
    }

    // Calculate Term Frequency (TF)
    public static double calculateTF(Map<String, Integer> document, String term, int documentLength) {
        return document.getOrDefault(term, 0) / (double) documentLength;
    }

    // Calculate Inverse Document Frequency (IDF)
    public static double calculateIDF(List<Map<String, Integer>> documents, String term) {
        int numberOfDocumentsContainingTerm = 0;

        // Count number of documents containing the term
        for (Map<String, Integer> document : documents) {
            if (document.containsKey(term)) {
                numberOfDocumentsContainingTerm++;
            }
        }

        // Calculate IDF, handle case where term is not present in any document
        return numberOfDocumentsContainingTerm == 0 ? 0 : Math.log((double) documents.size() / numberOfDocumentsContainingTerm);
    }
}

class FileOperator {
    
    private List<String> searchTerms = new ArrayList<>();
    private List<String> documentIndexes = new ArrayList<>();
    private List<Map<String, Integer>> documents = new ArrayList<>();
    private List<Integer> documentLengths = new ArrayList<>();

    // Read search terms and document indexes from the test cases file
    public void readTestData(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            searchTerms = Arrays.asList(line.split(" "));  // First line contains search terms
            
            line = reader.readLine();
            documentIndexes = Arrays.asList(line.split(" "));  // Second line contains document indexes
        } catch (IOException e) {
            System.err.println("An error occurred while reading the test data.");
            e.printStackTrace();
        }
    }

    // Read and process documents from the docs file
    public void readDocs(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            StringBuilder documentContent = new StringBuilder();
            int previousIndex = -1;

            // Read each line and process documents
            while ((line = reader.readLine()) != null) {
                int lineIndex = Integer.parseInt(line.substring(0, line.indexOf("\t")));
                String processedLine = line.substring(line.indexOf("\t") + 1);
                documentContent.append(processedLine);

                // Process document every 5 lines
                if (previousIndex != -1 && lineIndex % 5 == 0) {
                    processDocument(documentContent.toString());
                    documentContent.setLength(0);
                }

                previousIndex = lineIndex;
            }

            // Process remaining document content
            if (documentContent.length() > 0) {
                processDocument(documentContent.toString());
            }

        } catch (IOException e) {
            System.err.println("An error occurred while reading the documents.");
            e.printStackTrace();
        }
    }

    // Process individual document text into term frequency map
    private void processDocument(String document) {
        String[] wordsList = Parser.processedString(document).split(" ");
        Map<String, Integer> documentTermFrequency = new HashMap<>();
        int documentLength = wordsList.length;

        // Count term frequencies in the document
        for (String word : wordsList) {
            documentTermFrequency.put(word, documentTermFrequency.getOrDefault(word, 0) + 1);
        }

        documentLengths.add(documentLength);  // Store document length
        documents.add(documentTermFrequency);  // Store term frequency map
    }

    // Write TF-IDF results to the output file
    public static void writeOutput(String tfIdfResults) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            writer.write(tfIdfResults);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getters for the search terms, document indexes, documents, and document lengths
    public List<String> getSearchTerms() {
        return searchTerms;
    }

    public List<String> getDocumentIndexes() {
        return documentIndexes;
    }

    public List<Map<String, Integer>> getDocuments() {
        return documents;
    }

    public List<Integer> getDocumentLengths() {
        return documentLengths;
    }
}

class Parser {
    // Process input string: convert to lowercase, remove non-alphabetic characters, and trim spaces
    public static String processedString(String str) {
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