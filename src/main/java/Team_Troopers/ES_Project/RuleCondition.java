package Team_Troopers.ES_Project;

public class RuleCondition {
	
	private String metric;
	private String operator;
	private long value;
	
	public RuleCondition() {
		this.metric ="";
		this.operator = "";
		this.value = 0;
	}
	
	public RuleCondition(String metric, String operator, long value) {
		if(value >= 0) {
			this.metric = metric;
			this.operator = operator;
			this.value = value;
		}
	}

	public String getMetric() {
		return metric;
	}

	public void setMetric(String metric) {
		this.metric = metric;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

}
