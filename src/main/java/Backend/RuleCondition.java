package Backend;

import Team_Troopers.ES_Project.PrimaryController;
import Team_Troopers.ES_Project.RulesController;

/**
 * Classe responsável por gerar um objeto representativo de uma condição desenvolvida pelo utilizador.
 * 
 * @see      PrimaryController
 * @see		 RulesController
 * @see		 Rules
 * @author   João Polónio
 */

public class RuleCondition {
	
	private String metric;
	private String operator;
	private long value;
	
	/**
	  * Permite construir um objeto da classe.
	  * 
	  * @author   João Polónio
	  */
	
	public RuleCondition() {
		this.metric ="";
		this.operator = "";
		this.value = 0;
	}
	
	/**
	  * Permite construir um objeto da classe.
	  * 
	  * @param    metric     	Metrica a ser inserida na condição criada.
	  * @param	  operador		Operador a ser utilizado na condição criada.
	  * @param	  value			Valor a ser reservado por parte da condição criada.
	  * @author   João Polónio
	  */
	
	public RuleCondition(String metric, String operator, long value) {
		if(value >= 0) {
			this.metric = metric;
			this.operator = operator;
			this.value = value;
		}
	}

	@Override
	public String toString() {
		return "RuleCondition [metric=" + metric + ", operator=" + operator + ", value=" + value + "]";
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
