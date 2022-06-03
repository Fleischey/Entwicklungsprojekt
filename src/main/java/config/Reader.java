package config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import qualitypg.Condition;
import qualitypg.QualityGate;
import qualitypg.QualityProfile;
import qualitypg.Rule;

import org.w3c.dom.*;



public class Reader {
	String uri;
	boolean which = false;
	BufferedReader reader;
	public Reader(String path) {
		uri = path;
		try {
			reader = Files.newBufferedReader(Paths.get(uri));
			
			Gson gson = new GsonBuilder()
					.create();
			
			String jsonString = new String(Files.readAllBytes(Paths.get(uri)));
			if(jsonString.contains("qualitygates")) {
				which = true;
			} 
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public Object getQualitygp() {
		
		Gson gson = new GsonBuilder()
				.create();
			
		if(which == true) {
			return gson.fromJson(reader, QualityGate.class);
		} else {
			return gson.fromJson(reader, QualityProfile.class);
		}
	}
	
	public boolean getWhich() {
		return which;
	}
	
}
