package samples.money;

interface IMoney {
    // comparing
    public abstract boolean equals(Object anObject);
    public abstract int hashCode();

    // arithmetic
    public abstract IMoney add(IMoney m);
    public abstract IMoney subtract(IMoney m);
    public abstract IMoney negate();
    public abstract IMoney multiply(int factor);

    public abstract boolean isNull();

    // helpers
    IMoney addMoney(Money m);
    IMoney addMoneyBag(MoneyBag s);
}