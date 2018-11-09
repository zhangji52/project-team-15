package cs361.battleships.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BoardTest {

    private Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testInvalidPlacement() {
        assertFalse(board.placeShip(new Ship("MINESWEEPER"), 11, 'C', true));
    }

    @Test
    public void testPlaceMinesweeper() {
        assertTrue(board.placeShip(new Ship("MINESWEEPER"), 1, 'A', true));
    }

    @Test
    public void testAttackEmptySquare() {
        board.placeShip(new Ship("MINESWEEPER"), 1, 'A', true);
        Result result = board.attack(2, 'E');
        assertEquals(AtackStatus.MISS, result.getResult());
    }

    @Test
    public void testAttackShip() {
        Ship minesweeper = new Ship("MINESWEEPER");
        board.placeShip(minesweeper, 1, 'A', true);
        minesweeper = board.getShips().get(0);
        Result result = board.attack(1, 'A');
        assertEquals(AtackStatus.SURRENDER, result.getResult());
        assertEquals(minesweeper, result.getShip());
    }
      /* the captains quarters allows for squares to be hit twice, invalid test*/
//    @Test
//    public void testAttackSameSquareMultipleTimes() {
//        Ship minesweeper = new Ship("MINESWEEPER");
//        board.placeShip(minesweeper, 1, 'A', true);
//        board.attack(1, 'A');
//        Result result = board.attack(1, 'A');
//        assertEquals(AtackStatus.INVALID, result.getResult());
//    }

      /* the captains quarters allows for squares to be hit twice, invalid test*/
//    @Test
//    public void testAttackSameEmptySquareMultipleTimes() {
//        Result initialResult = board.attack(1, 'A');
//        assertEquals(AtackStatus.MISS, initialResult.getResult());
//        Result result = board.attack(1, 'A');
//        assertEquals(AtackStatus.INVALID, result.getResult());
//    }

    @Test
    public void testSurrender() {
        board.placeShip(new Ship("MINESWEEPER"), 1, 'A', true);
        board.attack(1, 'A');
        var result = board.attack(2, 'A');
        assertEquals(AtackStatus.SURRENDER, result.getResult());
    }

    @Test
    public void testPlaceMultipleShipsOfSameType() {
        assertTrue(board.placeShip(new Ship("MINESWEEPER"), 1, 'A', true));
        assertFalse(board.placeShip(new Ship("MINESWEEPER"), 5, 'D', true));

    }

    @Test
    public void testCantPlaceMoreThan3Ships() {
        assertTrue(board.placeShip(new Ship("MINESWEEPER"), 1, 'A', true));
        assertTrue(board.placeShip(new Ship("BATTLESHIP"), 5, 'D', true));
        assertTrue(board.placeShip(new Ship("DESTROYER"), 6, 'A', false));
        assertFalse(board.placeShip(new Ship(""), 8, 'A', false));

    }

    @Test
    public void testSonarPulse() {
        board.placeShip(new Ship("MINESWEEPER"), 1, 'A', true);
        board.placeShip(new Ship("BATTLESHIP"), 5, 'D', true);
        board.placeShip(new Ship("DESTROYER"), 6, 'A', false);
        List<Result> results = board.sonarPulse(5, 'D');

        List<Square> trueResults = new ArrayList<>();
        trueResults.add(new Square(5, 'D'));
        trueResults.add(new Square(6, 'C'));
        trueResults.add(new Square(6, 'D'));
        trueResults.add(new Square(7, 'D'));
        trueResults.forEach((s) -> assertTrue(results.stream().anyMatch(r -> r.getLocation().equals(s) && r.getResult() == AtackStatus.FOUND)));

        List<Square> falseResults = new ArrayList<>();
        falseResults.add(new Square(3, 'D'));
        falseResults.add(new Square(4, 'C'));
        falseResults.add(new Square(4, 'D'));
        falseResults.add(new Square(4, 'E'));
        falseResults.add(new Square(5, 'B'));
        falseResults.add(new Square(5, 'C'));
        falseResults.add(new Square(5, 'E'));
        falseResults.add(new Square(5, 'F'));
        falseResults.add(new Square(6, 'E'));
        falseResults.forEach((s) -> assertTrue(results.stream().anyMatch(r -> r.getLocation().equals(s) && r.getResult() == AtackStatus.NOTFOUND)));
    }

    @Test
    public void testCaptainsQuartersSurrender() {
        board.placeShip(new Ship("MINESWEEPER"), 1, 'A', true);
        board.placeShip(new Ship("BATTLESHIP"), 1, 'C', true);
        board.placeShip(new Ship("DESTROYER"), 1, 'B', true);

        var result = board.attack(1,'A');
        assertEquals(result.getResult(), AtackStatus.SUNK);

        result = board.attack(2,'B');
        assertEquals(result.getResult(), AtackStatus.MISS);
        result = board.attack(2,'B');
        assertEquals(result.getResult(), AtackStatus.SUNK);

        result = board.attack(3,'C');
        assertEquals(result.getResult(), AtackStatus.MISS);
        result = board.attack(3,'C');
        assertEquals(result.getResult(), AtackStatus.SURRENDER);
    }
}
