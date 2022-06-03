package com.SonarConfig.Entwicklungsprojekt;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EntwicklungsprojektApplication {

	//static String username = "admin";
	//	static String password = "admin2";

	public static void main(String[] args) {
		//SpringApplication.run(EntwicklungsprojektApplication.class, args);
		
		try {
			
			CloseableHttpClient client =  HttpClientBuilder.create().build();
			ArrayList<QualityGate> qgs = new Reader("C:\\Users\\fleis\\Desktop\\SonarConfig.xml").getQualityGates();
			
			for(int i = 0; i < qgs.size(); i++) {
				
				HttpPost request = new HttpPost("http://192.168.2.98:9000/api/qualitygates/create");
				request.addHeader("content-type", "application/x-www-form-urlencoded");
				request.setEntity(new StringEntity("name=" + qgs.get(i).getName()));

				HttpResponse response = client.execute(request);
				
				request = new HttpPost("http://192.168.2.98:9000/api/qualitygates/create_condition");
				
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("gateName", qgs.get(i).getName()));
				
				params.add(new BasicNameValuePair("metric", qgs.get(i).getConditions().get(0).getMetric()));
				params.add(new BasicNameValuePair("op", qgs.get(i).getConditions().get(0).getOperator()));
				params.add(new BasicNameValuePair("error", qgs.get(i).getConditions().get(0).getValue()));
				
				HttpEntity entity = (HttpEntity) new UrlEncodedFormEntity(params);
				
				request.setEntity(entity);
				response = client.execute(request);
				
				EntityUtils.consume(entity);
				
				List<NameValuePair> params2 = new ArrayList<NameValuePair>();
				params2.add(new BasicNameValuePair("gateName", qgs.get(i).getName()));
				
				params2.add(new BasicNameValuePair("metric", qgs.get(i).getConditions().get(1).getMetric()));
				params2.add(new BasicNameValuePair("op", qgs.get(i).getConditions().get(1).getOperator()));
				params2.add(new BasicNameValuePair("error", qgs.get(i).getConditions().get(1).getValue()));
				
				entity = (HttpEntity) new UrlEncodedFormEntity(params2);
				
				request.setEntity(entity);
				response = client.execute(request);
				
				
//				for(int o = 0; o < qgs.get(i).getConditions().size(); o++) {
//					
//					List<NameValuePair> params = new ArrayList<NameValuePair>();
//					params.add(new BasicNameValuePair("gateName", qgs.get(i).getName()));
//					
//					params.add(new BasicNameValuePair("metric", qgs.get(i).getConditions().get(o).getMetric()));
//					params.add(new BasicNameValuePair("op", qgs.get(i).getConditions().get(o).getOperator()));
//					params.add(new BasicNameValuePair("error", qgs.get(i).getConditions().get(o).getValue()));
//					
//					request.setEntity(new UrlEncodedFormEntity(params));
//					response = client.execute(request);
//					
//				}
				


			}
			
			
			


			
			
			
		} catch(Exception e) {
			
		}
		
	}
	
	public void createQualityGate(QualityGate g) {
		//String auth = username + ":" + password;
		//String authEncoded = Base64.getEncoder().encodeToString(auth.getBytes());
		//URL sonar = new URL("http://xxx.xxx.xxx.xx:9000/api/qualitygates/create");
		//HttpURLConnection conn = (HttpURLConnection) sonar.openConnection();
		//conn.setRequestMethod("POST");
		//conn.setRequestProperty("Authorization", "Basic " + authEncoded);
		//conn.setRequestProperty("Content-Type", "application/json; utf-8");
		//conn.setDoOutput(true);
		//OutputStream out = conn.getOutputStream();
		//byte[] input = jsonInputString.getBytes("utf-8");
	    //out.write(input, 0, input.length);
		//conn.connect();
		//System.out.println(conn.getResponseCode());

		

		//Scanner sc = new Scanner();

		//while(sc.hasNext()) {
		//	string.append(sc.nextLine());
		//}
		
	}

}
