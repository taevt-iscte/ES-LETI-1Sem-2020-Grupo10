package backendTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Backend.RuleCondition;
import Team_Troopers.ES_Project.*;

class ruleConditionTest {

	@Test
	void test() {
		RuleCondition rc = new RuleCondition("CYCLO", ">", 2);
		rc.setMetric("LOC");
		rc.setOperator("<");
		rc.setValue(2);
		assertEquals("<", rc.getOperator());
		assertEquals("LOC", rc.getMetric());
		RuleCondition rc1 = new RuleCondition();
		assertEquals(0, rc1.getValue());
	}

}
