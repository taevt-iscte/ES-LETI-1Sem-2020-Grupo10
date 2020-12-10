package backendTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Backend.TableEntry;

class tableEntryTest {

	@Test
	void test() {
		Backend.TableEntry te = new Backend.TableEntry("ADII", 14, 10, 123);
		te.setIPlasma_count(140);
		te.setPmd_count(140);
		te.setType("DCI");
		te.setUser_count(124);
		assertEquals(140, te.getIPlasma_count());
		assertEquals(140, te.getPmd_count());
		assertEquals("DCI", te.getType());
		assertEquals(124, te.getUser_count());
		
	}

}
