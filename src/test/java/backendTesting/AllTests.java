package backendTesting;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({excelRecordTest.class, ruleConditionTest.class, ruleTest.class, tableEntryTest.class})
public class AllTests {

}
