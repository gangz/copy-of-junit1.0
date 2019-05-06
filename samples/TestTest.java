package samples;

import java.util.Vector;
import test.framework.*;

/**
 * A test case testing the testing framework.
 *
 */
public class TestTest extends TestCase {
    protected TestCase fFailure, fError, fSuccess;

    public TestTest(String name) {
        super(name);
    }

    protected void setUp() {
        fFailure= new TestCase("failure") {
            protected void runTest() {
                assert(false);
            }
        };

        fError= new TestCase("error") {
            protected void runTest() {
                Vector v= new Vector();
                v.elementAt(-1);
            }
        };

        fSuccess= new TestCase("success") {
            protected void runTest() {
                assert(true);
            }
        };
    }

    public void testFailure() {
        TestResult result= fFailure.run();
        assert(result.runTests() == 1);
        assert(result.testFailures() == 1);
        assert(result.testErrors() == 0);
        assert(! result.wasSuccessful());
    }

    public void testError() {
        TestResult result= fError.run();
        assert(result.runTests() == 1);
        assert(result.testFailures() == 0);
        assert(result.testErrors() == 1);
        assert(! result.wasSuccessful());
    }

    public void testSuccess() {
        TestResult result= fSuccess.run();
        assert(result.runTests() == 1);
        assert(result.testFailures() == 0);
        assert(result.testErrors() == 0);
        assert(result.wasSuccessful());
    }

    public static Test suite() {
        TestSuite suite= new TestSuite();

        suite.addTest(new TestTest("testFailure"));
        suite.addTest(new TestTest("testError"));
        suite.addTest(new TestTest("testSuccess"));
        return suite;
    }
}

