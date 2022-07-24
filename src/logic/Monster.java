package logic;

import UI.ConsoleColors;

import java.util.ArrayList;
import java.util.List;

public class Monster extends Enemy{
    private int visionRange;
    protected static List<Action> MOVE_LIST = new ArrayList<Action>(){{
        add(Action.RIGHT);
        add(Action.LEFT);
        add(Action.NONE);
        add(Action.UP);
        add(Action.DOWN);



    }};

    public Monster(char tile, String name, int healthPool, int attackPoints, int defencePoints, int experienceValue,int visionRange) {
        super(tile, name, healthPool, attackPoints, defencePoints, experienceValue);
        this.visionRange = visionRange;
    }
    public Monster DeterministicMonster(Monster monster) {
        Monster m = new Monster(monster.tile,monster.name,monster.getHealthPool(),monster.attackPoints,monster.defencePoints,monster.experienceValue,monster.visionRange);
        m.r = new NumberGenerator();
        return m;


    }

    public void processStep(Player p) {
        unitMoveCallback.call(this, monsterMove(p));
    }
    private Action monsterMove(Player p){
        if(position.range(p.getPosition())<visionRange){
            int dx = this.position.getX() - p.getPosition().getX();
            int dy = this.position.getY() - p.getPosition().getY();
            if(Math.abs(dx) >Math.abs(dy))
                if(dx>0)
                    return Action.LEFT;
                else
                    return Action.RIGHT;
            else
                if(dy >0)
                    return Action.UP;
                else
                    return Action.DOWN;


        }
        return MOVE_LIST.get(r.nextInt(MOVE_LIST.size()));

    }
    protected Action determinsticMonsterMove(Player p, int dx, int dy){ // for tests
        if(position.range(p.getPosition())<visionRange){
            if(Math.abs(dx) >Math.abs(dy))
                if(dx>0)
                    return Action.LEFT;
                else
                    return Action.RIGHT;
            else
            if(dy >0)
                return Action.UP;
            else
                return Action.DOWN;


        }
        return MOVE_LIST.get(r.nextInt(MOVE_LIST.size()));

    }


    @Override
    public void onGameTick() {
    }

    @Override
    public String describe() {
        return super.describe()+String.format("\t\tVision Range: %d",visionRange);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
