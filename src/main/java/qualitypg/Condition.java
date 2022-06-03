package qualitypg;

public class Condition {

	private String metric;
	private String op;
	private String error;
	
	public Condition(String metric, String operator, String value) {
		
		this.metric = metric;
		this.op = operator;
		this.error = value;
		
	}
	
	public String getMetric() {
		return metric;
	}
	
	public String getOperator() {
		return op;
	}
	
	public String getValue() {
		return error;
	}
	
}
