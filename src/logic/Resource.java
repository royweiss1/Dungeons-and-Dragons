package logic;

public abstract class Resource {
    protected int pool;
    protected int amount;

    public int getPool() {
        return pool;
    }
    public int getAmount() {
        return amount;
    }
    protected Resource(int pool) {
        this.pool = pool;
        this.amount = pool;
    }

    public void reduceAmount(int toReduce){
        amount = Math.max(amount - toReduce, 0);

    }
    public void addToAmount(int toAdd){
        amount = Math.min(amount + toAdd, pool);

    }
    public void addToPool(int toAdd){
        pool+=toAdd;
    }
    abstract public void onLeveUp(int x);

}
