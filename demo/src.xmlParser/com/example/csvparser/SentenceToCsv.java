package com.example.csvparser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class SentenceToCsv {

  public static void main(String[] args) {

    // Input file path
    String inputFilePath = "input.txt";

    // Output file path
    String outputFilePath = "output.csv";

    try {

      // Create a new file object for the input file
      File inputFile = new File(inputFilePath);

      // Create a new buffered reader to read from the input file
      BufferedReader reader = new BufferedReader(new FileReader(inputFile));

      // Create a new string builder to build the CSV output
      StringBuilder sb = new StringBuilder();

      // Initialize the sentence count
      int sentenceCount = 0;

      // Read each line from the input file
      String line;
      while ((line = reader.readLine()) != null) {

        // Increment the sentence count
        sentenceCount++;

        // Split the line into sentences using period as the delimiter
        String[] sentences = line.split("\\.");

        // For each sentence in the line
        for (String sentence : sentences) {

          // Remove leading and trailing whitespace from the sentence
          sentence = sentence.trim();

          // Skip empty sentences
          if (sentence.isEmpty()) {
            continue;
          }

          // Split the sentence into words using whitespace as the delimiter
          String[] words = sentence.split("\\s+");

          // Append the sentence row with the sentence number
          sb.append("Sentence");
          sb.append(sentenceCount);
          sb.append(",");

          // Append word headers for each word column
          for (int i = 1; i <= 3; i++) {
            sb.append("Word ");
            sb.append(i);
            sb.append(",");
          }

          // Remove the trailing comma from the last word header
          sb.setLength(sb.length() - 1);

          // Append a newline character to the header row
          sb.append("\n");

          // Append the sentence row with the sentence words
          for (int i = 0; i < words.length; i++) {

            // Append the current word to the sentence row
            sb.append(words[i]);
            sb.append(",");

            // Append empty cells for any preceding word columns
            for (int j = 1; j < i + 1; j++) {
              sb.append(",");
            }

            // Append empty cells for any remaining word columns
            for (int j = i + 1; j < 3; j++) {
              sb.append(",");
            }

            // Append a newline character to the current word row
            sb.append("\n");

          }

        }

      }

      // Close the input file
      reader.close();

      // Create a new file object for the output file
      File outputFile = new File(outputFilePath);

      // Create a new buffered writer to write to the output file
      BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

      // Write the CSV output to the output file
      writer.write(sb.toString());

      // Close the output file
      writer.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
