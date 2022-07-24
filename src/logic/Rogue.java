package logic;

import java.util.List;

public class Rogue extends Player{
    private final int cost;
    private int energy;
    private static final int MAX_ENERGY =100;
    private static final int RANGE =2;


    public Rogue(String name, int healthPool, int attackPoints, int defencePoints, int cost) {
        super(name, healthPool,attackPoints, defencePoints);
        this.cost = cost;
        this.energy = MAX_ENERGY;
    }
    public Rogue deterministicRogue( Rogue r) {
        Rogue w = new Rogue(r.name,r.getHealthPool(),r.attackPoints,r.defencePoints,r.cost);
        w.r = new NumberGenerator();
        return w;


    }

    @Override//heroic unit
    public void castAbility(List<Enemy> enemies) {
        if(energy>= cost){
            energy-=(cost+10);
            List<Enemy> enemiesInRange = enemiesInRange(enemies,RANGE);
            messageCallback.send(String.format("%s used special abilty FAN OF KNIVES!",getName()));

            if(!enemiesInRange.isEmpty()) {
                enemiesInRange.forEach((e) -> doAbilityDamage(e, attackPoints));
            }
        }else{
            //TODO: add red highlight to message
            messageCallback.send(String.format("%s tried to use special ability but failed because not enough energy. %d/%d (energy/cost)!",getName(),energy,cost));
        }

    }

    @Override// player
    public void onLevelUp() {
        super.onLevelUp();
        energy=100;
        attackPoints+=3*level;


    }
    public int getEnergy(){
        return this.energy;
    }

    @Override//unit
    public void onGameTick() {
        energy = Math.min(energy+10,MAX_ENERGY);

    }
    @Override
    public String describe() {
        return super.describe()+ String.format("\t\tenergy: %s/%s",energy,MAX_ENERGY);
    }
}
