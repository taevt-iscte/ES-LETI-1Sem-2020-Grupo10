package backendTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Backend.EvalType;
import Backend.ExcelRecord;

@SuppressWarnings("unused")
class excelRecordTest {

	@Test
	void test() {
		ExcelRecord record = new ExcelRecord(10, "pom", "static", "chart", 10, 4, 2, 8.0, false, true, true, false);
		EvalType[] array = record.getEval();
		assertEquals(EvalType.PLASMA_ADII, array[0]);
		assertEquals(EvalType.PMD_ADII, array[1]);
		
		EvalType tipo = record.userEval(true);
		assertEquals(EvalType.USER_ADII, tipo);
		
		record.setId(700);
		assertEquals(700, record.getId());
		record.setPackage_("corrier");
		assertEquals("corrier", record.getPackage_());
		record.setClass_("hardset");
		assertEquals("hardset", record.getClass_());
		record.setMethod("customize");
		assertEquals("customize", record.getMethod());
		record.setIs_long_method(false);
		assertEquals(false, record.isIs_long_method());
		record.setIPlasma(true);
		assertEquals(true, record.isIPlasma());
		record.setPmd(true);
		assertEquals(true, record.isPmd());
		record.setIs_feature_envy(false);
		assertEquals(false, record.isIs_feature_envy());
		record.setLoc(10);
		assertEquals(10, record.getLoc());
		record.setCyclo(4);
		assertEquals(4, record.getCyclo());
		record.setAtfd(2);
		assertEquals(2, record.getAtfd());
		record.setLaa(8.0);
		assertEquals(8.0, record.getLaa());
		
		ExcelRecord record2 = new ExcelRecord(10, "pom", "static", "chart", 10, 4, 2, 8.0, true, true, true, false);
		assertEquals(EvalType.PLASMA_DCI, record2.getEval()[0]);
		assertEquals(EvalType.PMD_DCI, record2.getEval()[1]);
		
		ExcelRecord record3 = new ExcelRecord(10, "pom", "static", "chart", 10, 4, 2, 8.0, false, false, false, true);
		assertEquals(EvalType.PLASMA_ADCI, record3.getEval()[0]);
		assertEquals(EvalType.PMD_ADCI, record3.getEval()[1]);
		
		ExcelRecord record4 = new ExcelRecord(10, "pom", "static", "chart", 10, 4, 2, 8.0, true, false, false, false);
		assertEquals(EvalType.PLASMA_DII, record4.getEval()[0]);
		assertEquals(EvalType.PMD_DII, record4.getEval()[1]);
		
		EvalType tipo2 = record.userEval(false);
		assertEquals(EvalType.USER_ADCI, tipo2);
		
		EvalType tipo3 = record2.userEval(true);
		assertEquals(EvalType.USER_DCI, tipo3);
		
		EvalType tipo4 = record2.userEval(false);
		assertEquals(EvalType.USER_DII, tipo4);
	}

}
