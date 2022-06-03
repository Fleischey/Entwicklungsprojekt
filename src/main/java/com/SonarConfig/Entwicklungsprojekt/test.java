package com.SonarConfig.Entwicklungsprojekt;

import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.UIManager;

import org.json.simple.JSONObject;
import org.springframework.web.client.RestClientException;

import config.FileExplorer;
import config.Reader;
import controller.ApiController;
import qualitypg.QualityProfile;
import qualitypg.Rule;

public class test {

	static String k;
	
	public static void main(String args[]) {
		
		UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
		for(UIManager.LookAndFeelInfo look : looks) {
			System.out.println(look.getClassName());
		}
		System.out.println(k);
	}
	
	
	
}
