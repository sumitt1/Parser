package com.example.csvparser;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TextToCSVTest
{
	 private static final String INPUT_FILE_PATH = "small.in";
	  private static final String OUTPUT_FILE_PATH = "output_small.csv";
	 

    @Test
    public void testTextToCSV() throws IOException {
        // Given
        String inputFilePath = "src/test/resources/small.in";
        String outputFilePath = "src/test/resources/output.csv";
         String expectedOutput = "Sentence,Word 1,Word 2,Word 3\n" +
                                "Sentence 1,3,apple,banana,cherry\n" +
                                "Sentence 2,4,dog,cat,mouse,rat\n";

        // When
        TextToCSV.main(new String[] {inputFilePath, outputFilePath});

        // Then
        File outputFile = new File(outputFilePath);
        assertTrue(outputFile.exists());

        String outputContent = new String(Files.readAllBytes(outputFile.toPath()));
        assertEquals(expectedOutput, outputContent);
    }
    
    
    @Test
    public void testEmptyInputFile() throws IOException {
        // Set up the test
        File inputFile = File.createTempFile("empty", ".in");
        File outputFile = File.createTempFile("output", ".csv");
        TextToCSV converter = new TextToCSV();

        // Execute the code being tested
        
        // Check the result
        assertTrue(outputFile.exists());
      //  assertEquals("", readFromFile(outputFile));
    }

}
