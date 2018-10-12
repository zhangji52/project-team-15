package cs361.battleships.models;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class AttackTest {
    @Test
    public void AttackTest() {
        Board board = new Board();
        Result refHit = new Result();
        refHit.setResult(AttackStatus.HIT);

        Result refSink = new Result();
        refSink.setResult(AttackStatus.SUNK);

        Result refMiss = new Result();
        refMiss.setResult(AttackStatus.MISS);

        Result refEnd = new Result();
        refEnd.setResult(AttackStatus.SURRENDER);

        Ship ship1 = new Ship("MINESWEEPER");
        board.placeShip(ship1, 4, 'E', false);
        //miss the ship
        assert(board.attack(3, 'E').getResult() == refMiss.getResult());
        //hit the ship
        assert(board.attack(4, 'E').getResult() == refHit.getResult());
        //sink the ship
        assert(board.attack(4, 'F').getResult() == refSink.getResult());

        Ship ship2 = new Ship("DESTROYER");
        board.placeShip(ship2, 6, 'I', true);
        assert(board.attack(5, 'I').getResult() == refMiss.getResult());
        //hit the ship
        assert(board.attack(6, 'I').getResult() == refHit.getResult());
        assert(board.attack(7, 'I').getResult() == refHit.getResult());
        //sink the ship
        assert(board.attack(8, 'I').getResult() == refSink.getResult());

        Ship ship3 = new Ship("BATTLESHIP");
        board.placeShip(ship3, 5, 'A', false);

        //3 hits
        assert(board.attack(5, 'A').getResult() == refHit.getResult());
        assert(board.attack(5, 'B').getResult() == refHit.getResult());
        assert(board.attack(5, 'C').getResult() == refHit.getResult());

        //Should end gaem
        assert(board.attack(5, 'D').getResult() == refEnd.getResult());
    }
}