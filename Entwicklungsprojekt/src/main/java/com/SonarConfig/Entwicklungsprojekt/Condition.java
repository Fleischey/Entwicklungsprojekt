package com.SonarConfig.Entwicklungsprojekt;

public class Condition {

	private String metric;
	private String operator;
	private String value;
	
	public Condition(String metric, String operator, String value) {
		
		this.metric = metric;
		this.operator = operator;
		this.value = value;
		
	}
	
	public String getMetric() {
		return metric;
	}
	
	public String getOperator() {
		return operator;
	}
	
	public String getValue() {
		return value;
	}
	
}
