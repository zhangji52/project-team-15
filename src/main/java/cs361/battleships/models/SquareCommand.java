package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SquareCommand extends Square{

    @JsonProperty private int numHits;
    @JsonProperty private int armorPoints;

    public SquareCommand() {
    }

    public SquareCommand(int x, char y){
        numHits = 0;
        this.setColumn(y);
        this.setRow(x);
        armorPoints = 1;
    }

    //squareCommand
    public SquareCommand(int x, char y, int Armour){
        numHits = 0;
        this.setColumn(y);
        this.setRow(x);
        armorPoints = Armour;
    }


    public void hit() {
        if(numHits < armorPoints) {
            numHits += 1;
        }
        if(numHits == armorPoints){
            this.setHit(true);
        }
    }


    public int getNumHits(){ return numHits; }

    public int getarmorPoints(){ return armorPoints; }
}
