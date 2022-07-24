package logic;

import UI.CMDInputProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrapTest {
    private static Warrior w1;
    private static Trap e1;

    @BeforeEach
    void setup(){
        Warrior w = new Warrior("Arya Stark", 150, 40, 0, 2);
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
        e1 = new Trap('Q', "Queen's Trap", 250, 50, 10, 100, 3, 10);
        e1.initialize(Position.at(1, 0), (x) -> {
        }, (x) -> {
        }, (x, y) -> {
        });

    }

    @Test
    void onGameTick() {
        assertEquals(10,e1.getVisibilityTime());
        assertEquals(10,e1.getInvisibilityTime());
        e1.onGameTick();
        assertEquals(9,e1.getVisibilityTime());
        assertEquals(10,e1.getInvisibilityTime());
        e1.onGameTick();
        e1.onGameTick();
        e1.onGameTick();
        e1.onGameTick();
        e1.onGameTick();
        e1.onGameTick();
        e1.onGameTick();
        e1.onGameTick();
        e1.onGameTick();
        assertEquals(0,e1.getVisibilityTime());
        assertEquals(0,e1.getInvisibilityTime());



    }

    @Test
    void processStep() {
        e1.processStep(w1);
        assertEquals(100,w1.getHealthAmount());
        e1.processStep(w1);
        assertEquals(50,w1.getHealthAmount());
        e1.processStep(w1);
        assertEquals(0,w1.getHealthAmount());


    }

    @Test
    void testToString() {
        assertEquals("Q",e1.toString());
        e1.setVisible(false);
        assertEquals("",e1.toString());

    }
}