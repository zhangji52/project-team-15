package cs361.battleships.models;

import java.util.ArrayList;
import java.util.List;

public class Board {

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Board() {
		// TODO Implement
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public boolean placeShip(Ship ship, int x, char y, boolean isVertical) {
		if (isVertical) {
			// Ship is vertical so we make sure it has space above it and is within bounds horizontally
			if ( (x > 0 && x <= 10) && ((y - 'A') >= 0 && (y - 'A') < (10 - ship.getLength() + 1))) {
				// We are within bounds, so everything is OK to place the ship
				// TODO place the ship
				return true;
			}
		} else {
			// Ship is horizontal so we make sure it has space to the right and is within bounds vertically
			if ( ((y - 'A') >= 0 && (y - 'A') < 10) && (x > 0 && x <= (10 - ship.getLength() + 1))) {
				// We are within bounds, so everything is OK to place the ship
				// TODO place the ship
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
		//TODO implement
		return null;
	}

	public void setShips(List<Ship> ships) {
		//TODO implement
	}

	public List<Result> getAttacks() {
		//TODO implement
		return null;
	}

	public void setAttacks(List<Result> attacks) {
		//TODO implement
	}
}
