package samples;

import test.framework.*;
import java.util.Vector;

/**
 * A sample test case, testing <code>java.util.Vector</code>.
 *
 */
public class VectorTest extends TestCase {
    protected Vector fEmpty;
    protected Vector fFull;

    public VectorTest(String name) {
        super(name);
    }

    protected void setUp() {
        fEmpty= new Vector();
        fFull= new Vector();
        fFull.addElement(new Integer(1));
        fFull.addElement(new Integer(2));
        fFull.addElement(new Integer(3));
    }

    public void testContains() {
        assert(fFull.contains(new Integer(1)));
        assert(!fEmpty.contains(new Integer(1)));
    }

    public void testElementAt() {
        Integer i= (Integer)fFull.elementAt(0);
        assert(i.intValue() == 1);

        try {
            Integer j= (Integer)fFull.elementAt(fFull.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }
        assert(false); // should raise an exception
    }

    public void testClone() {
        Vector clone= (Vector)fFull.clone();
        assert(clone.size() == fFull.size());
        assert(clone.contains(new Integer(1)));
    }

    public void testRemoveAll() {
        fFull.removeAllElements();
        fEmpty.removeAllElements();
        assert(fFull.isEmpty());
        assert(fEmpty.isEmpty());
    }

    public void testRemoveElement() {
        fFull.removeElement(new Integer(3));
        assert(!fFull.contains(new Integer(3)) );
    }

    public void testCapacity() {
        int size= fFull.size();
        for (int i= 0; i < 100; i++)
            fFull.addElement(new Integer(i));
        assert(fFull.size() == 100+size);
    }

    public static Test suite() {
        TestSuite suite= new TestSuite();

        suite.addTest(new VectorTest("testContains"));
        suite.addTest(new VectorTest("testClone"));
        suite.addTest(new VectorTest("testRemoveAll"));
        suite.addTest(new VectorTest("testRemoveElement"));
        suite.addTest(new VectorTest("testElementAt"));
        suite.addTest(new VectorTest("testCapacity"));
        return suite;
    }

    public static Test activeSuite() {
        TestSuite suite= new TestSuite();
        for (int i= 0; i < 100; i++) {
            suite.addTest(new ActiveTest(new VectorTest("testContains"))
            );
            suite.addTest(new ActiveTest(new VectorTest("testClone")));
            suite.addTest(new ActiveTest(new VectorTest("testRemoveAll")));
            suite.addTest(new ActiveTest(new VectorTest("testRemoveElement")));
            suite.addTest(new ActiveTest(new VectorTest("testElementAt")));
            suite.addTest(new ActiveTest(new VectorTest("testCapacity")));
        }
        return suite;
    }

    public static void main (String[] args) {
        test.textui.TestRunner.run (suite());
    }
}

