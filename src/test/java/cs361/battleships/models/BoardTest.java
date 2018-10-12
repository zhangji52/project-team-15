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
        board = new Board();
        assertFalse(board.placeShip(new Ship("MINESWEEPER"), 11, 'C', true));

        //out of bounds horizontally, due to ship size
        board = new Board();
        assertFalse(board.placeShip(new Ship("DESTROYER"), 9, 'I', false));
        board = new Board();
        assert (board.placeShip(new Ship("DESTROYER"), 5, 'C', false));

        //out of bounds vertically, due to ship size
        board = new Board();
        assert (board.placeShip(new Ship("DESTROYER"), 5, 'I', true));
        assert (board.placeShip(new Ship("BATTLESHIP"), 5, 'H', true));
        board = new Board();
        assert (board.placeShip(new Ship("DESTROYER"), 5, 'G', true));
        board = new Board();
        assertFalse(board.placeShip(new Ship("BATTLESHIP"), 8, 'G', true));
        board = new Board();
        assert (board.placeShip(new Ship("BATTLESHIP"), 7, 'G', true));

        //within bounds
        board = new Board();
        assert (board.placeShip(new Ship("DESTROYER"), 5, 'D', false));
        assertFalse(board.placeShip(new Ship("DESTROYER"), 5, 'D', true));
    }

    @Test
    public void testShipOverlap() {
        Board board = new Board();
        assert (board.placeShip(new Ship("MINESWEEPER"), 4, 'C', false));
        assertFalse(board.placeShip(new Ship("DESTROYER"), 3, 'D', true));

        board = new Board();
        assert (board.placeShip(new Ship("DESTROYER"), 6, 'C', false));
        assertFalse(board.placeShip(new Ship("BATTLESHIP"), 3, 'D', true));

    }

    @Test
    public void testPlacementRules() {
        Board board = new Board();

        assert (board.placeShip(new Ship("MINESWEEPER"), 3, 'D', false));
        assert (board.placeShip(new Ship("DESTROYER"), 4, 'D', false));
        assert (board.placeShip(new Ship("BATTLESHIP"), 5, 'D', false));
        assertFalse(board.placeShip(new Ship("DESTROYER"), 7, 'D', false));

        board = new Board();

        assert (board.placeShip(new Ship("MINESWEEPER"), 3, 'D', false));
        assertFalse(board.placeShip(new Ship("MINESWEEPER"), 4, 'D', false));


    }

    @Test
    public void testShipLocations() {
        Board board = new Board();

        assert (board.placeShip(new Ship("MINESWEEPER"), 3, 'D', false));
        assert (board.placeShip(new Ship("DESTROYER"), 4, 'D', false));
        assert (board.placeShip(new Ship("BATTLESHIP"), 5, 'D', false));

        List<Ship> ships = board.getShips();
        Iterator<Ship> it = ships.iterator();
        Ship tempShip = it.next();
        List<Square> sweepSquares = tempShip.getOccupiedSquares();
        Iterator<Square> jt = sweepSquares.iterator();
        char x = 'D';
        while (jt.hasNext()) {
            Square temp = jt.next();
            assert (temp.getRow() == 3);
            assert (temp.getColumn() == x);
            x++;
        }

        tempShip = it.next();
        sweepSquares = tempShip.getOccupiedSquares();
        jt = sweepSquares.iterator();
        x = 'D';
        while (jt.hasNext()) {
            Square temp = jt.next();
            assert (temp.getRow() == 4);
            assert (temp.getColumn() == x);
            x++;
        }

        tempShip = it.next();
        sweepSquares = tempShip.getOccupiedSquares();
        jt = sweepSquares.iterator();
        x = 'D';
        while (jt.hasNext()) {
            Square temp = jt.next();
            assert (temp.getRow() == 5);
            assert (temp.getColumn() == x);
            x++;
        }
    }

//    @Test
    public void testInvalidAttack() {
        Board board = new Board();
        Result referenceResult = new Result();
        referenceResult.setResult(AttackStatus.INVALID);

        //checks for out of bounds attack
        assert (board.attack(11, 'a').getResult() == referenceResult.getResult());
        assert (board.attack(0, 'K').getResult() == referenceResult.getResult());
        assert (board.attack(11, 'D').getResult() == referenceResult.getResult());
        assert (board.attack(2, 'K').getResult() == referenceResult.getResult());


    }

    @Test
    public void testValidAttack() {
        Board board = new Board();
        Result referenceResult = new Result();
        referenceResult.setResult(AttackStatus.MISS);

        //checks for a few attacks on an empty board
        assert (board.attack(10, 'J').getResult() == referenceResult.getResult());
        assert (board.attack(10, 'A').getResult() == referenceResult.getResult());
        assert (board.attack(1, 'J').getResult() == referenceResult.getResult());
        assert (board.attack(5, 'E').getResult() == referenceResult.getResult());
        assert (board.attack(1, 'C').getResult() == referenceResult.getResult());
        assert (board.attack(7, 'H').getResult() == referenceResult.getResult());

    }
}

