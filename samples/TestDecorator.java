package samples;

import test.framework.*;

/**
 * A Decorator for Tests. Use TestDecorator as the base class
 * for defining new test decorators.
 *
 */
public class TestDecorator implements  Test {
	protected Test  fTest;

    public TestDecorator(Test test) {
    	fTest= test;
    }

    public int countTestCases() {
    	return fTest.countTestCases();
    }

    public String toString() {
    	return fTest.toString();
    }

    public void run(TestResult result) {
        fTest.run(result);
    }
}
