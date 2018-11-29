package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Sets;
import com.mchange.v1.util.CollectionUtils;
import jdk.jshell.Snippet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Ship_Minesweeper extends Ship_CaptainsQuarters {

    public Ship_Minesweeper() {
        this.setOccupiedSquares(new ArrayList<>());
        this.setKind("MINESWEEPER");
        this.setSize(2);
        this.setArmor(1);
        this.setCapLoc(0);
    }

}
