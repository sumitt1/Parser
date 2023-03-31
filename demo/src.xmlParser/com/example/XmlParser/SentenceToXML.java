package com.example.XmlParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SentenceToXML {

	  public static void main(String[] args) {

	    // Input file path
	    String inputFilePath = "input.txt";

	    // Output file path
	    String outputFilePath = "output.xml";

	    try {

	      // Create a new file object for the input file
	      File inputFile = new File(inputFilePath);

	      // Create a new buffered reader to read from the input file
	      BufferedReader reader = new BufferedReader(new FileReader(inputFile));

	      // Read the sentence from the input file
	      String sentence = reader.readLine();

	      // Close the input file
	      reader.close();

	      // Create a new document using the Java DOM parser
	      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	      DocumentBuilder builder = factory.newDocumentBuilder();
	      Document doc = builder.newDocument();

	      // Create the root element
	      Element rootElement = doc.createElement("text");
	      doc.appendChild(rootElement);

	      // Create the sentence element and add it to the root element
	      Element sentenceElement = doc.createElement("sentence");
	      rootElement.appendChild(sentenceElement);

	      // Split the sentence into words using whitespace as the delimiter
	      String[] words = sentence.split("\\s+");

	      // Create a new element for each word and add it to the sentence element
	      for (String word : words) {
	        Element wordElement = doc.createElement("word");
	        wordElement.appendChild(doc.createTextNode(word));
	        sentenceElement.appendChild(wordElement);
	      }

	      // Create a new file object for the output file
	      File outputFile = new File(outputFilePath);

	      // Create a new buffered writer to write to the output file
	      BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

	      // Write the XML output to the output file
	      writer.write(convertToString(doc));

	      // Close the output file
	      writer.close();

	    } catch (Exception e) {
	      e.printStackTrace();
	    }

	  }

	  // Convert the Document object to a String
	  public static String convertToString(Document doc) throws TransformerException {

	    TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer = tf.newTransformer();
	    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	    StringWriter writer = new StringWriter();
	    transformer.transform(new DOMSource(doc), new StreamResult(writer));
	    return writer.getBuffer().toString().replaceAll("\n|\r", "");

	  }

	}