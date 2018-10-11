package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonProperty;

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
		// I'm assuming non-zero indexing for the numbers, and valid characters to be 'A' through 'J'
		// So the board starts at 1-A and ends at 10-J

		//TODO make sure ships don't get placed on top of each other

		System.out.println("\tAttempting to place ship at: " + x + y + isVertical);

		if (this.ships.size() > 3)
			return false;

		if (isVertical) {
			// Ship is vertical so we make sure it has space above it and is within bounds horizontally
			if ( (x >= 0 && x < 10) && ((y - 'A') >= 0 && (y - 'A') < (10 - ship.getLength() + 1))) {
				// We are within bounds, so everything is OK to place the ship
				System.out.println("\tSuccessful");
				ship.setLocation(x, y, isVertical);
				ships.add(ship);
				return true;
			}
		} else {
			// Ship is horizontal so we make sure it has space to the right and is within bounds vertically
			if ( ((y - 'A') >= 0 && (y - 'A') < 10) && (x >= 0 && x < (10 - ship.getLength() + 1))) {
				// We are within bounds, so everything is OK to place the ship
				System.out.println("\tSuccessful");
				ship.setLocation(x, y, isVertical);
				ships.add(ship);
				return true;
			}
		}
		System.out.println("\tFailed");
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
