package cs361.battleships.models;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class BoardTest {

    @Test
    public void testShipPlacement() {
        Board board = new Board();
        //out of bounds horizontally, directly
        assertFalse(board.placeShip(new Ship("MINESWEEPER"), 11, 'C', true));

        //out of bounds horizontally, due to ship size
        assertFalse(board.placeShip(new Ship("DESTROYER"), 9, 'C', false));
        assert(board.placeShip(new Ship("DESTROYER"), 7, 'C', false));

        //out of bounds vertically, due to ship size
        assertFalse(board.placeShip(new Ship("DESTROYER"), 5, 'I', true));
        assertFalse(board.placeShip(new Ship("BATTLESHIP"), 5, 'H', true));
        assert(board.placeShip(new Ship("DESTROYER"), 5, 'G', true));

        //within bounds
        assert(board.placeShip(new Ship("DESTROYER"), 5, 'D', false));
        assert(board.placeShip(new Ship("DESTROYER"), 5, 'D', true));
    }

    @Test
    public void testShipLocations() {
        Board board = new Board();

        assert(board.placeShip(new Ship("MINESWEEPER"), 3, 'D', false));
        assert(board.placeShip(new Ship("DESTROYER"), 4, 'D', false));
        assert(board.placeShip(new Ship("BATTLESHIP"), 5, 'D', false));

        List<Ship> ships = board.getShips();
        Iterator<Ship> it = ships.iterator();
        int j = 3;
        while (it.hasNext()) {
            Ship tempShip = it.next();

            List<Square> sweepSquares = tempShip.getOccupiedSquares();
            Iterator<Square> jt = sweepSquares.iterator();
            int x = j;
            while (jt.hasNext()) {
                Square temp = jt.next();
                assert(temp.getRow() == x);
                assert(temp.getColumn() == 'D');
                x++;
            }
            j++;
        }
    }
}
