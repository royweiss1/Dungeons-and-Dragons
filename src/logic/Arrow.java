package logic;

public class Arrow extends Resource{


    public Arrow(int level){
        super(Integer.MAX_VALUE);
        amount=10*level;
        // "Starting amount of arrows in quiver is equals to (10 × level)"
    }

    @Override
    public void onLeveUp(int newLevel) {
        addToAmount(10*newLevel);
    }

    public boolean mayAttack(){
        return amount>0;
    }

}
