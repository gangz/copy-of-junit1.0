package samples.money;

import test.framework.*;

import java.util.Vector;

public class MoneyTest extends TestCase {
    private Money f12CHF;
    private Money f14CHF;
    private Money f7USD;
    private Money f21USD;

    private MoneyBag fMB1;
    private MoneyBag fMB2;

    public MoneyTest(String name) {
        super(name);
    }

    protected void setUp() {
        f12CHF= new Money(12, "CHF");
        f14CHF= new Money(14, "CHF");
        f7USD=  new Money( 7, "USD");
        f21USD= new Money(21, "USD");

        fMB1= new MoneyBag(f12CHF, f7USD);
        fMB2= new MoneyBag(f14CHF, f21USD);
    }

    public void testSimpleAdd() {
        // [12 CHF] + [14 CHF] == [26 CHF]
        Money expected= new Money(26, "CHF");
        assertEquals(expected, f12CHF.add(f14CHF));
    }

    public void testMixedSimpleAdd() {
        // [12 CHF] + [7 USD] == {[12 CHF][7 USD]}
        Money bag[]= { f12CHF, f7USD };
        MoneyBag expected= new MoneyBag(bag);
        assertEquals(expected, f12CHF.add(f7USD));
    }

    public void testBagSimpleAdd() {
        // {[12 CHF][7 USD]} + [14 CHF] == {[26 CHF][7 USD]}
        Money bag[]= { new Money(26, "CHF"), new Money(7, "USD") };
        MoneyBag expected= new MoneyBag(bag);
        assertEquals(expected, fMB1.add(f14CHF));
    }

    public void testSimpleBagAdd() {
        // [14 CHF] + {[12 CHF][7 USD]} == {[26 CHF][7 USD]}
        Money bag[]= { new Money(26, "CHF"), new Money(7, "USD") };
        MoneyBag expected= new MoneyBag(bag);
        assertEquals(expected, f14CHF.add(fMB1));
    }

    public void testBagSumAdd() {
        // {[12 CHF][7 USD]} + {[14 CHF][21 USD]} == {[26 CHF][28 USD]}
        Money bag[]= { new Money(26, "CHF"), new Money(28, "USD") };
        MoneyBag expected= new MoneyBag(bag);
        assertEquals(expected, fMB1.add(fMB2));
    }

    public void testNormalize() {
        Money bag[]= { new Money(26, "CHF"), new Money(28, "CHF"), new Money(6, "CHF") };
        MoneyBag moneyBag= new MoneyBag(bag);
        Money expected[]= { new Money(60, "CHF") };
        // note: the expected is still a MoneyBag
        MoneyBag expectedBag= new MoneyBag(expected);
        assertEquals(expectedBag, moneyBag);
    }

    public void testNormalize2() {
        // {[12 CHF][7 USD]} - [12 CHF] == [7 USD]
        Money expected= new Money(7, "USD");
        assertEquals(expected, fMB1.subtract(f12CHF));
    }

    public void testNormalize3() {
        // {[12 CHF][7 USD]} - {[12 CHF][3 USD]} == [4 USD]
        Money s1[]= { new Money(12, "CHF"), new Money(3, "USD") };
        MoneyBag ms1= new MoneyBag(s1);
        Money expected= new Money(4, "USD");
        assertEquals(expected, fMB1.subtract(ms1));
    }

    public void testNormalize4() {
        // [12 CHF] - {[12 CHF][3 USD]} == [-3 USD]
        Money s1[]= { new Money(12, "CHF"), new Money(3, "USD") };
        MoneyBag ms1= new MoneyBag(s1);
        Money expected= new Money(-3, "USD");
        assertEquals(expected, f12CHF.subtract(ms1));
    }

    public void testSimpleNegate() {
        // [14 CHF] negate == [-14 CHF]
        Money expected= new Money(-14, "CHF");
        assertEquals(expected, f14CHF.negate());
    }

    public void testBagNegate() {
        // {[12 CHF][7 USD]} negate == {[-12 CHF][-7 USD]}
        Money bag[]= { new Money(-12, "CHF"), new Money(-7, "USD") };
        MoneyBag expected= new MoneyBag(bag);
        assertEquals(expected, fMB1.negate());
    }

    public void testSimpleSubtract() {
        // [14 CHF] - [12 CHF] == [2 CHF]
        Money expected= new Money(2, "CHF");
        assertEquals(expected, f14CHF.subtract(f12CHF));
    }

    public void testBagSubtract() {
        // {[12 CHF][7 USD]} - {[14 CHF][21 USD] == {[-2 CHF][-14 USD]}
        Money bag[]= { new Money(-2, "CHF"), new Money(-14, "USD") };
        MoneyBag expected= new MoneyBag(bag);
        assertEquals(expected, fMB1.subtract(fMB2));
    }

    public void testSimpleMultiply() {
        // [14 CHF] *2 == [28 CHF]
        Money expected= new Money(28, "CHF");
        assertEquals(expected, f14CHF.multiply(2));
    }

    public void testBagMultiply() {
        // {[12 CHF][7 USD]} *2 == {[24 CHF][14 USD]}
        Money bag[]= { new Money(24, "CHF"), new Money(14, "USD") };
        MoneyBag expected= new MoneyBag(bag);
        assertEquals(expected, fMB1.multiply(2));
        assert(fMB1.multiply(0).isNull());
    }

    public void testIsNull() {
        assert(fMB1.subtract(fMB1).isNull());
    }

    public void testMoneyEquals() {
        assert(!f12CHF.equals(null));

        assertEquals(f12CHF, f12CHF);
        assertEquals(f12CHF, new Money(12, "CHF"));
        assert(!f12CHF.equals(f14CHF));
    }

    public void testMoneyBagEquals() {
        assert(!fMB1.equals(null));

        assertEquals(fMB1, fMB1);
        assert(!fMB1.equals(f12CHF));
        assert(!f12CHF.equals(fMB1));
        assert(!fMB1.equals(fMB2));
    }

    public static Test suite() {
        TestSuite suite= new TestSuite();

        suite.addTest(new MoneyTest("testMoneyEquals"));
        suite.addTest(new MoneyTest("testMoneyBagEquals"));
        suite.addTest(new MoneyTest("testNormalize"));
        suite.addTest(new MoneyTest("testNormalize2"));
        suite.addTest(new MoneyTest("testNormalize3"));
        suite.addTest(new MoneyTest("testNormalize4"));
        suite.addTest(new MoneyTest("testSimpleAdd"));
        suite.addTest(new MoneyTest("testMixedSimpleAdd"));
        suite.addTest(new MoneyTest("testBagSimpleAdd"));
        suite.addTest(new MoneyTest("testSimpleBagAdd"));
        suite.addTest(new MoneyTest("testBagSumAdd"));
        suite.addTest(new MoneyTest("testSimpleNegate"));
        suite.addTest(new MoneyTest("testBagNegate"));
        suite.addTest(new MoneyTest("testSimpleSubtract"));
        suite.addTest(new MoneyTest("testBagSubtract"));
        suite.addTest(new MoneyTest("testSimpleMultiply"));
        suite.addTest(new MoneyTest("testBagMultiply"));
        suite.addTest(new MoneyTest("testIsNull"));
        return suite;
    }

    public static void main(String args[]) {
        test.textui.TestRunner.run(suite());
    }
}

