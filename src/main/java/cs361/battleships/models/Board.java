package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.Math;

public class Board {

	@JsonProperty private List<Ship> ships;
	@JsonProperty private List<Result> attacks;

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Board() {
		ships = new ArrayList<>();
		attacks = new ArrayList<>();
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public boolean placeShip(Ship ship, int x, char y, boolean isVertical) {
		if (ships.size() >= 3) {
			return false;
		}
		if (ships.stream().anyMatch(s -> s.getKind().equals(ship.getKind()))) {
			return false;
		}
		final var placedShip = new Ship(ship.getKind());
		placedShip.place(y, x, isVertical);
		if (ships.stream().anyMatch(s -> s.overlaps(placedShip))) {
			return false;
		}
		if (placedShip.getOccupiedSquares().stream().anyMatch(s -> s.isOutOfBounds())) {
			return false;
		}
		ships.add(placedShip);
		return true;
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Result attack(int x, char y) {
		Result attackResult = attack(new Square(x, y));
		attacks.add(attackResult);
		return attackResult;
	}

	private Result attack(Square s) {
//		if (attacks.stream().anyMatch(r -> r.getLocation().equals(s))) {
//			var attackResult = new Result(s);
//			attackResult.setResult(AtackStatus.INVALID);
//			return attackResult;
//		}
		var shipsAtLocation = ships.stream().filter(ship -> ship.isAtLocation(s)).collect(Collectors.toList());
		if (shipsAtLocation.size() == 0) {
			var attackResult = new Result(s);
			return attackResult;
		}
		var hitShip = shipsAtLocation.get(0);
		var attackResult = hitShip.attack(s.getRow(), s.getColumn());
		if (attackResult.getResult() == AtackStatus.SUNK) {
			if (ships.stream().allMatch(ship -> ship.isSunk())) {
				attackResult.setResult(AtackStatus.SURRENDER);
			}
		}
		return attackResult;
	}

	public List<Result> sonarPulse(int x, char y) {
		List<Result> pulseResults = new ArrayList<>();
		
		/**
		 * Need to sonarPulseTest on squares in a two square radius, like shown
		 * OOOOOOO
		 * OOOXOOO
		 * OOXXXOO
		 * OXXTXXO
		 * OOXXXOO
		 * OOOXOOO
		 * OOOOOOO
		 * Where X are tested squares and T is the targeted square, passed in to this function
		 */
		for (int i = -2; i <= 2; i++) {
			for (int j = (-2 + Math.abs(i)); j <= (2 - Math.abs(i)); j++){
				pulseResults.add(sonarPulse(new Square(x + i, (char) (y + j))));
			} 
		}
		
		return pulseResults;
	}

	private Result sonarPulse(Square s) {
		var shipsAtLocation = ships.stream().filter(ship -> ship.isAtLocation(s)).collect(Collectors.toList());
		if (shipsAtLocation.size() == 0) {
			// Didn't find a ship, return a MISS
			System.out.println("Returning miss result at: " + s.getColumn() + s.getRow());
			var pulseResult = new Result(s);
			return pulseResult;
		} 
		// Found a ship, return a FOUND
		var pulseResult = new Result(s);
		pulseResult.setResult(AtackStatus.FOUND);
		return pulseResult;
	}

	List<Ship> getShips() {
		return ships;
	}
}
