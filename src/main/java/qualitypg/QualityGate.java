package qualitypg;

import java.util.ArrayList;

public class QualityGate {

	String type;
	private String name;
	private String projectKey;
	private ArrayList<Condition> conditions = new ArrayList<>();
	
	public QualityGate(String name, ArrayList<Condition> conditions) {
		
		this.name = name;
		this.conditions = conditions;
		type = "qualitygates";
		
	}
	
	public QualityGate(String name, String projectKey, ArrayList<Condition> conditions) {
		
		this.name = name;
		this.projectKey = projectKey;
		this.conditions = conditions;
		type = "qualitygates";
		
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Condition> getConditions() {
		return conditions;
	}
	
	public String getProject() {
		return projectKey;
	}
}
