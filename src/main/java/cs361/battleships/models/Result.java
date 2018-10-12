package cs361.battleships.models;

import java.sql.ResultSet;

public class Result {

	private AttackStatus attackResult;
	private Ship resultShip;
	private Square location;

	public AttackStatus getResult() {			 //Returns the result
		return attackResult;
	}

	public void setResult(AttackStatus result) { //Sets the attackResult variable
		attackResult = result;
	}

	public Ship getShip() {						 //Returns the ship
		return resultShip;
	}

	public void setShip(Ship ship) {         	//Sets ship variable
		resultShip = ship;
	}

	public Square getLocation() { 				//Returns the Square location on the board
		return location;
	}

	public void setLocation(Square square) {   //Sets passed in square as the value for location variable
		location = square;
	}
}
