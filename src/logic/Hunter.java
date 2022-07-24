package logic;

import java.util.List;

public class Hunter extends Player implements HeroicUnit{

    private int tickCount=0;
    private int range;
    private Arrow arrow;

    public Hunter(String name, int healthPool, int attackPoints, int defencePoints, int shootingRange){
        super(name, healthPool, attackPoints, defencePoints);
        range=shootingRange;
        arrow=new Arrow(level);
    }

    @Override
    public void onLevelUp() {
        super.onLevelUp();
        arrow.onLeveUp(level);
        attackPoints+=level*2;
        defencePoints+=level;
        range=level;
        //--> not sure because player already times 4
        //same with defence
        //probably need to add those.

    }

    @Override
    public void onGameTick() {
        if (tickCount==10){
            arrow.addToAmount(level);
            tickCount=0;
        }
        else
            tickCount++;
    }

    @Override
    public void castAbility(List<Enemy> enemyList) {
        if (arrow.mayAttack()) {
            Enemy closest=findNearestEnemyInRange(enemyList);
            if (closest!=null) {
                arrow.reduceAmount(1);
                doAbilityDamage(closest, attackPoints);
                //shoot arrow on closest enemy on range. attack points. enemy will try to defend
            }
        }
    }


    private Enemy findNearestEnemyInRange(List <Enemy> enemyList){
        List<Enemy> inRange=enemiesInRange(enemyList,range);
        if (inRange.isEmpty())
            return null;
        Enemy closest=inRange.get(0);
        for (Enemy e: inRange){
            if(e.position.range(position)<position.range(closest.position))
                closest=e;
        }
        return closest;
    }

    @Override
    public String describe() {
        return super.describe()+ String.format("\t\t arrows: %s",arrow.amount);
    }

}
