package cs361.battleships.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class SquareCommandTest {
    @Test
    public void testIsOutOfBoundTest() {
        SquareCommand square = new SquareCommand(11, 'A');
        assertTrue(square.isOutOfBounds());

        square = new SquareCommand(1, 'Z');
        assertTrue(square.isOutOfBounds());

        square = new SquareCommand(1, 'a');
        assertTrue(square.isOutOfBounds());

        square = new SquareCommand(0, 'A');
        assertTrue(square.isOutOfBounds());
    }

    @Test
    public void testIsHit() {
        SquareCommand square = new SquareCommand(1, 'A', 2);
        assertFalse(square.isHit());

        square.hit();
        assertFalse(square.isHit());

        square.hit();
        assertTrue(square.isHit());
    }

    @Test
    public void testEquals() {
        Square square1 = new SquareCommand(1, 'A');
        Square square2 = new SquareCommand(1, 'A');

        assertTrue(square1.equals(square2));
        assertEquals(square1.hashCode(), square2.hashCode());
    }

    @Test
    public void testNotEquals() {
        Square square1 = new Square(1, 'A');
        Square square2 = new Square(2, 'A');

        assertFalse(square1.equals(square2));
        assertNotEquals(square1.hashCode(), square2.hashCode());
    }
}
