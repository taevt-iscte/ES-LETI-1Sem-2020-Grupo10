package Team_Troopers.ES_Project;

import java.util.ArrayList;

public class Rule {
	
	private ArrayList<String> rule = new ArrayList<>();
	private int numberRules;
	
	public Rule(ArrayList<String> rule) {
		this.rule = rule;
		if(rule.size() == 3) {
			numberRules = 1;
		}
		else {
			numberRules = 2;
		}
	}
	
	

}
