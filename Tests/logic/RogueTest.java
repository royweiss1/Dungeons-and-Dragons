package logic;

import UI.CMDInputProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RogueTest {
    private static Rogue r1;
    private static Monster e1;
    private static Monster e2;
    private static Monster monster3;
    private static Monster monster4;
    private static Rogue r2;
    private static Tile empty;




    @BeforeEach
    void setUp() {
        Rogue rogue = new Rogue("Arya Stark", 150, 40, 2, 20);
        //rogue = new Mage("Melisandre", 100, 5, 1, 300, 30, 15, 5, 6);
        rogue.initialize((x)->{},()->{},new CMDInputProvider());
        rogue.setPosition(Position.at(0,0));
        Rogue r = rogue.deterministicRogue(rogue);
        r.initialize((x)->{},()->{},new CMDInputProvider());
        r.setPosition(Position.at(0,0));
        r1 =  r;
        Monster  m1 = new Monster('a',"a",100,30,10,1,1);
        m1 = m1.DeterministicMonster(m1);
        m1.initialize(Position.at(1,0),(x)->{},(x)->{},(x,y)->{});
        e1 = m1;
        Monster  m2 = new Monster('a',"a",500,1,5,1,1);
        m2 = m2.DeterministicMonster(m2);
        m2.initialize(Position.at(0,1),(x)->{},(x)->{},(x,y)->{});
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
        enemies.add(e2);
        enemies.add(monster3);
        r1.castAbility(enemies);
        assertEquals(70, e1.getHealthAmount()); // hit
        assertEquals(465, e2.getHealthAmount()); // hit
        assertEquals(100, monster3.getHealthAmount()); // no hit

    }
    @Test
    void onLevelUp() {
        r1.onLevelUp();
        assertEquals(54,r1.attackPoints);
        assertEquals(2,r1.level);
        assertEquals(0,r1.experience);



    }

    @Test
    void onGameTick() {
        List<Enemy> enemies = new LinkedList<>();
        enemies.add(e1);
        r1.castAbility(enemies);
        r1.onGameTick();
        r1.castAbility(enemies);
        r1.onGameTick();
        assertEquals(60,r1.getEnergy());

    }

    @Test
    void describe() {
        assertEquals("Arya Stark\t\tHealth: 150/150\t\tAttack: 40\t\tDefense: 2\t\tLevel: 1 \t\tExperience: 0/50\t\tenergy: 100/100",r1.describe());
    }
}