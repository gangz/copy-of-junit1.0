
package test.ui;


import java.util.Vector;
import test.framework.*;

public class UITestResult extends TestResult {
	private TestRunner fRunner;

	UITestResult(TestRunner runner) {
		fRunner= runner;
	}

	public synchronized void addError(Test test, Throwable t) {
		super.addError(test, t);
		fRunner.addError(this, test, t);
	}

	public synchronized void addFailure(Test test, Throwable t) {
		super.addFailure(test, t);
		fRunner.addFailure(this, test, t);
	}

	public synchronized void endTest(Test test) {
		super.endTest(test);
		fRunner.endTest(this, test);
	}
}