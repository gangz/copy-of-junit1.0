
package test.framework;

/**
 * A <em>test</em> can be run and collect its results.
 *
 * @see TestResult
 */
public interface Test {

    /**
     * Runs a test and collects its result in a TestResult instance.
     */
	public abstract void run(TestResult result);

    /**
     * Counts the number of test cases that will be run by this test.
     */
	public abstract int countTestCases();

}