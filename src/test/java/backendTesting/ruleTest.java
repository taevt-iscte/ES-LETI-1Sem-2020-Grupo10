package backendTesting;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Backend.Rule;

class ruleTest {

	@Test
	void test() {
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("CYCLO");
		arr.add(">");
		arr.add("5");
		arr.add("AND");
		Rule rl = new Rule(arr);
		assertEquals(2, rl.getNumberMetrics());
		assertEquals(arr, rl.getRule());
		
	}

}
