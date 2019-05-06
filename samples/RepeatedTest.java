package samples;

import test.framework.*;

/**
 * A Decorator that runs a test repeatedly.
 *
 */
public class RepeatedTest extends  TestDecorator {
	private int fTimesRepeat;

    public RepeatedTest(Test test, int repeat) {
    	super(test);
    	fTimesRepeat= repeat;
    }

    public int countTestCases() {
    	return super.countTestCases()*fTimesRepeat;
    }

    public String toString() {
    	return super.toString()+"(repeated)";
    }

    public void run(TestResult result) {
        for (int i= 0; i < fTimesRepeat; i++) {
        	if (result.shouldStop() )
        		break;
        	super.run(result);
        }
    }
}