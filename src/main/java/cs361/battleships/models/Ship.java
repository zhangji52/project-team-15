package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.hql.internal.ast.SqlASTFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


import static cs361.battleships.models.AttackStatus.HIT;
import static cs361.battleships.models.AttackStatus.MISS;
import static cs361.battleships.models.AttackStatus.SUNK;

public class Ship {

	@JsonProperty
	private List<Square> occupiedSquares;


	public Ship() {
		occupiedSquares = new ArrayList<>();
	}


	public Ship(Ship otherShip) {
		this.occupiedSquares = new ArrayList<>();

		List<Square> otherSquares = otherShip.getOccupiedSquares();
		Iterator<Square> it = otherSquares.iterator();
		while (it.hasNext()) {
			Square curSquare = it.next();
			this.occupiedSquares.add(new Square(curSquare.getRow(), curSquare.getColumn()));
		}
	}


	public Ship(String kind) {
		// This switch statement will determine how long our ship is, based on the type string given
		int shipLength = 0;
		switch (kind) {
			case "MINESWEEPER":
				shipLength = 2;
				break;

			case "DESTROYER":
				shipLength = 3;
				break;

			case "BATTLESHIP":
				shipLength = 4;
				break;

		}

		this.occupiedSquares = new ArrayList<>();

		for (int i = 0; i < shipLength; i++) {
			this.occupiedSquares.add(new Square());
		}
	}

	public void setLocation(int x, char y, boolean isVertical) {

		Iterator<Square> iterator = this.occupiedSquares.iterator();
		while (iterator.hasNext()) {
			// This is INTENDED to take each of the squares stored in this ship, and set their location on the board
			// This way ships themselves can keep track of where they are

			Square curSquare = iterator.next();
			curSquare.setRow(x);
			curSquare.setColumn(y);

			if (isVertical) {
				x++;
			} else {
				y++;
			}
		}
	}

	public List<Square> getOccupiedSquares() {
		return this.occupiedSquares;
	}

	public @JsonIgnore
	int getLength() {

		return this.occupiedSquares.size();
	}

	public boolean checkHit(int x, char y) {

		for (int i = 0; i < occupiedSquares.size(); i++) {
			if (occupiedSquares.get(i).getRow() == x && occupiedSquares.get(i).getColumn() == y)
				return true;
		}
		return false;
	}

	//Returns an attack status baised off the location given
	public AttackStatus checkHitStatus(int x, char y) {
		int hitCounter = 0;
		AttackStatus toSend = MISS;

		//cycles through the occupiedSquares to check if the hit location is owned by a ship
		for (int i = 0; i < occupiedSquares.size(); i++) {
			if ((occupiedSquares.get(i).getRow() == x) && (occupiedSquares.get(i).getColumn() == y)) {
				toSend = HIT;
				occupiedSquares.get(i).setSquareEvent(HIT);
			}

			//Checks for the number of hits in relation to the ships size to check if theres been a sink
			if (occupiedSquares.get(i).getSquareEvent() == HIT) {
				hitCounter += 1;
			}
		}


		if ((hitCounter == occupiedSquares.size()) && (toSend == HIT)) {
			toSend = SUNK;
			for (int i = 0; i < occupiedSquares.size(); i++) {
				if ((occupiedSquares.get(i).getRow() == x) && (occupiedSquares.get(i).getColumn() == y)) {
					occupiedSquares.get(i).setSquareEvent(SUNK);
				}
			}

		}
		return toSend;
	}
}