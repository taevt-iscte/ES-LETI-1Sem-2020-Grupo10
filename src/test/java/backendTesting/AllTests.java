package backendTesting;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({excelRecordTest.class, ruleConditionTest.class,
	ruleTest.class, tableEntryTest.class})
public class AllTests {

}
