package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.hql.internal.ast.SqlASTFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Ship {
	@JsonProperty private List<Square> occupiedSquares;

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
		int shipLength=0;
		switch(kind) {
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

		for(int i = 0; i < shipLength; i++) {
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

	public @JsonIgnore int getLength() {

		return this.occupiedSquares.size();
	}
}
