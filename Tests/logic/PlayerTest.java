package logic;

import UI.CMDInputProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private static Player warrior;
    private static Monster e1;
    private static Monster e2;
    private static Monster e3;
    private static Tile empty;




    @BeforeEach
    void setUp() {
        Warrior w = new Warrior("Jon Snow", 300, 30, 15, 3);
        w = w.deterministicWarrior(w);
        w.initialize((x)->{},()->{},new CMDInputProvider());
        w.setPosition(Position.at(0,0));
        warrior =  w;
        Monster  m1 = new Monster('a',"a",100,30,10,1,1);
        m1 = m1.DeterministicMonster(m1);
        m1.initialize(Position.at(4,4),(x)->{},(x)->{},(x,y)->{});
        e1 = m1;
        Monster  m2 = new Monster('a',"a",100,1,5,1,1);
        m2 = m2.DeterministicMonster(m2);
        m2.initialize(Position.at(3,3),(x)->{},(x)->{},(x,y)->{});
        e2 = m2;
        empty = new Empty();
        empty.setPosition(Position.at(15,15));


    }

    @Test
    void maxExp() {
        assertEquals(warrior.maxExp(),50);
        warrior.onLevelUp();
        assertEquals(warrior.maxExp(),100);


    }

    @Test
    void addExperience() {
        warrior.addExperience(100);
        assertEquals(warrior.experience,0);
        assertEquals(warrior.level,2);
    }

    @Test
    void onLevelUp() {
        warrior.onLevelUp();
        assertEquals(warrior.experience,0);
        assertEquals(warrior.level,2);
        assertEquals(warrior.getHealthAmount(),warrior.getHealthPool());
        assertEquals(warrior.getHealthPool(),330);
        assertEquals(warrior.attackPoints,42);
        assertEquals(warrior.defencePoints,19);

    }

    @Test
    void enemiesInRange() {
        List<Enemy> e = new ArrayList<>();
        e.add(e1);
        e.add(e2);
        e3 = new Monster('a',"test",1,1,1,1,1);
        e3.setPosition(Position.at(6,6));
        e.add(e3);
        assertEquals(warrior.enemiesInRange(e,0).size(),0);
        assertEquals(warrior.enemiesInRange(e,1).size(),0);
        assertEquals(warrior.enemiesInRange(e,2).size(),0);
        assertEquals(warrior.enemiesInRange(e,3).size(),0);
        assertEquals(warrior.enemiesInRange(e,5).size(),1);
        assertEquals(warrior.enemiesInRange(e,10).size(),3);
    }

    @Test
    void doAbilityDamage() {
        warrior.doAbilityDamage(e1,50);
        assertEquals(e1.getHealthAmount(),60);
        e1.setDefencePoints(50);
        warrior.doAbilityDamage(e1,50);
        assertEquals(e1.getHealthAmount(),60);
        warrior.doAbilityDamage(e1,200);
        assertEquals(e1.getHealthAmount(),0);




    }
}