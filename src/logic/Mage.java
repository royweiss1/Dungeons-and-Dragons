package logic;

import java.util.ArrayList;
import java.util.List;

public class Mage extends Player{

    private Mana mana;
    private int cost;
    private int spellPower;
    private int hitCount;
    private int range;

    public Mage( String name, int healthPool, int attackPoints, int defencePoints, int manaPool, int cost, int spellPower, int hitCount, int range) {
        super( name,healthPool, attackPoints, defencePoints);
        this.mana = new Mana(manaPool,manaPool/4);
        this.cost = cost;
        this.spellPower = spellPower;
        this.hitCount = hitCount;
        this.range = range;
    }
    public Mage deterministicMage( Mage mage) {
        Mage w = new Mage(mage.name,mage.getHealthPool(),mage.attackPoints,mage.defencePoints,mage.mana.pool,mage.cost,mage.spellPower,mage.hitCount,mage.range);
        w.r = new NumberGenerator();
        return w;


    }

    @Override// heroic unit
    public void castAbility(List<Enemy> enemies) {
        if(mana.amount>= cost){
            messageCallback.send(String.format("%s used special ability: Blizzard!",getName()));
            int hits = 0;
            mana.reduceAmount(cost);
            List<Enemy> enemiesInRange = (enemiesInRange(enemies,range));
            while(hits<hitCount&&!enemiesInRange.isEmpty()){
                hits++;
                Enemy e = enemiesInRange.get(r.nextInt(enemiesInRange.size())); // choose random enemy from enemies in range.
                doAbilityDamage(e,spellPower); // cast ability, do damage to enemy, all enemy death logic is here
                if(!e.isAlive())
                    enemiesInRange = (enemiesInRange(enemies,range));
            }
        }else{
            messageCallback.send(String.format("%s tried to use special ability but failed because not enough mana. %d/%d (mana/cost)!",getName(),mana.getAmount(),cost));
        }

    }


    @Override// player
    public void onLevelUp() {
        super.onLevelUp();
        mana.addToPool(25*level);
        mana.addToAmount(mana.getPool()/4);
        spellPower+=10*level;

    }

    @Override//unit
    public void onGameTick() {
        mana.addToAmount(level);

    }

    @Override
    public String describe() {
        return super.describe()+ String.format("\t\tMana: %s \t\tSpell Power: %s", mana.getAmount()+"/"+mana.getPool(), spellPower);
    }
    protected int getMana(){
        return mana.amount;
    }
}
