package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

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

//		for(int i = 0; i < this.shipLength; i++) {
//			this.occupiedSquares.add(new Square());
//		}
	}

	public List<Square> getOccupiedSquares() {
		return this.occupiedSquares;
	}

	public int getLength() {
		return shipLength;
	}
}
