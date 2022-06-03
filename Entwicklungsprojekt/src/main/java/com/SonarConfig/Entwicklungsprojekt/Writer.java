package com.SonarConfig.Entwicklungsprojekt;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Writer {

	public static void main(String[] args) {
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try(FileOutputStream out = new FileOutputStream("C:\\Users\\fleis\\Desktop\\SonarConfig.xml")) {
			
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			
			
			Element rootElement = doc.createElement("Config");
			doc.appendChild(rootElement);
			
			rootElement.appendChild(doc.createElement("QualityGates"));
			
			rootElement.getFirstChild().appendChild(doc.createElement("QualityGate"));
			Element g = (Element) rootElement.getFirstChild().getFirstChild();
			g.setAttribute("id", "g01");
			g.setAttribute("name", "NameTest");
			
			rootElement.getFirstChild().getFirstChild().appendChild(doc.createElement("Name"));
			rootElement.getFirstChild().getFirstChild().appendChild(doc.createElement("Condition"));
			
			rootElement.getFirstChild().getFirstChild().getLastChild().appendChild(doc.createElement("Metric"));
			rootElement.getFirstChild().getFirstChild().getLastChild().appendChild(doc.createElement("Operator"));
			rootElement.getFirstChild().getFirstChild().getLastChild().appendChild(doc.createElement("Value"));
			
			
			rootElement.appendChild(doc.createElement("QualityProfiles"));
			
			rootElement.getLastChild().appendChild(doc.createElement("QualityProfile"));
			Element p = (Element) rootElement.getFirstChild().getFirstChild();
			p.setAttribute("id", "p01");
			p.setAttribute("name", "NameTest");
			
			rootElement.getLastChild().getLastChild().appendChild(doc.createElement("Key"));
			rootElement.getLastChild().getLastChild().appendChild(doc.createElement("Rule"));
		
				
			writeConfig(doc, out);
		
		} catch(ParserConfigurationException pce) {
			
		} catch(IOException ioe) {
			
		}
		
		
	}
	
	public static void writeConfig(Document doc, OutputStream out)  {
		
		try {
			TransformerFactory trf = TransformerFactory.newInstance();
			Transformer tr = trf.newTransformer();
			tr.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(out);
			
			tr.transform(source, result);
		
		} catch (TransformerConfigurationException trce) {
			
		} catch (TransformerException tre) {
			
		} 
		
		
		
	}
}
