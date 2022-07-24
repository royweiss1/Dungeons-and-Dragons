package logic;

import UI.CMDInputProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MageTest {
    private static Mage mage1;
    private static Monster e1;
    private static Monster e2;
    private static Monster monster3;
    private static Monster monster4;
    private static Mage mage2;
    private static Tile empty;




    @BeforeEach
    void setUp() {
        Mage mage = new Mage("Melisandre", 100, 5, 1, 300, 30, 15, 5, 6);
        mage2 = new Mage("Melisandre", 100, 5, 1, 300, 30, 15, 5, 6);
        mage2.initialize((x)->{},()->{},new CMDInputProvider());
        mage2.setPosition(Position.at(0,0));
        Mage m = mage.deterministicMage(mage);
        m.initialize((x)->{},()->{},new CMDInputProvider());
        m.setPosition(Position.at(0,0));
        mage1 =  m;
        Monster  m1 = new Monster('a',"a",100,30,10,1,1);
        m1 = m1.DeterministicMonster(m1);
        m1.initialize(Position.at(4,4),(x)->{},(x)->{},(x,y)->{});
        e1 = m1;
        Monster  m2 = new Monster('a',"a",500,1,5,1,1);
        m2 = m2.DeterministicMonster(m2);
        m2.initialize(Position.at(3,3),(x)->{},(x)->{},(x,y)->{});
        e2 = m2;
        empty = new Empty();
        empty.setPosition(Position.at(15,15));
        monster3 = new Monster('a',"a",100,1,5,1,1);
        monster3.initialize(Position.at(3,3),(x)->{},(x)->{},(x,y)->{});

        monster4 = new Monster('a',"a",100,1,5,1,1);
        monster4.initialize(Position.at(10,10),(x)->{},(x)->{},(x,y)->{});



    }

    @Test
    void castAbility() {
        List<Enemy> enemies = new LinkedList<>();
        enemies.add(e1);
        mage2.castAbility(enemies);
        assertEquals(75,e1.getHealthAmount());
        mage2.onLevelUp();
        mage2.onLevelUp();
        mage2.castAbility(enemies);
        assertEquals(0,e1.getHealthAmount());
        enemies.remove(e1);
        enemies.add(e2);

        mage2.castAbility(enemies);
        assertEquals(200,e2.getHealthAmount());






    }

    @Test
    void onLevelUp() {
        mage1.onLevelUp();
        assertEquals(162,mage1.getMana());
        mage1.onLevelUp();
        assertEquals(268,mage1.getMana());
    }

    @Test
    void onGameTick() {
        mage1.onGameTick();

        assertEquals(76,mage1.getMana());
        mage1.onGameTick();

        assertEquals(77,mage1.getMana());
    }

    @Test
    void describe() {
        assertEquals("Melisandre\t\tHealth: 100/100\t\tAttack: 5\t\tDefense: 1\t\tLevel: 1 \t\tExperience: 0/50\t\tMana: 75/300 \t\tSpell Power: 15",mage1.describe());
    }
}