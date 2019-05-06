package samples.money;

class Money implements IMoney {

    private int fAmount;
    private String fCurrency;

    public Money(int amount, String currency) {
        fAmount= amount;
        fCurrency= currency;
    }

    public IMoney add(IMoney m) {
        return m.addMoney(this);
    }

    public IMoney negate() {
        return new Money(-amount(), currency());
    }

    public IMoney multiply(int factor) {
        return new Money(amount()*factor, currency());
    }

    public IMoney subtract(IMoney m) {
        return add(m.negate());
    }

    public boolean isNull() {
        return amount() == 0;
    }

    public IMoney addMoney(Money m) {
        if (m.currency().equals(currency()) )
            return new Money(amount()+m.amount(), currency());
        return new MoneyBag(this, m);
    }

    public IMoney addMoneyBag(MoneyBag s) {
        return s.addMoney(this);
    }

    public int amount() {
        return fAmount;
    }

    public String currency() {
        return fCurrency;
    }

    public boolean equals(Object anObject) {
        if (isNull())
            if (anObject instanceof IMoney)
                return ((IMoney)anObject).isNull();

        if (anObject instanceof Money) {
            Money aMoney= (Money)anObject;
            return aMoney.currency().equals(currency())
                             && amount() == aMoney.amount();
        }
        return false;
    }

    public int hashCode() {
        return fCurrency.hashCode()+fAmount;
    }

	public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("["+amount()+" "+currency()+"]");
        return buffer.toString();
    }
}
