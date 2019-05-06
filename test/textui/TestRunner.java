
package test.textui;

import java.lang.reflect.*;
import test.framework.*;

/**
 * A command line based tool to run tests.
 * TestRunner expects as its only argument the name of a class with a <code>suite</code>
 * method. The suite method should return a test to be run.
 * TestRunner prints out a trace as the tests are executed followed by a
 * summary at the end.
 * Here is the synopsis:
 * <pre>
 *  java test.testui.TestRunner [-wait] VectorTest
 * </pre>
 */
public class TestRunner {

    private static boolean fWait= false;

    public static void main(String argv[]) {
        String testCase= "";
        for (int i= 0; i < argv.length; i++) {
            if (argv[i].equals("-wait"))
                fWait= true;
            else
                testCase= argv[i];
        }

        if (testCase.equals("")) {
            System.out.println("Usage: driver [-wait] testCaseName, where name is the name of the TestCase class");
            System.exit(0);
        }

        try {
            Class testClass= null;
	        Method suiteMethod= null;
	        Test suite= null;

        	try {
        		 testClass= Class.forName(testCase);
            } catch(Exception e) {
        		System.out.println("Suite class \""+testCase+"\" not found");
        		return;
        	}

        	try {
        		suiteMethod= testClass.getMethod("suite", new Class[0]);
         	} catch(Exception e) {
        		System.out.println("The suite class should have a method named \"suite()\"");
        		return;
        	}

        	try {
        		suite= (Test)suiteMethod.invoke(null, new Class[0]); // static method
        	} catch(Exception e) {
        		System.out.println("Could not invoke the suite() method");
        		return;
        	}
        	run(suite);
        }
        catch(Exception e) {
            System.out.println("Could not create and run test suite");
        }
    }

    /**
     * Runs a single test and collects its results.
     * This method can be used to start a test run
     * from your program.
     * <pre>
     * public static void main (String[] args) {
     *     test.textui.TestRunner.run(suite());
     * }
     * </pre>
     */
    public static void run(Test suite) {
        TextTestResult result= new TextTestResult();
        long startTime= System.currentTimeMillis();
        suite.run(result);
        long endTime= System.currentTimeMillis();
        long runTime= endTime-startTime;
        System.out.println();
        System.out.println("Time: "+runTime/1000+"."+runTime%1000);
        result.print();

        System.out.println();

        if (fWait) {
            System.out.println("<RETURN> to continue");
            try {
                System.in.read();
            }
            catch(Exception e) {
            }
        }
    }

}