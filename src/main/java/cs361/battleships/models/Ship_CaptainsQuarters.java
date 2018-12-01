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

public class Ship_CaptainsQuarters extends Ship{

    @JsonProperty private SquareCommand CaptainModule;
    @JsonProperty private int CapLoc;
    @JsonProperty private int Armor;
    @JsonProperty boolean isSubmerged = false;

    public void place(char col, int row, boolean isVertical) {
        List<Square> temp = getOccupiedSquares();
        for (int i=0; i < getSize(); i++) {
            if (isVertical) {
                if(i == CapLoc){
                    SquareCommand toPass = new SquareCommand(row + i, col, Armor);
                    setCaptainModule(toPass);
                    temp.add(toPass);
                } else {
                    temp.add(new Square(row + i, col));
                }
            } else {
                if(i == CapLoc){
                    SquareCommand toPass = new SquareCommand(row, (char) (col + i), Armor);
                    setCaptainModule(toPass);
                    temp.add(toPass);
                } else {
                    temp.add(new Square(row, (char) (col + i)));
                }
            }
        }
        this.setOccupiedSquares(temp);
    }

    public Result attack(int x, char y) {
        var attackedLocation = new Square(x, y);
        var square = getOccupiedSquares().stream().filter(s -> s.equals(attackedLocation)).findFirst();
        if (!square.isPresent()) {
            return new Result(attackedLocation);
        }
        var attackedSquare = square.get();

        if(attackedSquare.getColumn() == CaptainModule.getColumn() && attackedSquare.getRow() == CaptainModule.getRow()){
            attackedSquare = CaptainModule;
        }

        attackedSquare.hit();
        var result = new Result(attackedLocation);
        result.setShip(this);
        if (isSunk()) {
            result.setResult(AtackStatus.SUNK);
        } else if (attackedSquare.getHit() == false) {
            result.setResult(AtackStatus.MISS);
        } else {
            result.setResult(AtackStatus.HIT);
        }
        return result;
    }

    @JsonIgnore
    public boolean isSunk() {
        int hit_check = 0;
        for (int i = 0; i < getOccupiedSquares().size(); i++) {
            if(this.getOccupiedSquares().get(i).getHit()){
                hit_check += 1;
            }
        }
        if(hit_check == getOccupiedSquares().size() || CaptainModule.getHit()){
            return true;
        }
        return false;
        //return getOccupiedSquares().stream().allMatch(s -> s.isHit());
    }

    public boolean overlaps(Ship other) {
        if(other.getKind() == "SUBMARINE_S" || this.getKind() == "SUBMARINE_S"){
            return false;
        }
        Set<Square> thisSquares = Set.copyOf(getOccupiedSquares());
        Set<Square> otherSquares = Set.copyOf(other.getOccupiedSquares());
        Sets.SetView<Square> intersection = Sets.intersection(thisSquares, otherSquares);
        return intersection.size() != 0;
    }

    @JsonIgnore
    public SquareCommand getCaptainModule() {return CaptainModule; }

    @JsonIgnore
    protected void setCaptainModule(SquareCommand in){
        CaptainModule = in;
    }

    @JsonIgnore
    protected void setArmor(int ArmorAmount){ Armor = ArmorAmount; }

    @JsonIgnore
    protected void setCapLoc(int CaptainsLocation){ CapLoc = CaptainsLocation; }

    protected void setIsSubmerged(boolean Submerged){ isSubmerged = Submerged; }

    @JsonIgnore
    boolean getIsSubmerged(){ return  isSubmerged; }

}
