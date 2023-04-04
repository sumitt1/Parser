package com.example.XmlParser;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XmlParserTest {
  
  private static final String INPUT_FILE_PATH = "small.in";
  private static final String OUTPUT_FILE_PATH = "output_small.xml";
  
  private File outputFile;
  
  @Before
  public void setUp() throws Exception {
    // Delete the output file if it exists
    outputFile = new File(OUTPUT_FILE_PATH);
    if (outputFile.exists()) {
      outputFile.delete();
    }
  }
  
  @After
  public void tearDown() throws Exception {
    // Delete the output file if it was created during the test
    if (outputFile.exists()) {
      outputFile.delete();
    }
  }
  
  @Test
  public void testConversion() throws Exception {
    // Prepare test input
    String sentence = "This is a test sentence";
    File inputFile = new File(INPUT_FILE_PATH);
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile))) {
      writer.write(sentence);
    }
    
    // Run the conversion
    SentenceToXMLSmallFile.main(new String[0]);
    
    // Verify the output
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    Document doc = factory.newDocumentBuilder().parse(outputFile);
    Element rootElement = doc.getDocumentElement();
    assertEquals("text", rootElement.getNodeName());
    NodeList sentenceList = rootElement.getElementsByTagName("sentence");
    assertEquals(1, sentenceList.getLength());
    Element sentenceElement = (Element) sentenceList.item(0);
    NodeList wordList = sentenceElement.getElementsByTagName("word");
    assertEquals(5, wordList.getLength());
    assertEquals("This", wordList.item(0).getTextContent());
    assertEquals("is", wordList.item(1).getTextContent());
    assertEquals("a", wordList.item(2).getTextContent());
    assertEquals("test", wordList.item(3).getTextContent());
    assertEquals("sentence", wordList.item(4).getTextContent());
    
    // Clean up
    inputFile.delete();
  }
  
  @Test(expected = ParserConfigurationException.class)
  public void testParserConfigurationException() throws Exception {
    // Replace the DocumentBuilderFactory with a factory that always throws ParserConfigurationException
    DocumentBuilderFactory factory = new DocumentBuilderFactory() {
      @Override
      public void setAttribute(String name, Object value) throws IllegalArgumentException {}
      @Override
      public Object getAttribute(String name) throws IllegalArgumentException { return null; }
      @Override
      public void setFeature(String name, boolean value) throws ParserConfigurationException {}
      @Override
      public boolean getFeature(String name) throws ParserConfigurationException { return false; }
      @Override
      public DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
        throw new ParserConfigurationException();
      }
    };
    
    // Run the conversion
    SentenceToXMLSmallFile.main(new String[0]);
  }

}
