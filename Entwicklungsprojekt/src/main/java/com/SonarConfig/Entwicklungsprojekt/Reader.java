package com.SonarConfig.Entwicklungsprojekt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;

public class Reader {
	
	private ArrayList<QualityGate> qualityGates;
	private String pathToFile;
	//private ArrayList<QualityProfile> qualityProfiles;
	
	
	public Reader(String pathToFile) {
		
		this.pathToFile = pathToFile;
		qualityGates = getQualityGates();
		//qualityProfiles = getQualityProfiles(pathToFile);
		
	}
	
	/**Gets the QualityGates of the given Configfile
	 * 
	 * @param pathToFile path from the Configfile
	 * @return all the QualityGates in an arraylist
	 */
	public ArrayList<QualityGate> getQualityGates() {
		
		//initiate retun arraylist
		ArrayList<QualityGate> qgs = new ArrayList<>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			
			DocumentBuilder db = dbf.newDocumentBuilder();
			//parse the configfile
			Document doc = db.parse(new File(pathToFile));
			//get root element of structure
			Element rootElement = doc.getDocumentElement();
			//get qualitygates
			NodeList list = doc.getElementsByTagName("QualityGate");

			//for every qualitygate get the conditions and put into return arraylist
			for(int i = 0; i < list.getLength(); i++) {
				
				Element e = (Element) list.item(i);
				String name = e.getAttribute("name");
				
				//list of conditions
				NodeList conditions = e.getElementsByTagName("Condition");
				
				ArrayList<Condition> conds = new ArrayList<>();
				
				//get the values of the conditions
				for(int c = 0; c < conditions.getLength(); c++) {
					
					Element tempC = (Element) conditions.item(c);
					
					String metric = tempC.getElementsByTagName("Metric").item(0).getTextContent();
					String operator = tempC.getElementsByTagName("Operator").item(0).getTextContent();
					String value = tempC.getElementsByTagName("Value").item(0).getTextContent();
					
					conds.add(new Condition(metric, operator, value));
					
					
				}

				qgs.add(new QualityGate(name, conds));
			}
			
		} catch(Exception e) {
			
		}
		
		return qgs;
		
	}
	
	
	
}
