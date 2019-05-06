package test.textui;

import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;
import test.framework.*;

class TextTestResult extends TestResult {

	public synchronized void addError(Test test, Throwable t) {
		super.addError(test, t);
		System.out.println("E");
	}

	public synchronized void addFailure(Test test, Throwable t) {
		super.addFailure(test, t);
		System.out.print("F");
	}

	public synchronized void startTest(Test test) {
		super.startTest(test);
		System.out.print(".");
	}

    /**
     * Prints the errors to the standard output
     */
	public void printErrors() {
	    if (testErrors() != 0) {
	        if (testErrors() == 1)
    	        System.out.println("There was "+testErrors()+" error:");
	        else
    	        System.out.println("There were "+testErrors()+" errors:");

            int i= 1;
    		for (Enumeration e= errors(); e.hasMoreElements(); i++) {
    		    TestFailure failure= (TestFailure)e.nextElement();
    			System.out.println(i+") "+failure.failedTest());
    			failure.thrownException().printStackTrace();
    	    }
    	}
	}

    /**
     * Prints failures to the standard output
     */
	public void printFailures() {
	    if (testFailures() != 0) {
	        if (testFailures() == 1)
    	        System.out.println("There was "+testFailures()+" failure:");
    	    else
    	        System.out.println("There were "+testFailures()+" failures:");

            int i= 1;
    		for (Enumeration e= failures(); e.hasMoreElements(); i++) {
    		    TestFailure failure= (TestFailure)e.nextElement();
    			System.out.print(i+") "+failure.failedTest());
    			Throwable t= failure.thrownException();
    			if (t.getMessage() != null)
    			    System.out.println(" \""+t.getMessage()+"\"");
    			else {
    			    System.out.println();
    			    failure.thrownException().printStackTrace();
    			}
    	    }
    	}
	}

    /**
     * Prints failures to the standard output
     */
	public synchronized void print() {
	    printHeader();
	    printErrors();
	    printFailures();
	}

    /**
     * Prints the header of the report
     */
    public void printHeader() {
        if (wasSuccessful()) {
            System.out.println();
            System.out.print("OK");
            System.out.println (" (" + runTests() + " tests)");

        } else {
            System.out.println();
            System.out.println("!!!FAILURES!!!");
	        System.out.println("Test Results:");
            System.out.println("Run: "+runTests()+ " Failures: "+testFailures()+" Errors: "+testErrors());
        }
    }
}