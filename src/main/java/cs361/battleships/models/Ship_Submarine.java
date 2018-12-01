package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.common.collect.Sets;
import com.mchange.v1.util.CollectionUtils;
import jdk.jshell.Snippet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Ship_Submarine extends Ship_CaptainsQuarters {

    public Ship_Submarine() {
        this.setOccupiedSquares(new ArrayList<>());
        this.setKind("SUBMARINE");
        this.setSize(4);
    }

    public Ship_Submarine(boolean Submerge) {
        this.setOccupiedSquares(new ArrayList<>());
        if(Submerge) {
            this.setKind("SUBMARINE_S");
        } else {
            this.setKind("SUBMARINE");
        }
        this.setSize(4);
        this.setIsSubmerged(Submerge);
    }


    public void place(char col, int row, boolean isVertical) {
        List<Square> temp = getOccupiedSquares();
        for (int i=0; i < getSize(); i++) {
            if (isVertical) {
                if(i == 3){
                    SquareCommand toPass = new SquareCommand(row + i, col, 2);
                    setCaptainModule(toPass);
                    temp.add(toPass);
                } else if(i == 2) {
                    temp.add(new Square(row + i, col));
                    temp.add(new Square(row + i, (char) (col + 1)));
                } else {
                    temp.add(new Square(row + i, col));
                }
            } else {
                if(i == 3){
                    SquareCommand toPass = new SquareCommand(row, (char) (col + i), 2);
                    setCaptainModule(toPass);
                    temp.add(toPass);
                } else if(i == 2) {
                    temp.add(new Square(row, (char) (col + i)));
                    temp.add(new Square(row-1, (char) (col + i)));
                } else {
                    temp.add(new Square(row, (char) (col + i)));
                }
            }
        }
        this.setOccupiedSquares(temp);
    }

    public boolean overlaps(Ship other) {
        if(this.getIsSubmerged()){
            return false;
        }
        Set<Square> thisSquares = Set.copyOf(getOccupiedSquares());
        Set<Square> otherSquares = Set.copyOf(other.getOccupiedSquares());
        Sets.SetView<Square> intersection = Sets.intersection(thisSquares, otherSquares);
        return intersection.size() != 0;
    }

    boolean getIsSubmerged(){ return  isSubmerged; }

}
