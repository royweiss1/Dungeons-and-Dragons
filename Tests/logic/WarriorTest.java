package logic;

import UI.CMDInputProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest {
    private static Warrior w1;
    private static Monster e1;
    private static Monster e2;
    private static Monster monster3;
    private static Monster monster4;
    private static Rogue r2;
    private static Tile empty;




    @BeforeEach
    void setUp() {
        Warrior w = new Warrior("Arya Stark", 150, 40, 2, 2);
        //rogue = new Mage("Melisandre", 100, 5, 1, 300, 30, 15, 5, 6);
        w.initialize((x) -> {
        }, () -> {
        }, new CMDInputProvider());
        w.setPosition(Position.at(0, 0));
        Warrior r = w.deterministicWarrior(w);
        r.initialize((x) -> {
        }, () -> {
        }, new CMDInputProvider());
        r.setPosition(Position.at(0, 0));
        w1 = r;
        Monster m1 = new Monster('a', "a", 100, 30, 10, 1, 1);
        m1 = m1.DeterministicMonster(m1);
        m1.initialize(Position.at(1, 0), (x) -> {
        }, (x) -> {
        }, (x, y) -> {
        });
        e1 = m1;
        Monster m2 = new Monster('a', "a", 500, 1, 5, 1, 1);
        m2 = m2.DeterministicMonster(m2);
        m2.initialize(Position.at(0, 1), (x) -> {
        }, (x) -> {
        }, (x, y) -> {
        });
        e2 = m2;
        empty = new Empty();
        empty.setPosition(Position.at(15, 15));
        monster3 = new Monster('a', "a", 100, 1, 5, 1, 1);
        monster3.initialize(Position.at(0, 1), (x) -> {
        }, (x) -> {
        }, (x, y) -> {
        });
    }

    @Test
    void castAbility() {
        List<Enemy> enemies = new LinkedList<>();
        enemies.add(e1);
        //enemies.add(e2);
        w1.castAbility(enemies);
        assertEquals(94,e1.getHealthAmount());
        w1.health.reduceAmount(50);
        w1.onGameTick();
        w1.onGameTick();
        w1.onGameTick();
        w1.castAbility(enemies);
        assertEquals(120,w1.getHealthAmount());

    }

    @Test
    void onLevelUp() {
        w1.onLevelUp();
        assertEquals(180,w1.getHealthAmount());
        assertEquals(180,w1.getHealthPool());
        assertEquals(52,w1.attackPoints);
        assertEquals(6,w1.defencePoints);


    }

    @Test
    void onGameTick() {
        List<Enemy> enemies = new LinkedList<>();
        enemies.add(e1);
        w1.castAbility(enemies);
        w1.onGameTick();
        assertEquals(2,w1.getCooldown());

    }

    @Test
    void describe() {
        assertEquals("Arya Stark\t\tHealth: 150/150\t\tAttack: 40\t\tDefense: 2\t\tLevel: 1 \t\tExperience: 0/50\t\tCooldown: 0/2",w1.describe());
    }
}