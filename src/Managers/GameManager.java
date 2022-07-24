package Managers;

import callBacks.MessageCallback;

import java.util.List;

public class GameManager {
    private List<LevelManager> levels;
    private boolean isDone;
    private int levelsCounter;
    private MessageCallback messageCallback;


    public GameManager(MessageCallback messageCallback){
        this.messageCallback = messageCallback;
    }
    public void initializeGameManager(List<LevelManager> lms){
        this.levels = lms;
        isDone = false;
        levelsCounter = levels.size();
    }

    public void initGame(){
        for(int i=0; i<levelsCounter&&!isDone; i++){
            levels.get(i).initLevel();
        }
        if(!isDone)
            messageCallback.send("YOU WON!!!!!!!!!!!!!!!");


    }

    public void playerDeath(){
        isDone = true;
    }


}
