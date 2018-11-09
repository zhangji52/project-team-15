package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("unused")
public class Square {

	@JsonProperty private int row;
	@JsonProperty private char column;
	@JsonProperty private boolean hit = false;

	public Square() {
	}

	public Square(int row, char column) {
		this.row = row;
		this.column = column;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Square) {
			return ((Square) other).row == this.row && ((Square) other).column == this.column;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 31 * row + column;
	}

	@JsonIgnore
	public boolean isOutOfBounds() {
		return row > 10 || row < 1 || column > 'J' || column < 'A';
	}

	public boolean isHit() {
		return hit;
	}

	public void hit() { hit = true; }

	@Override
	public String toString() {
		return "(" + row + ", " + column + ')';
	}

	//Setters and Getters for values
	@JsonIgnore
	public void setColumn(char colChange){
		column = colChange;
	}

	@JsonIgnore
	public char getColumn() {
		return column;
	}

	@JsonIgnore
	public void setRow(int rowChange) {
		row = rowChange;
	}

	@JsonIgnore
	public int getRow() {
		return row;
	}

	@JsonIgnore
	void setHit(boolean in){
		hit = in;
	}

	@JsonIgnore
	boolean getHit(){
		return hit;
	}
}
