package logic;

import UI.ConsoleColors;

public class Trap extends Enemy{
    private int visibilityTime;
    private int invisibilityTime;
    private int ticksCount;
    private boolean visible;

    public Trap(char tile, String name, int healthPool, int attackPoints, int defencePoints, int experienceValue, int visibilityTime, int invisibilityTime) {
        super(tile, name, healthPool, attackPoints, defencePoints, experienceValue);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.ticksCount = 0;
        this.visible= true;
    }

    @Override//unit
    public void onGameTick() {
        visible = ticksCount<visibilityTime;
        if(ticksCount == visibilityTime+invisibilityTime)
            ticksCount=0;
        else
            ticksCount++;


    }

    public void processStep(Player p) {
        if(p.getPosition().range(position)<2) {
            battle(p); // trap engages battle with player.
            if(!p.isAlive()){
                p.onDeath(this);
            }
        }

    }


    @Override
    public String toString() {
        return visible?super.toString():".";

    }

    public int getVisibilityTime() {
        return visibilityTime;
    }

    public int getInvisibilityTime() {
        return invisibilityTime;
    }

    protected void setVisible(boolean visible) { // for tests
        this.visible = visible;
    }
}
