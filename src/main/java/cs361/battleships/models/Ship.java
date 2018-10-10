package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class Ship {

	@JsonProperty private List<Square> occupiedSquares;
	private String shipType;

	public Ship() {
		occupiedSquares = new ArrayList<>();
	}
	
	public Ship(String kind) {
		//TODO implement
		this.shipType = kind;
		/*switch(kind) {
			case "MINESWEEPER":
				//stuff
				break;

			case "DESTROYER":
				//stuff
				break;

			case "BATTLESHIP":
				//stuff
				break;

		}*/
	}

	public List<Square> getOccupiedSquares() {
		//TODO implement
		return null;
	}
}
