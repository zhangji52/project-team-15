package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Iterator;

import java.util.ArrayList;
import java.util.List;

public class Board {

	@JsonProperty public List<Ship> ships;

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Board() {
		ships = new ArrayList<>();
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public boolean placeShip(Ship ship, int x, char y, boolean isVertical) {

		//checks to see if number of ships is maxed
		if (this.ships.size() > 2) {
			return false;
		}

		//checks to make sure ships don't get placed on top of each other
        //I'm also hijacking this to check whether or not this ship type has already been placed.
		Iterator<Ship> it = this.ships.iterator();
        while (it.hasNext()) {
            //System.out.println("J is: " +j);
            Ship tempShip = it.next();

            if (ship.getLength() == tempShip.getLength()) {
                //This type of ship has already been placed, so don't let them place another one!!!
                return false;
            }

            List<Square> sweepSquares = tempShip.getOccupiedSquares();
            Iterator<Square> jt = sweepSquares.iterator();
            while (jt.hasNext()) {
                Square tempSquare = jt.next();
				//Need to make sure no blocks in this ship will intersect with any other blocks in ANY other ship
				for (int i = 0; i < ship.getLength(); i++) {
					if (isVertical) {
						if (tempSquare.getRow() == (x+i) && tempSquare.getColumn() == y) {
							//Squares overlap, so we can't place the ship here.
							return false;
						}
					} else {
						if (tempSquare.getRow() == x && tempSquare.getColumn() == (y+i)) {
							//Squares overlap, so we can't place the ship here.
							return false;
						}
					}
				}
            }
		}

		int effectiveY = y - 'A' + 1;

		if (isVertical) {
			// Ship is vertical so we make sure it has space above it and is within bounds horizontally
			if ( (effectiveY > 0 && effectiveY <= 10) && (x > 0 && x <= (10 - ship.getLength() + 1))) {
				// We are within bounds, so everything is OK to place the ship
				//System.out.println("\tSuccessful");
				ship.setLocation(x, y, isVertical);
				//System.out.println("\t\t\tActually adding ship of length: " + ship.getLength());
				ships.add(ship);
				return true;
			}
		} else {
			// Ship is horizontal so we make sure it has space to the right and is within bounds vertically
			if ( (x > 0 && x <= 10) && (effectiveY > 0 && effectiveY <= (10 - ship.getLength() + 1))) {
				// We are within bounds, so everything is OK to place the ship
				ship.setLocation(x, y, isVertical);
				ships.add(ship);
				return true;
			}
		}
		return false;
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Result attack(int x, char y) {
		//TODO Implement
		return null;
	}

	public List<Ship> getShips() {
		return ships;
	}

	public void setShips(List<Ship> ships) {
		this.ships = ships;
	}

	public List<Result> getAttacks() {
		//TODO implement
		return null;
	}

	public void setAttacks(List<Result> attacks) {
		//TODO implement
	}
}
