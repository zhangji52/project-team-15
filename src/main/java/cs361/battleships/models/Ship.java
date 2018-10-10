package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.hql.internal.ast.SqlASTFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Ship {

	@JsonProperty private List<Square> occupiedSquares;
	@JsonProperty private int shipLength;

	public Ship() {
		occupiedSquares = new ArrayList<>();
	}
	
	public Ship(String kind) {

		// This switch statement will determine how long our ship is, based on the type string given
		switch(kind) {
			case "MINESWEEPER":
				this.shipLength = 2;
				break;

			case "DESTROYER":
				this.shipLength = 3;
				break;

			case "BATTLESHIP":
				this.shipLength = 4;
				break;

		}

		this.occupiedSquares = new ArrayList<>();

		for(int i = 0; i < this.shipLength; i++) {
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
				y++;
			} else {
				x++;
			}
		}
	}

	public List<Square> getOccupiedSquares() {
		return this.occupiedSquares;
	}

	public int getLength() {
		return shipLength;
	}
}
