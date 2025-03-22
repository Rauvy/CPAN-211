/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab_8;

import java.io.*;
import java.util.*;

/**
 *
 * @author yakubiv
 */

// Rehina Yakubiv (N01649674)
public class Lab_8 {

    // Method to read a file and store unique words in a Set
    public static Set<String> readFileToSet(String filename) throws IOException {
        Set<String> words = new HashSet<>(); // Using HashSet to store unique words
        BufferedReader br = new BufferedReader(new FileReader(filename));

        String line;
        while((line = br.readLine()) != null){
            // Tokenize the line using multiple delimiters (punctuation & special characters)
            StringTokenizer st = new StringTokenizer(line, " .'\"-,:;()[]{}`/*+");

            while(st.hasMoreTokens()){
                String token = st.nextToken().toLowerCase();
                words.add(token);
            }
        }

        br.close();
        return words;
    }

    public static double computeSimilarity(Set<String> set1, Set<String> set2){
        Set<String> intersection = new HashSet<>(set1);// Create a new HashSet to store common words (Intersection)
        intersection.retainAll(set2);

        Set<String> union = new HashSet<>(set2); // Create a new HashSet to store total unique words (Union)
        union.addAll(set1);

        return (double) intersection.size() / union.size();
    }

    public static void main(String[] args) {
        try{
            Set<String> nhlWords = readFileToSet("NHL.txt");
            Set<String> javaWords = readFileToSet("Java.txt");
            Set<String> javaCppWords = readFileToSet("JavaCPP.txt");

            double NHLJavaSimilarity = computeSimilarity(nhlWords, javaWords);
            double NHLJavaCppSimilarity = computeSimilarity(nhlWords, javaCppWords);
            double javaJavaCppSimilarity = computeSimilarity(javaWords, javaCppWords);

            System.out.printf("Similarity between NHL.txt and Java.txt: %.2f%%\n", NHLJavaSimilarity * 100);
            System.out.printf("Similarity between NHL.txt and JavaCPP.txt: %.2f%%\n", NHLJavaCppSimilarity * 100);
            System.out.printf("Similarity between Java.txt and JavaCPP.txt: %.2f%%\n", javaJavaCppSimilarity * 100);
        } catch (IOException e){
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    
}
