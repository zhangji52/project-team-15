package cs361.battleships.models;

import org.hibernate.hql.internal.ast.SqlASTFactory;
import org.junit.Test;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import static org.junit.Assert.assertFalse;

public class ShipTest {

    @Test
    public void shipLocationTest1() {
        Ship sweeper = new Ship("DESTROYER");

        int x = 5; char y = 'C';
        sweeper.setLocation(x, y, false);

        List<Square> sweepSquares = sweeper.getOccupiedSquares();
        Iterator<Square> it = sweepSquares.iterator();
        while (it.hasNext()) {
            Square temp = it.next();
            assert(temp.getRow() == x);
            assert(temp.getColumn() == y);
            y++;
        }
    }
}
