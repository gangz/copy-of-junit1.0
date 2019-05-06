package samples.money;

import java.util.Hashtable;
import java.util.Enumeration;

class MoneyBag implements IMoney {
    private Hashtable fMonies= new Hashtable(5);

    MoneyBag(Money m1, Money m2) {
        appendMoney(m1);
        appendMoney(m2);
    }

    MoneyBag(MoneyBag m1, MoneyBag m2) {
        appendBag(m1);
        appendBag(m2);
    }

    MoneyBag(Money m, MoneyBag bag) {
        appendMoney(m);
        appendBag(bag);
    }

    MoneyBag(Money bag[]) {
        for (int i= 0; i < bag.length; i++)
            appendMoney(bag[i]);
    }

    private MoneyBag() {
    }

    public IMoney add(IMoney m) {
        return m.addMoneyBag(this);
    }

    public IMoney multiply(int factor) {
        MoneyBag result= new MoneyBag();
        if (factor != 0) {
    	    for (Enumeration e= fMonies.elements(); e.hasMoreElements(); ) {
    	        Money m= (Money) e.nextElement();
    	        result.appendMoney((Money)m.multiply(factor));
            }
        }
        return result;
    }

    public IMoney negate() {
        MoneyBag result= new MoneyBag();
	    for (Enumeration e= fMonies.elements(); e.hasMoreElements(); ) {
	        Money m= (Money) e.nextElement();
	        result.appendMoney((Money)m.negate());
        }
        return result;
    }

    public IMoney subtract(IMoney m) {
        return add(m.negate());
    }

    public boolean isNull() {
        return fMonies.size() == 0;
    }

    public IMoney addMoney(Money m) {
        return (new MoneyBag(m, this)).simplify();
    }

    public IMoney addMoneyBag(MoneyBag s) {
        return (new MoneyBag(s, this)).simplify();
    }

    public boolean equals(Object anObject) {
        if (isNull())
            if (anObject instanceof IMoney)
                return ((IMoney)anObject).isNull();

        if (anObject instanceof MoneyBag) {
            MoneyBag aMoneyBag= (MoneyBag)anObject;
            if (aMoneyBag.fMonies.size() != fMonies.size())
                return false;

    	    for (Enumeration e= fMonies.elements(); e.hasMoreElements(); ) {
    	        Money m= (Money) e.nextElement();
                if (!aMoneyBag.contains(m))
                    return false;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        return fMonies.hashCode();
    }

	public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
    	for (Enumeration e= fMonies.elements(); e.hasMoreElements(); )
    	    buffer.append((Money) e.nextElement());
        buffer.append("}");
        return buffer.toString();
    }

    private IMoney simplify() {
        if (fMonies.size() == 1)
            return (IMoney)fMonies.elements().nextElement();
        return this;
    }

    private boolean contains(Money aMoney) {
        Money m= (Money)fMonies.get(aMoney.currency());
        return m.amount() == aMoney.amount();
    }

    private void appendMoney(Money aMoney) {
        IMoney m= (IMoney)fMonies.get(aMoney.currency());
        if (m != null)
            m= m.add(aMoney);
        else
            m= aMoney;
        if (!m.isNull())
            fMonies.put(aMoney.currency(), m);
        else
            fMonies.remove(aMoney.currency()); // remove empty money "bins"
    }

    private void appendBag(MoneyBag aBag) {
    	for (Enumeration e= aBag.fMonies.elements(); e.hasMoreElements(); )
            appendMoney((Money)e.nextElement());
    }
}