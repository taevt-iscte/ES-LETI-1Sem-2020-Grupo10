package Backend;

import java.util.ArrayList;

import Team_Troopers.ES_Project.PrimaryController;
import Team_Troopers.ES_Project.RulesController;

/**
 * Classe responsável por gerar um objeto representativo de uma regra desenvolvida pelo utilizador.
 * 
 * @see      PrimaryController
 * @see		 RulesController
 * @see		 RuleCondition
 * @author   João Polónio
 */

public class Rule {
	
	private ArrayList<String> rule = new ArrayList<>();
	private int numberMetrics;
	
	/**
	  * Permite construir um objeto da classe.
	  * 
	  * @param    rule		     ArrayList com a informação submetida pelo utilizador a fim de ser utilizado.
	  * @author   João Polónio
	  */
	
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
