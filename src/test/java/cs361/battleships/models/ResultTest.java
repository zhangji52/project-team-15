package cs361.battleships.models;

import org.junit.Test;

import javax.validation.constraints.AssertTrue;

import java.util.MissingFormatArgumentException;

import static cs361.battleships.models.AttackStatus.*;
import static org.eclipse.jetty.webapp.MetaDataComplete.False;
import static org.eclipse.jetty.webapp.MetaDataComplete.True;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ResultTest {
    @Test
    public void testGetResult(){
        Result resultTester = new Result();
        resultTester.setResult(HIT);
        assert(resultTester.getResult() == HIT);
        resultTester.setResult(MISS);
        assert(resultTester.getResult() == MISS);
        resultTester.setResult(SUNK);
        assert(resultTester.getResult() == SUNK);
        resultTester.setResult(SURRENDER);
        assert(resultTester.getResult() == SURRENDER);
        resultTester.setResult(INVALID);
        assert(resultTester.getResult() == INVALID);

    }

    @Test
    public void testSetResult(){
        Result resultTester = new Result();
        resultTester.setResult(MISS);
        resultTester.setResult(HIT);
        assert(resultTester.getResult() == HIT);
        resultTester.setResult(MISS);
        assert(resultTester.getResult() == MISS);
        resultTester.setResult(SUNK);
        assert(resultTester.getResult() == SUNK);
        resultTester.setResult(SURRENDER);
        assert(resultTester.getResult() == SURRENDER);
        resultTester.setResult(INVALID);
        assert(resultTester.getResult() == INVALID);

    }

    @Test
    public void testGetShip(){
        Result resultTester = new Result();
        Ship testShip = new Ship();
        resultTester.setShip(testShip);
        assert(resultTester.getShip() == testShip);
    }

    @Test
    public void testSetShip(){
        Result resultTester = new Result();
        Ship testShip = new Ship();
        Ship testShip2 = new Ship();
        resultTester.setShip(testShip);
        resultTester.setShip(testShip2);
        assert(resultTester.getShip() == testShip2);
    }

    @Test
    public void testGetLocation(){
        Result resultTester = new Result();
        Square squareTest = new Square();
        resultTester.setLocation(squareTest);
        assert(resultTester.getLocation() == squareTest);

    }

    @Test
    public void testSetLocation(){
        Result resultTester = new Result();
        Square squareTest = new Square();
        Square squareTest2 = new Square();
        resultTester.setLocation(squareTest);
        resultTester.setLocation(squareTest2);
        assert(resultTester.getLocation() == squareTest2);
    }
}

