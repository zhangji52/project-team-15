package cs361.battleships.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Ship_MinesweeperTest {

    @Test
    public void testPlaceMinesweeperHorizontaly() {
        Ship minesweeper = new Ship_Minesweeper();
        minesweeper.place('A', 1, false);
        List<Square> occupiedSquares = minesweeper.getOccupiedSquares();
        ArrayList<Object> expected = new ArrayList<>();
        expected.add(new Square(1, 'A'));
        expected.add(new Square(1, 'B'));
        assertEquals(expected, occupiedSquares);
    }

    public void testPlaceMinesweeperVertically() {
        Ship minesweeper = new Ship_Minesweeper();
        minesweeper.place('A', 1, false);
        List<Square> occupiedSquares = minesweeper.getOccupiedSquares();
        ArrayList<Object> expected = new ArrayList<>();
        expected.add(new Square(1, 'A'));
        expected.add(new Square(2, 'A'));
        assertEquals(expected, occupiedSquares);
    }

    @Test
    public void testSinkCommand() {
        Ship_Minesweeper Minesweeper = new Ship_Minesweeper();
        Minesweeper.place('A', 1, true);

        var result = Minesweeper.attack(1, 'A');
        assertEquals(result.getResult(), AtackStatus.SUNK);
    }

    @Test
    public void testHitSinkCommand() {
        Ship_Minesweeper Minesweeper = new Ship_Minesweeper();
        Minesweeper.place('A', 1, true);

        var result = Minesweeper.attack(2, 'A');
        assertEquals(result.getResult(), AtackStatus.HIT);

        result = Minesweeper.attack(1, 'A');
        assertEquals(result.getResult(), AtackStatus.SUNK);
    }

    @Test
    public void testCommandPlace() {
        Ship_Minesweeper Minesweeper = new Ship_Minesweeper();
        Minesweeper.place('A', 1, true);
       assertEquals(Minesweeper.getCaptainModule().getarmorPoints(), 1);
        assertEquals(Minesweeper.getCaptainModule().getColumn(), 'A');
        assertEquals(Minesweeper.getCaptainModule().getRow(), 1);

        Square command = Minesweeper.getCaptainModule();
        Square location = Minesweeper.getOccupiedSquares().get(0);
        assertEquals(command, location);


    }
}

