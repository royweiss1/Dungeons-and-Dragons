package logic;

import UI.CMDInputProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {
    private static Unit warrior;
    private static Unit e1;
    private static Unit e2;
    private static Unit e3;
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
    void interact() {
        warrior.interact(e1);
        assertEquals(e1.getHealthAmount(),80);
        e1.interact(warrior);
        assertEquals(warrior.getHealthAmount(),285);

        Position enemyPos = e1.getPosition();
        Position emptyPos = Position.at(empty.getPosition().x,empty.getPosition().y);
        e1.interact(empty);
        assertEquals(e1.getPosition().x,emptyPos.x);
        assertEquals(e1.getPosition().y,emptyPos.y);

        Tile wall = new Wall();
        wall.setPosition(Position.at(40,40));
        warrior.interact(wall);
        assertEquals(warrior.getPosition().x,0);
        assertEquals(warrior.getPosition().x,0);

    }

    @Test
    void getName() {
        assertEquals(warrior.getName(),"Jon Snow");
    }

    @Test
    void describe() {
        assertEquals(warrior.describe(),"Jon Snow\t\tHealth: 300\t\tAttack: 30\t\tDefense: 15\t\tLevel: 1 \t\tExperience: 0/50\t\tCooldown: 0/3");

    }

    @Test
    void switchPosition() {
        Position emptyPos = Position.at(empty.getPosition().x,empty.getPosition().y);
        warrior.switchPosition(empty);
        assertEquals(warrior.getPosition().x,emptyPos.x);
        assertEquals(warrior.getPosition().y,emptyPos.y);

    }

    @Test
    void isAlive() {
        assertTrue(warrior.isAlive());
        warrior.health.reduceAmount(500);
        assertFalse(warrior.isAlive());
    }

    @Test
    void battle() {
        warrior.battle(e1);
        assertEquals(e1.getHealthAmount(),80);
        e1.battle(warrior);
        assertEquals(warrior.getHealthAmount(),285);
    }

    @Test
    void onDeath() {
    }
}