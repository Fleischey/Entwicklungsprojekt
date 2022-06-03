package controller;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.tomcat.util.json.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import qualitypg.QualityGate;
import qualitypg.QualityProfile;

@RestController
public class ApiController {
	
	//resttemplatebuilder to append basic auth to the resttemplate
	private RestTemplateBuilder rtb = new RestTemplateBuilder();
	
	//funktioniert nicht :( 
	@Value("${test.api}")
	private String getSettingsApi; //environment variable testen bei der ide
	
	//resttemplate used to post/get data from/to api
	private RestTemplate restTemplate = rtb.basicAuthentication("admin", "admin2").build();
	
	public ApiController() {
		
	}
	
	/**creates qualitygate by first creating a conditionless gate with a certain name
	 * and then adding the conditions to said gate
	 * @param g qualitygate
	 */
	public void createQuality(QualityGate g) {
		
		try {
			//multivaluemap to append data to post to api
			MultiValueMap m = new LinkedMultiValueMap();
			m.add("name", g.getName()); //name of qualitygate
			
			//posting the request
			restTemplate.postForObject("http://192.168.2.98:9000/api/qualitygates/create",
					m, String.class);
			
			
			//adding the conditions to the previously created gate
			for(int i = 0; i < g.getConditions().size(); i++) {
				
				m.clear();
				m.add("gateName", g.getName()); //name of gate
				m.add("metric", g.getConditions().get(i).getMetric()); //the rule
				m.add("op", g.getConditions().get(i).getOperator()); //greater or lower than
				m.add("error", g.getConditions().get(i).getValue()); //the value of the rule
				
				//posting the request
				restTemplate.postForObject("http://192.168.2.98:9000/api/qualitygates/create_condition",
						m, String.class);
				
			}
			
			if(g.getProject() != null) {
				m.clear();
				m.add("gateName", g.getName());
				m.add("projectKey", g.getProject());
				
				restTemplate.postForObject("http://192.168.2.98:9000/api/qualitygates/select",
						m, String.class);
				
			}
		} catch(Exception e) {
			
			String exceptionMessage = e.getMessage();
			exceptionMessage = exceptionMessage.substring(exceptionMessage.indexOf("msg")+6,exceptionMessage.length()-5);
			
			System.out.println("Watch out! " + exceptionMessage);
			
			
		}
		
	}
	
	/**creates qualityprofile by first creating the qualityprofile with a given name and language and then
	 * activating rules on the created qualityprofile
	 * 
	 * @param p qualityprofile
	 */
	public void createQuality(QualityProfile p) {
		
		try {
			//multivaluemap to append data to the request
			MultiValueMap m = new LinkedMultiValueMap();
			m.add("name", p.getName()); //name of qualityprofile
			m.add("language", p.getLanguage()); //language of qualityprofile
			
			//post the request and get the response
			String keyOfProfile = restTemplate.postForObject("http://192.168.2.98:9000/api/qualityprofiles/create",
					m, String.class);
			
			//get the key of the profile from response
			int temp = keyOfProfile.indexOf("key");
			keyOfProfile = keyOfProfile.substring(temp + 6, temp + 26);
			
			//clear out name and language
			m.clear();
			m.add("key", keyOfProfile); //key of previously created profile
			
			
			//activate all rules
			for(int i = 0; i < p.getRules().size(); i++) {
				
				m.add("rule", p.getRules().get(i).getRuleKey()); //rule to be activated
				
				//post the request
				restTemplate.postForObject("http://192.168.2.98:9000/api/qualityprofiles/activate_rule",
						m, String.class);
				
			}
			
			if(p.getProject() != null) {
				m.clear();
				m.add("qualityProfile", p.getName());
				m.add("language", p.getLanguage());
				m.add("project", p.getProject());
				
				restTemplate.postForObject("http://192.168.2.98:9000/api/qualityprofiles/add_project",
						m, String.class);
				
			}
		} catch(Exception e) {
			
			String exceptionMessage = e.getMessage();
			exceptionMessage = exceptionMessage.substring(exceptionMessage.indexOf("msg")+6,exceptionMessage.length()-5);
			
			System.out.println("Watch out! " + exceptionMessage);
			
			
		}
		
	}
	
	public ArrayList<QualityGate> getQualityGates() {
		
		ArrayList<String> name = new ArrayList<>();
		ArrayList<QualityGate> gates = new ArrayList<>();
		String keyOfProfile = restTemplate.getForObject("http://192.168.2.98:9000/api/qualitygates/list"
				, String.class);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		JsonElement je = JsonParser.parseString(keyOfProfile);
		keyOfProfile = gson.toJson(je);
		name = getNames(keyOfProfile);
		
		MultiValueMap m = new LinkedMultiValueMap();
		
		for(int i = 0; i < name.size(); i++) {
			
			m.add("name", name.get(i)); //name of qualitygate
			keyOfProfile = restTemplate.postForObject("http://192.168.2.98:9000/api/qualitygates/show", m
					, String.class);
			gates.add(umwandeln(keyOfProfile));
			
			m.clear();
		}
		
		
		return gates;
	
	}
	
	public void getQualityProfiles() {
		
	}
	
	public ArrayList<String> getNames(String list) {
		
		ArrayList<String> liste = new ArrayList<>();
		Scanner sc = new Scanner(list);
		
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			if(line.contains("\"name\"")) {
				line = line.substring(line.indexOf(":") + 3, line.indexOf(",")-1);
				liste.add(line);
			}
		}
		return liste;
		
	}
	
	public QualityGate umwandeln(String str) {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		JsonElement je = JsonParser.parseString(str);
		String keyOfProfile = gson.toJson(je);
		Scanner sc = new Scanner(keyOfProfile);
		keyOfProfile = "";
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			if(line.contains("\"id\"")) {
				
			} else {
				keyOfProfile += line + "\n";
				
			}
		}
		keyOfProfile = "{\n  \"type\": \"qualitygates\"," + keyOfProfile.substring(1, keyOfProfile.indexOf("]") + 1) + "}";
		return gson.fromJson(keyOfProfile, QualityGate.class);
	}
	
	
	
}
