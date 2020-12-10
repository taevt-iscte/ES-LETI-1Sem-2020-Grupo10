package guiTesting;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import Backend.EvalType;
import Team_Troopers.ES_Project.TextualController;

class textualControllerTest {

	@Test
	void test() {
		HashMap<EvalType, Integer> data = new HashMap<EvalType, Integer>();
		data.put(EvalType.PLASMA_DCI, 241);
		data.put(EvalType.PLASMA_DII, 0);
		data.put(EvalType.PMD_DCI, 10);
		
		TextualController textual = new TextualController(data);
	}

}
