package samples;

import test.framework.*;

/**
 * TestSuite that runs all the sample tests
 *
 */
public class AllTests {

    public static Test suite ( ) {
        TestSuite suite= new TestSuite();
        suite.addTest(VectorTest.suite());
        suite.addTest(samples.money.MoneyTest.suite());
        suite.addTest(TestTest.suite());
	    return suite;
	}
}