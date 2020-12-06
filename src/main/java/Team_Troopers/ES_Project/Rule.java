package Team_Troopers.ES_Project;

import java.util.ArrayList;

public class Rule {
	
	private ArrayList<String> rule = new ArrayList<>();
	private int numberMetrics;
	
	public Rule(ArrayList<String> rule) {
		this.rule = rule;
		if(rule.size() % 2 == 0) {
			rule.remove(rule.size()-1);
		}
		numberMetrics = rule.size()/2 +1;
	}

	public int getNumberMetrics() {
		return numberMetrics;
	}

	public ArrayList<String> getRule() {
		return rule;
	}
	
	
	
	

}
