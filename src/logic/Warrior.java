package logic;

import java.util.List;
import java.util.Random;

public class Warrior extends Player{
    private final static int RANGE =3;
    private final int cooldown;
    private int remainingCooldown;

    public Warrior( String name, int healthPool, int attackPoints, int defencePoints, int cooldown) {
        super( name, healthPool,attackPoints, defencePoints);
        this.cooldown = cooldown;
        this.remainingCooldown =0;
    }
    public Warrior deterministicWarrior( Warrior warrior) {
        Warrior w = new Warrior(warrior.name,warrior.getHealthPool(),warrior.attackPoints,warrior.defencePoints,warrior.cooldown);
        w.r = new NumberGenerator();
        return w;


    }

    @Override // heroic unit
    public void castAbility(List<Enemy> enemyList) {
        if(remainingCooldown == 0) {
            messageCallback.send(String.format("%s used special ability: Avengers Shield!", getName()));
            remainingCooldown = cooldown+1;
            List<Enemy> enemiesInRange = enemiesInRange(enemyList, RANGE);
            if (!enemiesInRange.isEmpty()) {
                Enemy e = enemiesInRange.get(r.nextInt(enemiesInRange.size())); // choose random enemy from enemies in range.
                doAbilityDamage(e, (int) (0.1 * getHealthPool())); // cast ability, do damage to enemy, all enemy death logic is here
            }
            health.addToAmount(10 * defencePoints); // healing the warrior
            messageCallback.send(String.format("%s healed himself for %d health", getName(),10*defencePoints));

        }else{
            messageCallback.send(String.format("%s tried to use special ability but failed because  ability is on cooldown. %d game tick remaining!",getName(),remainingCooldown));
        }
    }




    @Override//player
    public void onLevelUp(){
        super.onLevelUp();
        // add logic
        remainingCooldown = 0;
        health.addToPool(5*level);
        health.addToAmount(5*level);
        attackPoints+=2*level;
        defencePoints+=level;
    }

    @Override//unit
    public void onGameTick() {
        remainingCooldown = Math.max(0,remainingCooldown-1);
    }

    @Override
    public String describe() {
        return super.describe() + String.format("\t\tCooldown: %s", remainingCooldown+"/"+cooldown);
    }
    public int getCooldown(){
        return cooldown;
    }

}
