import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

import java.io.IOException;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class TFIDFCalculatorTrie {

    public static void main(String[] args) {
        String docsPath = args[0], testCasePath = args[1];

        FileOperator operator = new FileOperator();
        operator.readTestData(testCasePath);
        operator.readDocs(docsPath);

        List<String> terms = operator.getTerms();
        List<String> docIndexes = operator.getDocIndexes();
        List<Trie> docs = operator.getDocs();
        List<Integer> lengthOfDocs = operator.getLengthOfDocs();

        StringBuilder tfIdfAns = new StringBuilder();
        Trie idfCache = new Trie();

        for (int i = 0; i < terms.size(); i++) {
            String term = terms.get(i).toLowerCase();
            int docIndex = Integer.parseInt(docIndexes.get(i));
            int docLength = lengthOfDocs.get(docIndex);

            if (!idfCache.contains(term)) {
                idfCache.insert(term, idf(docs, term));
            }

            double tfIdf = tf(docs.get(docIndex), term, docLength) * idfCache.search(term);
            tfIdfAns.append(String.format("%.5f", tfIdf)).append(" ");
        }

        tfIdfAns.setLength(tfIdfAns.length() - 1);  // Remove trailing space
        FileOperator.writeOutput(tfIdfAns.toString());
    }

    public static double tf(Trie doc, String term, int lengthOfDoc) {
        return doc.search(term) / (double) lengthOfDoc;
    }

    public static double idf(List<Trie> docs, String term) {
        int numberDocContainTerm = 0;

        for (Trie doc : docs) {
            if (doc.contains(term)) {
                numberDocContainTerm++;
            }
        }

        return numberDocContainTerm == 0 ? 0 : Math.log((double) docs.size() / numberDocContainTerm);
    }
}

class FileOperator {
    private List<String> terms = new ArrayList<>();
    private List<String> docIndexes = new ArrayList<>();
    private List<Trie> docs = new ArrayList<>();
    private List<Integer> lengthOfDocs = new ArrayList<>();

    public void readTestData(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            terms = Arrays.asList(line.split(" "));

            line = reader.readLine();
            docIndexes = Arrays.asList(line.split(" "));
        } catch (IOException e) {
            System.err.println("An error occurred while reading the test data.");
            e.printStackTrace();
        }
    }

    public void readDocs(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            StringBuilder doc = new StringBuilder();
            int prevIndex = -1;

            while ((line = reader.readLine()) != null) {
                int lineIdx = Integer.parseInt(line.substring(0, line.indexOf("\t")));
                String processedLine = line.substring(line.indexOf("\t") + 1);
                doc.append(processedLine);

                if (prevIndex != -1 && lineIdx % 5 == 0) {
                    processDocument(doc.toString());
                    doc.setLength(0);
                }

                prevIndex = lineIdx;
            }

            if (doc.length() > 0) {
                processDocument(doc.toString());
            }

        } catch (IOException e) {
            System.err.println("An error occurred while reading the docs.");
            e.printStackTrace();
        }
    }

    private void processDocument(String document) {
        String[] wordsList = Parser.processedString(document).split(" ");
        Trie docTrie = new Trie();
        int lengthOfDoc = wordsList.length;

        for (String word : wordsList) {
            docTrie.insert(word);
        }

        lengthOfDocs.add(lengthOfDoc);
        docs.add(docTrie);
    }

    public static void writeOutput(String tfIdfAns) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            bw.write(tfIdfAns);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getTerms() {
        return terms;
    }

    public List<String> getDocIndexes() {
        return docIndexes;
    }

    public List<Trie> getDocs() {
        return docs;
    }

    public List<Integer> getLengthOfDocs() {
        return lengthOfDocs;
    }
}

class Parser {
    public static String processedString(String str) {
        return str.toLowerCase().replaceAll("[^a-zA-Z]", " ").replaceAll("\\s+", " ").trim();
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (!node.contains(c)) {
                node.put(c, new TrieNode());
            }
            node = node.get(c);
            node.incrementCount();
        }
        node.setEnd();
    }

    public void insert(String word, double value) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (!node.contains(c)) {
                node.put(c, new TrieNode());
            }
            node = node.get(c);
        }
        node.setEnd();
        node.setValue(value);
    }

    public boolean contains(String word) {
        TrieNode node = searchNode(word);
        return node != null && node.isEnd();
    }

    public double search(String word) {
        TrieNode node = searchNode(word);
        return node != null && node.isEnd() ? node.getValue() : 0;
    }

    private TrieNode searchNode(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (!node.contains(c)) {
                return null;
            }
            node = node.get(c);
        }
        return node;
    }
}

class TrieNode {
    private TrieNode[] links;
    private final int R = 26;
    private int count;
    private boolean isEnd;
    private double value;

    public TrieNode() {
        links = new TrieNode[R];
        count = 0;
        isEnd = false;
        value = 0;
    }

    public boolean contains(char ch) {
        return links[ch - 'a'] != null;
    }

    public TrieNode get(char ch) {
        return links[ch - 'a'];
    }

    public void put(char ch, TrieNode node) {
        links[ch - 'a'] = node;
    }

    public void setEnd() {
        isEnd = true;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void incrementCount() {
        count++;
    }
}
