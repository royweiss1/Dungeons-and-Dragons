package logic;

public class Mana extends Resource{
    public Mana(int pool,int amount) {
        super(pool);
        this.amount = amount;
    }

    @Override
    public void onLeveUp(int level) {
       // addToPool(25,level);
    }

}
