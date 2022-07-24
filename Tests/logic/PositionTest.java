package logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void compareTo() {
        Position pos1 = new Position(3,0);
        Position pos2 = new Position(3,4);
        assertEquals(-1,pos1.compareTo(pos2));
        assertEquals(1,pos2.compareTo(pos1));
    }

    @Test
    void range() {
        Position pos1 = new Position(3,0);
        Position pos2 = new Position(3,4);
        assertEquals(4,pos1.range(pos2));

    }

    @Test
    void translate() {
        Position pos = new Position(0,0);
        Position newpos = pos.translate(Action.RIGHT);
        assertEquals(1,newpos.x);
        assertEquals(0,newpos.y);
        Position newpos2 = pos.translate(Action.DOWN);
        assertEquals(1,newpos2.y);
        assertEquals(0,newpos2.x);
    }
}