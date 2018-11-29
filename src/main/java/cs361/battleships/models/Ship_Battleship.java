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
public class Ship_Battleship extends Ship_CaptainsQuarters {

    public Ship_Battleship() {
        this.setOccupiedSquares(new ArrayList<>());
        this.setKind("BATTLESHIP");
        this.setSize(4);
        this.setArmor(2);
        this.setCapLoc(2);
    }


}
