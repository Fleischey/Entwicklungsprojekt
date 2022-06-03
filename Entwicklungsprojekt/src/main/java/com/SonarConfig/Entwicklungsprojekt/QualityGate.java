package com.SonarConfig.Entwicklungsprojekt;

import java.util.ArrayList;

public class QualityGate {

	private String name;
	private ArrayList<Condition> conditions = new ArrayList<>();
	
	public QualityGate(String name, ArrayList<Condition> conditions) {
		
		this.name = name;
		this.conditions = conditions;
		
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Condition> getConditions() {
		return conditions;
	}
}
