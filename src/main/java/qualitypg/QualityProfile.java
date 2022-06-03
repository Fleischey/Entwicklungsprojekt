package qualitypg;

import java.util.ArrayList;

public class QualityProfile {

	String type;
	private String name;
	private String language;
	private String projectKey;
	private ArrayList<Rule> rules = new ArrayList<>();
	
	public QualityProfile(String name, String language, ArrayList<Rule> rules) {
		
		this.name = name;
		this.language = language;
		this.rules = rules;
		type = "qualityprofiles";
		
	}
	
	public QualityProfile(String name, String language, String projectKey, ArrayList<Rule> rules) {
		
		this.name = name;
		this.language = language;
		this.projectKey = projectKey;
		this.rules = rules;
		type = "qualityprofiles";
		
	}
	
	public String getName() {
		return name;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public String getProject() {
		return projectKey;
	}
	
	public ArrayList<Rule> getRules() {
		return rules;
	}
}
