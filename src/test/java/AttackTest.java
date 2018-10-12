package cs361.battleships.models;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class AttackTest {
    @Test
    public void AttackTest() {
        Board board = new Board();
        Ship ship = new Ship("MINESWEEPER");
        Result refHit = new Result();

        refHit.setResult(AttackStatus.HIT);
        Result refSink = new Result();

        refSink.setResult(AttackStatus.SUNK);
        Result refMiss = new Result();
        //
        refMiss.setResult(AttackStatus.MISS);
        board.placeShip(ship, 4, 'E', false);
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
        assert(board.attack(9, 'I').getResult() == refMiss.getResult());


    }
}
