package com.example.csvparser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class TextToCSV {

  public static void main(String[] args) {

	 // Input file path
    String inputFilePath = "small.in";

    // Output file path
    String outputFilePath = "output.csv";

    try {

      // Create a new file object for the input file
      File inputFile = new File(inputFilePath);

      // Create a new buffered reader to read from the input file
      BufferedReader reader = new BufferedReader(new FileReader(inputFile));

      // Create a new string builder to build the CSV output
      StringBuilder sb = new StringBuilder();
      StringBuilder sb1=new StringBuilder();

      // Initialize the sentence count
      int sentenceCount = 0;

      // Read each line from the input file
      String line;
      while ((line = reader.readLine()) != null) {

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

          // Increment the sentence count
          sentenceCount++;

          // Append the header row with the word columns, only for the first sentence
          if (sentenceCount == 1) {
            sb.append("Sentence,");
            for (int i = 1; i <= words.length; i++) {
              sb.append("Word ");
              sb.append(i);
              sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("\n");
          }
          else
          {
        	    for (int i = 1; i <= words.length; i++) {
                    sb1.append("Word ");
                    sb1.append(i);
                   sb1.append(",");
                  }
                          	  
          }

          // Append the row with the sentence and word count
          sb.append("Sentence ");
          sb.append(sentenceCount);
          sb.append(",");
          sb.append(words.length);
          sb.append("\n");

          // Append the row with the sentence words
          sb.append(",");
          for (String word : words) {
            sb.append(word);
            sb.append(",");
          }
          sb.deleteCharAt(sb.length() - 1);
          sb.append("\n");

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

