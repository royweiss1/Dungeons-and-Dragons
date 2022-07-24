package logic;

import callBacks.EnemyDeathCallback;
import callBacks.MessageCallback;
import callBacks.UnitMoveCallback;

public abstract class Enemy extends Unit {
    protected int experienceValue;
    protected EnemyDeathCallback enemyDeathCallback;

    protected Enemy(char tile, String name, int healthPool, int attackPoints, int defencePoints, int experienceValue) {
        super(tile, name, healthPool, attackPoints, defencePoints);
        this.experienceValue = experienceValue;
    }


    public void initialize(Position position, MessageCallback messageCallback, EnemyDeathCallback enemyDeathCallback, UnitMoveCallback unitMoveCallback) {
        //super.initialize(position, messageCallback,unitMoveCallback);
        super.initialize(position);
        this.messageCallback = messageCallback;
        this.enemyDeathCallback = enemyDeathCallback;
        this.unitMoveCallback = unitMoveCallback;
    }

    @Override
    public void onDeath(Unit player) {
        enemyDeathCallback.call(this); // enemy.setDeathCallback((e) -> levelManger.remove(e))
        messageCallback.send(String.format("%s died in battle against %s. %s gained %d experience",getName(),player.getName(),player.getName(),experienceValue));

    }

    @Override
    public void visit(Player p) {
        battle(p); // enemy engages battle with player
        if(!p.isAlive()){
            p.onDeath(this);
        }

    }

    @Override
    //nothing happens.
    public void visit(Enemy e) {}


    @Override
    public void accept(Unit unit) {
        unit.visit(this);

    }
     public abstract void processStep(Player p);




    @Override
    public String describe() {
        return super.describe()+String.format("\t\tExperience Value: %d", experienceValue);
    }

    public int getExperienceValue() {
        return experienceValue;
    }

}
