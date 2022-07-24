package logic;

import callBacks.MessageCallback;
import callBacks.UnitMoveCallback;

import java.util.Random;

public abstract class Unit extends Tile{
    protected AbstractGenerator r;
	protected String name;
    protected Health health;
    protected int attackPoints;
    protected int defencePoints;
    protected MessageCallback messageCallback;
    protected UnitMoveCallback unitMoveCallback;

    protected Unit(char tile, String name, int healthPool, int attackPoints, int defencePoints) {
        super(tile);
        this.name = name;
        this.health = new Health(healthPool);
        this.attackPoints = attackPoints;
        this.defencePoints = defencePoints;
        r = new RandomGenerator();
    }



/*
    protected void initialize(Position position, MessageCallback messageCallback, UnitMoveCallback unitMoveCallback){
        this.position = position;
        this.messageCallback = messageCallback;
        this.unitMoveCallback = unitMoveCallback;
    }
    */


    protected int attack(){
        int toAttack = r.nextInt(attackPoints);
        messageCallback.send(String.format("%s rolled %d attack points",getName(),toAttack));
        return toAttack;

    }

    public int defend(){
        int toDef = r.nextInt(defencePoints);
        messageCallback.send(String.format("%s rolled %d defence points",getName(),toDef));
        return toDef;

    }
    // This unit attempts to interact with another tile.
    public void interact(Tile tile){
        tile.accept(this);
    }

    public void visit(Empty e){
        switchPosition(e);
    }
    public void visit(Wall w){
        // doesn't do anything
    }

    public String getName() {
        return name;
    }


    // Combat against another unit.
    protected void battle(Unit u){
        messageCallback.send(String.format("%s engaged in combat with %s.",getName(),u.getName()));
        messageCallback.send(describe());
        u.messageCallback.send(u.describe());
        int toReduce = Math.max(attack()-u.defend(),0);
        u.health.reduceAmount(toReduce);
        messageCallback.send(String.format("%s dealt %d damage to %s !",getName(),toReduce,u.getName()));


    }


    public String describe() {
        return String.format("%s\t\tHealth: %s/%s\t\tAttack: %d\t\tDefense: %d", getName(), getHealthAmount(),getHealthPool(), getAttackPoints(),getDefencePoints());
    }
    public void printUnit(){
        messageCallback.send(describe());

    }





    public int getHealthPool() {
        return health.getPool();
    }


    public int getHealthAmount() {
        return health.getAmount();
    }


    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public int getDefencePoints() {
        return defencePoints;
    }

    public void setDefencePoints(int defencePoints) {
        this.defencePoints = defencePoints;
    }
    protected void switchPosition(Tile t){
        Position otherPos = t.getPosition();
        t.setPosition(this.getPosition());
        this.setPosition(otherPos);
    }
    public boolean isAlive(){
        return getHealthAmount()>0;
    }


    // ********* ABSTRACT METHODS *******

    // Should be automatically called once the unit finishes its turn
   // public abstract void processStep();
    public abstract void onGameTick();

    // What happens when the unit dies
    public abstract void onDeath(Unit unit);

    public abstract void visit(Player p);

    public abstract void visit(Enemy e);



}
