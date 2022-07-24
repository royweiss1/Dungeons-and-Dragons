package Managers;

import logic.*;

import java.util.Iterator;
import java.util.List;

public class LevelManager {
    private GameBoard gameBoard;
    private List<Enemy> enemyList;
    private Player player;
    private Position initPlayerPosition;

    public LevelManager() {

    }

    public void setInitPlayerPosition(Position position){
        this.initPlayerPosition = position;
    }
    public void initLevelManager(GameBoard gameBoard, List<Enemy> enemies){
        this.gameBoard = gameBoard;
        this.enemyList =enemies;
    }
    public void setPlayer(Player p){
        this.player = p;
    }
    public void onEnemyDeath(Enemy e){
        gameBoard.remove(e);
        enemyList.remove(e);

    }

    public void onUnitMove(Unit u, Action action){
        if(action == Action.NONE)
            return;
        Position moveTo = u.getPosition().translate(action);
        if(gameBoard.validatePos(moveTo.getX(),moveTo.getY())){
            Tile t = gameBoard.get(moveTo);
            u.interact(t);
        }


    }
    public void onTick(){
        player.onGameTick();
        enemyList.forEach(Unit::onGameTick);
    }
    public void onPlayerAbility(){
        player.castAbility(enemyList);
    }
    //on plyaer death we will call GameManger.
    public void initLevel(){
        player.initializePlayerForLevel(initPlayerPosition,this::onUnitMove,this::onPlayerAbility);
        gameBoard.printBoard();
        player.printUnit();
        while(!enemyList.isEmpty()& player.isAlive()){
            player.processStep();
            Iterator<Enemy> iterator = enemyList.iterator();
            while(iterator.hasNext()&& player.isAlive()) {
                iterator.next().processStep(player);
            }
            onTick();
            gameBoard.printBoard();
            player.printUnit();
        }

    }

    public Player getPlayer() {
        return player;
    }
}
