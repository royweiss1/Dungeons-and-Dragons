package logic;

import UI.ConsoleColors;
import UI.InputProvider;
import callBacks.MessageCallback;
import callBacks.PlayerCastAbilityCallback;
import callBacks.PlayerDeathCallback;
import callBacks.UnitMoveCallback;

import java.util.List;

public abstract class Player extends Unit implements HeroicUnit{
    protected int experience;
    protected int level;
    protected PlayerDeathCallback playerDeathCallback;
    protected InputProvider inputProvider;
    protected PlayerCastAbilityCallback playerCastAbilityCallback;

    protected Player(String name, int healthPool, int attackPoints, int defencePoints) {
        super('@', name, healthPool, attackPoints, defencePoints);
        this.experience = 0;
        this.level = 1;
    }



    public void initialize(MessageCallback messageCallback, PlayerDeathCallback playerDeathCallback, InputProvider ip) {
        this.messageCallback = messageCallback;
        this.playerDeathCallback = playerDeathCallback;
        this.inputProvider = ip;
    }
    public void initializePlayerForLevel(Position position,UnitMoveCallback unitMoveCallback,PlayerCastAbilityCallback playerCastAbilityCallback){
        super.initialize(position);
        this.unitMoveCallback = unitMoveCallback;
        this.playerCastAbilityCallback = playerCastAbilityCallback;
    }
    protected Action getInput(){
        return inputProvider.getInputQuery().getInput();
    }



    protected int maxExp(){
        return level*50;
    }
    @Override
    public String describe() {
        return super.describe()+String.format("\t\tLevel: %d \t\tExperience: %s", level, experience+"/"+maxExp());
    }



    //@Override
    public void processStep() {
        Action action = getInput();
        if(action == Action.ABILITY){
            playerCastAbilityCallback.call(); // ()->levelMAnager.playerAbility()
        }else{
            unitMoveCallback.call(this,action); //unitMovecallback = (unit,action)->levelM.onStep(unit,action);
        }

    }

    @Override
    public void onDeath(Unit enemy) {
        messageCallback.send(String.format("%s died in battle against %s.",getName(),enemy.getName()));
        messageCallback.send("Game Over.");
        playerDeathCallback.call();


    }
    @Override
    public void accept(Unit unit) {
        unit.visit(this);

    }

    @Override
    public void visit(Player p) {
       // doesn't do anything

    }
    @Override
    public void visit(Enemy e) {
        battle(e); // player engages battle with enemy
        if(!e.isAlive()){
            switchPosition(e);
            onKill(e);

        }

    }
    protected void onKill(Enemy e){
        e.onDeath(this);
        addExperience(e.getExperienceValue());
    }
    protected void addExperience(int toAdd){
        int newExp = Math.min(maxExp(),experience+toAdd);
        if(newExp == maxExp()){
            onLevelUp();
        }else{
            experience=newExp;
        }

    }
    public void onLevelUp(){
        experience=0;
        level++;
        health.onLeveUp(level);
        attackPoints+=4*level;
        defencePoints+=level;
        messageCallback.send(String.format("%s leveled up to level %d! ",getName(),level));
    }
    protected List<Enemy> enemiesInRange(List<Enemy> enemies, int dist){
        return enemies.stream().filter((x)->x.position.range(this.position)<=dist).toList();
        // filter enemies by the distance between them and the player, if distance<=range, we add the enemy to list.
        // lambda expression in filter gets a Predicate - see Predicate interface.
    }
    protected void doAbilityDamage(Enemy e,int abilityDamage){
        int toReduce = Math.max(abilityDamage-e.defend(),0);
        e.health.reduceAmount(toReduce);
        messageCallback.send(String.format("%s hit %s for %d special ability damage",getName(),e.getName(),toReduce));
        if(!e.isAlive())
            onKill(e);
    }
    //TODO: check if color works
    @Override
    public String toString() {
        return isAlive()? super.toString():"X";
    }


    //public abstract void onGameTick(); // should be implemented separately



}
