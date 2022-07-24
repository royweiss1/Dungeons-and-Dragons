package logic;

import UI.CMDInputProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest {
    private static Warrior w1;
    private static Monster e1;

    @BeforeEach
    void setup(){
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
        Monster m1 = new Monster('a', "a", 100, 30, 10, 1, 5);
        m1 = m1.DeterministicMonster(m1);
        m1.initialize(Position.at(1, 0), (x) -> {
        }, (x) -> {
        }, (x, y) -> {
        });
        e1 = m1;
    }



    @Test
    void determinsticMonsterMove() {
        int dx =2;
        int dy =2;
        assertEquals(Action.UP,e1.determinsticMonsterMove(w1,dx,dy));
        dx =3;
        assertEquals(Action.LEFT,e1.determinsticMonsterMove(w1,dx,dy));
        dx =1;
        dy =3;
        assertEquals(Action.UP,e1.determinsticMonsterMove(w1,dx,dy));
        w1.setPosition(Position.at(3,3));
        dx =-2;
        dy =0;
        assertEquals(Action.RIGHT,e1.determinsticMonsterMove(w1,dx,dy));






    }

    @Test
    void describe() {
        assertEquals("a\t\tHealth: 100/100\t\tAttack: 30\t\tDefense: 10\t\tExperience Value: 1\t\tVision Range: 5",e1.describe());
    }

    @Test
    void testToString() {
        assertEquals("a",e1.toString());
    }
}