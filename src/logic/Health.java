package logic;

public class Health extends Resource{
    public Health(int pool) {
        super(pool);
    }

    @Override
    public void onLeveUp(int level) {
        pool+=10*level;
        amount = pool;
    }



}
