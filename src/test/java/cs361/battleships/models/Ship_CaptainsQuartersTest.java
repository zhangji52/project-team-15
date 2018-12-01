package cs361.battleships.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Ship_CaptainsQuartersTest {

    @Test
    public void testPlaceMinesweeperHorizontaly() {
        Ship_Minesweeper  minesweeper = new Ship_Minesweeper();
        minesweeper.place('A', 1, false);
        List<Square> occupiedSquares = minesweeper.getOccupiedSquares();
        ArrayList<Object> expected = new ArrayList<>();
        expected.add(new Square(1, 'A'));
        expected.add(new Square(1, 'B'));
        assertEquals(expected, occupiedSquares);
    }

    @Test
    public void testPlaceMinesweeperVertically() {
        Ship_Minesweeper  minesweeper = new Ship_Minesweeper();
        minesweeper.place('A', 1, true);
        List<Square> occupiedSquares = minesweeper.getOccupiedSquares();
        ArrayList<Object> expected = new ArrayList<>();
        expected.add(new Square(1, 'A'));
        expected.add(new Square(2, 'A'));
        assertEquals(expected, occupiedSquares);
    }

    @Test
    public void testPlaceDestroyerHorizontaly() {
        Ship_Destroyer  minesweeper = new Ship_Destroyer();
        minesweeper.place('A', 1, false);
        List<Square> occupiedSquares = minesweeper.getOccupiedSquares();
        ArrayList<Object> expected = new ArrayList<>();
        expected.add(new Square(1, 'A'));
        expected.add(new Square(1, 'B'));
        expected.add(new Square(1, 'C'));
        assertEquals(expected, occupiedSquares);
    }

    @Test
    public void testPlaceDestroyerVertically() {
        Ship_Destroyer  minesweeper = new Ship_Destroyer();
        minesweeper.place('A', 1, true);
        List<Square> occupiedSquares = minesweeper.getOccupiedSquares();
        ArrayList<Object> expected = new ArrayList<>();
        expected.add(new Square(1, 'A'));
        expected.add(new Square(2, 'A'));
        expected.add(new Square(3, 'A'));
        assertEquals(expected, occupiedSquares);
    }

    @Test
    public void testPlaceBattleshipHorizontaly() {
        Ship_Battleship  minesweeper = new Ship_Battleship();
        minesweeper.place('A', 1, false);
        List<Square> occupiedSquares = minesweeper.getOccupiedSquares();
        ArrayList<Object> expected = new ArrayList<>();
        expected.add(new Square(1, 'A'));
        expected.add(new Square(1, 'B'));
        expected.add(new Square(1, 'C'));
        expected.add(new Square(1, 'D'));
        assertEquals(expected, occupiedSquares);
    }

    @Test
    public void testPlaceBattleshipVertically() {
        Ship_Battleship  minesweeper = new Ship_Battleship();
        minesweeper.place('A', 1, true);
        List<Square> occupiedSquares = minesweeper.getOccupiedSquares();
        ArrayList<Object> expected = new ArrayList<>();
        expected.add(new Square(1, 'A'));
        expected.add(new Square(2, 'A'));
        expected.add(new Square(3, 'A'));
        expected.add(new Square(4, 'A'));
        assertEquals(expected, occupiedSquares);
    }
    @Test
    public void testShipOverlaps() {
        Ship_Minesweeper  minesweeper1 = new Ship_Minesweeper();
        minesweeper1.place('A', 1, true);

        Ship_Minesweeper  minesweeper2 = new Ship_Minesweeper();
        minesweeper2.place('A', 1, true);

        assertTrue(minesweeper1.overlaps(minesweeper2));
    }

    @Test
    public void testShipsDontOverlap() {
        Ship_Minesweeper  minesweeper1 = new Ship_Minesweeper();
        minesweeper1.place('A', 1, true);

        Ship_Minesweeper  minesweeper2 = new Ship_Minesweeper();
        minesweeper2.place('C', 2, true);

        assertFalse(minesweeper1.overlaps(minesweeper2));
    }

    @Test
    public void testIsAtLocation() {
        Ship_Battleship  minesweeper = new Ship_Battleship();
        minesweeper.place('A', 1, true);

        assertTrue(minesweeper.isAtLocation(new Square(1, 'A')));
        assertTrue(minesweeper.isAtLocation(new Square(2, 'A')));
    }

    @Test
    public void testHit() {
        Ship_Battleship  minesweeper = new Ship_Battleship();
        minesweeper.place('A', 1, true);

        Result result = minesweeper.attack(1, 'A');
        assertEquals(AtackStatus.HIT, result.getResult());
        assertEquals(minesweeper, result.getShip());
        assertEquals(new Square(1, 'A'), result.getLocation());
    }

    @Test
    public void testSink() {
        Ship_Minesweeper  minesweeper = new Ship_Minesweeper();
        minesweeper.place('A', 1, true);

        minesweeper.attack(1, 'A');
        Result result = minesweeper.attack(2, 'A');

        assertEquals(AtackStatus.SUNK, result.getResult());
        assertEquals(minesweeper, result.getShip());
        assertEquals(new Square(2, 'A'), result.getLocation());
    }

    @Test
    public void testOverlapsBug() {
        Ship_Minesweeper minesweeper = new Ship_Minesweeper();
        Ship_Destroyer destroyer= new Ship_Destroyer();
        minesweeper.place('C', 5, false);
        destroyer.place('C', 5, false);
        assertTrue(minesweeper.overlaps(destroyer));
    }

    @Test
    public void testAttackSameSquareTwice() {
        Ship_Minesweeper minesweeper = new Ship_Minesweeper();
        minesweeper.place('A', 1, true);
        var result = minesweeper.attack(1, 'A');
        assertEquals(AtackStatus.SUNK, result.getResult());
        result = minesweeper.attack(1, 'A');
        assertEquals(AtackStatus.SUNK, result.getResult());
    }

    @Test
    public void testEquals() {
        Ship_Minesweeper minesweeper1 = new Ship_Minesweeper();
        minesweeper1.place('A', 1, true);
        Ship_Minesweeper minesweeper2 = new Ship_Minesweeper();
        minesweeper2.place('A', 1, true);
        assertTrue(minesweeper1.equals(minesweeper2));
        assertEquals(minesweeper1.hashCode(), minesweeper2.hashCode());
    }

    @Test
    public void testSinkCommand() {
        Ship Minesweeper = new Ship_Minesweeper();
        Ship Destroyer = new Ship_Destroyer();
        Ship BattleShip = new Ship_Battleship();

        Minesweeper.place('A',1, true);
        Destroyer.place('B',1, true);
        BattleShip.place('C',1, true);

        var result = Minesweeper.attack(1, 'A');
        assertEquals(result.getResult(), AtackStatus.SUNK);

        result = Destroyer.attack(1, 'B');
        assertEquals(result.getResult(), AtackStatus.HIT);

        result = Destroyer.attack(2, 'B');
        assertEquals(result.getResult(), AtackStatus.MISS);
        assertEquals(1, ((Ship_Destroyer) Destroyer).getCaptainModule().getNumHits());
        result = Destroyer.attack(2, 'B');
        assertEquals(result.getResult(), AtackStatus.SUNK);

        result = BattleShip.attack(3,'C');
        assertEquals(result.getResult(), AtackStatus.MISS);
        result = BattleShip.attack(3,'C');
        assertEquals(result.getResult(), AtackStatus.SUNK);
    }
}
