package config;

import java.awt.Color;
import java.awt.Font;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import controller.ApiController;
import qualitypg.Condition;
import qualitypg.QualityGate;
import qualitypg.QualityProfile;
import qualitypg.Rule;

public class Writer {

	ApiController a;
	
	JFrame frame;
	JPanel tcontain;
	JLabel headline;
	
	public Writer(ApiController a) {
		
		this.a = a;
		
		frame = new JFrame("Konfigurationsdateien erstellen");
		frame.setSize(720, 480);
		frame.setResizable(false);
		frame.setLayout(new java.awt.BorderLayout());
		
		headline = new JLabel();
		headline.setText("Erstelle eine Konfigurationsdatei!");
		headline.setFont(new Font("Univers", Font.PLAIN, 26));
		headline.setForeground(new Color(255, 255, 255));
		
		tcontain = new JPanel();
		tcontain.setBackground(new Color(0,135,69));
		tcontain.add(headline);
		
		frame.add(tcontain, java.awt.BorderLayout.PAGE_START);
		frame.setVisible(true);
	}
	
	
//	public static void main(String[] args) throws IOException {
//		
//		ArrayList<Rule> rules = new ArrayList<>();
//		QualityProfile qp = new QualityProfile("NoRule", "java", "Test_key", rules);
//		
//		Gson gson = new GsonBuilder()
//				.setPrettyPrinting()
//				.create();
//		
//		String jsonString = gson.toJson(qp);
//		FileWriter file = new FileWriter("C:\\Users\\Niklas Gieshoff\\Desktop\\SonarConfigQualityProfileEmpty.json");
//		file.write(jsonString);
//		file.flush();
//		file.close();
//		System.out.print(jsonString);	
//			
//	}
	
	
	
}
