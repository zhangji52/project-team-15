package cs361.battleships.models;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class BoardTest {

    @Test
    public void testInvalidPlacement() {
        Board board = new Board();
        //out of bounds horizontally, directly
        assertFalse(board.placeShip(new Ship("MINESWEEPER"), 11, 'C', true));

        //out of bounds horizontally, due to ship size
        assertFalse(board.placeShip(new Ship("DESTROYER"), 9, 'C', false));
        assert(board.placeShip(new Ship("DESTROYER"), 8, 'C', false));

        //out of bounds vertically, due to ship size
        assertFalse(board.placeShip(new Ship("DESTROYER"), 5, 'I', true));
        assertFalse(board.placeShip(new Ship("BATTLESHIP"), 5, 'H', true));
        assert(board.placeShip(new Ship("DESTROYER"), 5, 'G', true));

        //within bounds
        assert(board.placeShip(new Ship("DESTROYER"), 5, 'D', false));
        assert(board.placeShip(new Ship("DESTROYER"), 5, 'D', true));
    }
}
