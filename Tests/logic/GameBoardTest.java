package logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {
    private static GameBoard g;
    private static Enemy e;

    @BeforeEach
    void setUp(){

        Warrior w1 = new Warrior("Arya Stark", 150, 40, 2, 2);
        w1.setPosition(Position.at(0,1));

        Empty e1 = new Empty();
        e1.setPosition(Position.at(1,0));

        Wall w2 = new Wall();
        w2.setPosition(Position.at(1,1));
        Monster m2 = new Monster('a', "a", 500, 1, 5, 1, 1);
        m2.setPosition(Position.at(0,0));
        e = m2;

        Tile[][] gameb = new Tile[][]{{e,w1},{e1,w2}};
        g= new GameBoard(gameb,2,2);

    }


    @Test
    void get() {
        Tile t = g.get(Position.at(0,0));
        assertEquals("a",t.toString());
        Tile t1 = g.get(Position.at(1,0));
        assertEquals(".",t1.toString());

    }

    @Test
    void testToString() {
        assertEquals(g.toString(),"a.\n" +
                "@#\n");
    }
}