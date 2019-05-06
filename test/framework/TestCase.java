
package test.framework;

import java.lang.reflect.*;

/**
 * A test case defines the fixture to run multiple tests. To define a test case<br>
 * 1) implement a subclass of TestCase<br>
 * 2) define instance variables that store the state of the fixture<br>
 * 3) initialize the fixture state by overriding <code>setUp</code><br>
 * 4) clean-up after a test by overriding <code>tearDown</code>.<br>
 * Each test runs in its own fixture so there
 * can be no side effects among test runs.
 * Here is an example:
 * <pre>
 * public class MathTest extends TestCase {
 *     protected double fValue1;
 *     protected double fValue2;
 *
 *     public MathTest(String name) {
 *         super(name);
 *     }
 *
 *    protected void setUp() {
 *         fValue1= 2.0;
 *         fValue2= 3.0;
 *     }
 * }
 * </pre>
 *
 * For each test implement a method which interacts
 * with the fixture. Verify the expected results with assertions specified
 * by calling <code>assert</code> with a boolean.
 * <pre>
 *    protected void testAdd() {
 *        double result= fValue1 + fValue2;
 *        assert(result == 5.0);
 *    }
 * </pre>
 * Once the methods are defined you can run them. The framework supports
 * both a static type safe and more dynamic way to run a test.
 * In the static way you override the runTest method and define the method to
 * be invoked. A convenient way to do so is with an anonymous inner class.
 * <pre>
 * Test test= new MathTest("add") {
 *        public void runTest() {
 *            testAdd();
 *        }
 * };
 * test.run();
 * </pre>
 * The dynamic way uses reflection to implement <code>runTest</code>. It dynamically finds
 * and invokes a method.
 * In this case the name of the test case has to correspond to the test method
 * to be run.
 * <pre>
 * Test= new MathTest("testAdd");
 * test.run();
 * </pre>
 * The tests to be run can be collected into a TestSuite. JUnit provides
 * different <i>test runners</i> which can run a test suite and collect the results.
 * The test runners expect a static method <code>suite</code> as the entry
 * point to get a test to run.
 * <pre>
 * public static Test suite() {
 *      suite.addTest(new MathTest("testAdd"));
 *      suite.addTest(new MathTest("testDivideByZero"));
 *      return suite;
 *  }
 * </pre>
 * @see TestResult
 * @see TestSuite
 */

public abstract class TestCase implements Test {
	private final String fName;

    /**
     * Constructs a test case with the given name.
     */
	public TestCase(String name) {
		fName= name;
	}

    /**
     * Asserts that a condition is true. If it isn't it throws
     * an AssertionFailedError.
     */
	protected void assert(boolean condition) {
		if (!condition)
			throw new AssertionFailedError();
	}

    /**
     * Asserts that a condition is true. If it isn't it throws
     * an AssertionFailedError with the given message.
     */
	protected void assert(String message, boolean condition) {
		if (!condition)
			throw new AssertionFailedError(message);
	}

    /**
     * Asserts that two objects are equal. If they are not
     * an AssertionFailedError is thrown.
     * @param expected the expected value of an object
     * @param actual the actual value of an object
     */
	protected void assertEquals(Object expected, Object actual) {
	    if (!expected.equals(actual))
    		assert(notEqualsMessage("", expected, actual), false);
	}

    /**
     * Asserts that two longs are equal.
     * @param expected the expected value of an object
     * @param actual the actual value of an object
     */
	protected void assertEquals(long expected, long actual) {
	    if (expected != actual)
    		assert(notEqualsMessage("", new Long(expected), new Long(actual)), false);
	}

    /**
     * Asserts that two doubles are equal.
     * @param expected the expected value of an object
     * @param actual the actual value of an object
     * @param delta tolerated delta
     */
	protected void assertEquals(double expected, double actual, double delta) {
	    if (Math.abs(expected-actual) > delta)
    		assert(notEqualsMessage("", new Double(expected), new Double(actual)), false);
	}

    /**
     * Asserts that two objects are equal. If they are not
     * an AssertionFailedError is thrown.
     * @param message the detail message for this assertion
     * @param expected the expected value of an object
     * @param actual the actual value of an object
     */
    protected void assertEquals(String message, Object expected, Object actual) {
        if (!expected.equals(actual))
            assert(notEqualsMessage(message+" ", expected, actual), false);
    }

    /**
     * Asserts that two longs are equal.
     * @param message the detail message for this assertion
     * @param expected the expected value of an object
     * @param actual the actual value of an object
     */
    protected void assertEquals(String message, long expected, long actual) {
	    if (expected != actual)
    		assert(notEqualsMessage(message+" ", new Long(expected), new Long(actual)), false);
    }

    /**
     * Asserts that two doubles are equal.
     * @param message the detail message for this assertion
     * @param expected the expected value of an object
     * @param actual the actual value of an object
     * @param delta tolerated delta
     */
	protected void assertEquals(String message, double expected, double actual, double delta) {
	    if (Math.abs(expected-actual) > delta)
    		assert(notEqualsMessage(message+" ", new Double(expected), new Double(actual)), false);
    }

    protected String notEqualsMessage(String message, Object expected, Object actual) {
    	return message+"expected:\""+expected+"\"but was:\""+actual+"\"";
    }

    /**
     * Runs the test case and collects the results in TestResult.
     * This is the template method that defines the control flow
     * for running a test case.
     */
	public void run(TestResult result) {
		result.startTest(this);
		setUp();

		try {
			runTest();
		}
		catch (AssertionFailedError e) {
			result.addFailure(this, e);
		}
		catch (Throwable e) {
			result.addError(this, e);
		}

		tearDown();
		result.endTest(this);
	}

    /**
     * Counts the number of test cases executed by run(TestResult result).
     */
	public int countTestCases() {
		return 1;
	}

	/**
     * Override to run the test and assert its state.
     * @exception Throwable if any exception is thrown
     */
    protected void runTest() throws Throwable {
        Method runMethod= null;
        try {
            runMethod= getClass().getMethod(fName, new Class[0]);
        } catch (NoSuchMethodException e) {
			e.fillInStackTrace();
            throw e;
        }

        try {
            runMethod.invoke(this, new Class[0]);
        }
        catch (InvocationTargetException e) {
			e.fillInStackTrace();
            throw e.getTargetException();
        }
        catch (IllegalAccessException e) {
			e.fillInStackTrace();
            throw e;
        }
    }


    /**
     * Sets up the fixture, for example, open a network connection.
     * This method is called before a test is executed.
     */
	protected void setUp() {
	}

    /**
     * Tears down the fixture, for example, close a network connection.
     * This method is called after a test is executed.
     */
	protected void tearDown() {
	}

	/**
     * Gets the name of the test case.
     */
	public String name() {
		return fName;
	}

    /**
     * A convenience method to run this test, collecting the results with a
     * default TestResult object.
     *
     * @see TestResult
     */
	public TestResult run() {
		TestResult result= defaultResult();
		run(result);
		return result;
	}

    /**
     * Returns a default TestResult object
     *
     * @see TestResult
     */
	protected TestResult defaultResult() {
	    return new TestResult();
	}

    /**
     * Returns a string representation of the test case
     */
	public String toString() {
	    return getClass().getName()+"."+name();
	}
}

