package samples;

import test.framework.*;

/**
 * Some simple tests.
 *
 */
public class SimpleTest extends TestCase {
    protected double fValue1;
    protected double fValue2;

    public SimpleTest(String name) {
        super(name);
    }

    protected void setUp() {
        fValue1= 2.0;
        fValue2= 3.0;
    }

    public void testAdd() {
        double result= fValue1 + fValue2;
        assert(result == 6.0);
    }

    public void testDivideByZero() {
        int zero= 0;
        int result= 8/zero;
    }

    public void testEquals() {
        assertEquals(12, 12);
        assertEquals(12L, 12L);
        assertEquals(new Long(12), new Long(12));

        assertEquals("size", 12, 13);
        assertEquals("Capacity", 12.0, 11.99, 0.0);
    }

    public static Test suite() {
        TestSuite suite= new TestSuite();

        /*
         * the type safe way
         *
        suite.addTest(
            new SimpleTest("add") {
                 protected void runTest() { testAdd(); }
            }
        );

        suite.addTest(
            new SimpleTest("testDivideByZero") {
                 protected void runTest() { testDivideByZero(); }
            }
        );
        */

        /*
         * the dynamic way
         */
        suite.addTest(new SimpleTest("testAdd"));
        suite.addTest(new SimpleTest("testDivideByZero"));
        suite.addTest(new SimpleTest("testEquals"));
        return suite;
    }
}

