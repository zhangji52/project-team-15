var isSetup = true;
var placedShips = 0;
var game;
var shipType;
var vertical;
var sonarChecked;
var sonarUnlock = 0;
var sonarCounter = 0;
var sonarButton = 0;

function makeGrid(table, isPlayer) {
    for (i=0; i<10; i++) {
        let row = document.createElement('tr');
        for (j=0; j<10; j++) {
            let column = document.createElement('td');
            column.addEventListener("click", cellClick);
            row.appendChild(column);
        }
        table.appendChild(row);
    }
}

function outputTextBox(input) {
    switch(input) {
        case 0:
            document.getElementById("textBox").value = "Ye missed scallywag!\n";
            break;
        case 1:
            document.getElementById("textBox").value = "You may have hit me, yar...\n";
            break;
        case 2:
            document.getElementById("textBox").value = "AAGHHHHH Ye sunk me precious booty!\n";
            break;
        case 3:
            document.getElementById("textBox").value = "Arrrgh ye placed a ship from the harbor\n";
            break;
        case 4:
            document.getElementById("textBox").value = "There is a fleet ahead! FIRE CANNONS!\n";
            break;
        case 5:
            document.getElementById("textBox").value = "You are out of sonar pulses\n";
            break;
        case 6:
            document.getElementById("textBox").value = "AAGHHHHH Ye sunk me precious booty!\n \n Sonar pulse now active\n \n 2 charges available!\n";
        case 7:
            document.getElementById("textBox").value = "You can now move your battleships!\n  Good luck Captain!";
    }

}

function markHits(board, elementId, surrenderText) {
    board.attacks.forEach((attack) => {
        let className;
        if (attack.result === "MISS"){
            className = "miss";
            if(elementId === "opponent")
                outputTextBox(0);
            }
        else if (attack.result === "HIT"){
            className = "hit"
            if(elementId === "opponent")
                outputTextBox(1);
            }
        else if (attack.result === "SUNK"){

            className = "hit"
            if(elementId === "opponent"){
                sonarUnlock++;
                if(sonarUnlock == 1)
                {
                    outputTextBox(6);
                }
                else {
                    outputTextBox(2);
                }
            }
            }
        else if (attack.result === "SURRENDER"){
            document.getElementById("textBox").value = surrenderText.toString();
            alert(surrenderText);
            }
        document.getElementById(elementId).rows[attack.location.row-1].cells[attack.location.column.charCodeAt(0) - 'A'.charCodeAt(0)].classList.add(className);
    });
}

function markPulse(board, elementId) {
    board.sonarResults.forEach((pulse) => {
        let className;
        if (pulse.result === "FOUND") {
            className = "occupied"
        } else if (pulse.result === "NOTFOUND") {
            className = "notfound"
        }
        document.getElementById(elementId).rows[pulse.location.row-1].cells[pulse.location.column.charCodeAt(0) - 'A'.charCodeAt(0)].classList.add(className);
    });
}

function redrawGrid() {
    Array.from(document.getElementById("opponent").childNodes).forEach((row) => row.remove());
    Array.from(document.getElementById("player").childNodes).forEach((row) => row.remove());
    makeGrid(document.getElementById("opponent"), false);
    makeGrid(document.getElementById("player"), true);
    if (game === undefined) {
        return;
    }

    game.playersBoard.ships.forEach((ship) => ship.occupiedSquares.forEach((square) => {
        document.getElementById("player").rows[square.row-1].cells[square.column.charCodeAt(0) - 'A'.charCodeAt(0)].classList.add("occupied");
    }));
    markHits(game.opponentsBoard, "opponent", "You won the game!\n");
    markHits(game.playersBoard, "player", "You lost the game :(\n");
    markPulse(game.opponentsBoard, "opponent")
}

var oldListener;
function registerCellListener(f) {
    let el = document.getElementById("player");
    for (i=0; i<10; i++) {
        for (j=0; j<10; j++) {
            let cell = el.rows[i].cells[j];
            cell.removeEventListener("mouseover", oldListener);
            cell.removeEventListener("mouseout", oldListener);
            cell.addEventListener("mouseover", f);
            cell.addEventListener("mouseout", f);
        }
    }
    oldListener = f;
}

function cellClick() {
    let row = this.parentNode.rowIndex + 1;
    let col = String.fromCharCode(this.cellIndex + 65);
    sonarChecked = document.getElementById("sonar_pulse").checked;

    if (isSetup) {
        sendXhr("POST", "/place", {game: game, shipType: shipType, x: row, y: col, isVertical: vertical}, function(data) {
            game = data;
            redrawGrid();
            placedShips++;
            if (placedShips == 4) {
                outputTextBox(4);
                isSetup = false;
                registerCellListener((e) => {});
            } else {
                outputTextBox(3);
            }
        });
    }

    else if(sonarUnlock >= 1 && sonarChecked == true && sonarCounter < 2)
    {

        sendXhr("POST", "/sonarPulse", {game: game,x: row, y: col}, function(data) {   //connects to Routes.java which connects to game.sonarPulse
            game = data;
            redrawGrid();
            sonarCounter++;
            console.log(sonarCounter);
            if(sonarCounter == 2)
            {
                outputTextBox(5);
            }
        });
    }

    else {
        sendXhr("POST", "/attack", {game: game, x: row, y: col}, function(data) {
            game = data;
            redrawGrid();
        })

    }
}

function sendXhr(method, url, data, handler) {
    var req = new XMLHttpRequest();
    req.addEventListener("load", function(event) {
        if (req.status != 200) {
            document.getElementById("textBox").value ="Invalid action\n"
            return;
        }
        handler(JSON.parse(req.responseText));
    });
    req.open(method, url);
    req.setRequestHeader("Content-Type", "application/json");
    req.send(JSON.stringify(data));
}

function place(size) {

    return function() {
        let row = this.parentNode.rowIndex;
        let col = this.cellIndex;
        let invalidPlacement = false;
        
        vertical = document.getElementById("is_vertical").checked;
        let table = document.getElementById("player");
        for (let i=0; i<size; i++) {
            let cell;
            if(vertical) {
                let tableRow = table.rows[row+i];
                if (tableRow === undefined) {
                    // ship is over the edge; let the back end deal with it
                    // we also need to turn them red though, so toggle this bool here
                    invalidPlacement = true;
                    break;
                }
                cell = tableRow.cells[col];
            } else {
                cell = table.rows[row].cells[col+i];
            }
            if (cell === undefined) {
                // ship is over the edge; let the back end deal with it
                // we also need to turn them red though, so toggle this bool here
                invalidPlacement = true;
                break;
            }
            if (cell.classList.contains("occupied")) {
                // we need to turn them red, so toggle this bool here
                invalidPlacement = true;
            }
            cell.classList.toggle("placed");
        }
        if (invalidPlacement) {
            for (let i = 0; i < size; i++) {
                let cell;
                if(vertical) {
                    let tableRow = table.rows[row+i];
                    if (tableRow === undefined) {
                        // ship is over the edge; let the back end deal with it
                        break;
                    }
                    cell = tableRow.cells[col];
                } else {
                    cell = table.rows[row].cells[col+i];
                }
                if (cell === undefined) {
                    // ship is over the edge; let the back end deal with it
                    break;
                }
                cell.classList.toggle("invalid");
            }
            
        }
    }
}
function initGame() {
    makeGrid(document.getElementById("opponent"), false);
    makeGrid(document.getElementById("player"), true);
    document.getElementById("place_minesweeper").addEventListener("click", function(e) {
        shipType = "MINESWEEPER";
       registerCellListener(place(2));
    });
    document.getElementById("place_destroyer").addEventListener("click", function(e) {
        shipType = "DESTROYER";
       registerCellListener(place(3));
    });
    document.getElementById("place_battleship").addEventListener("click", function(e) {
        shipType = "BATTLESHIP";
       registerCellListener(place(4));
    });
    document.getElementById("place_submarine").addEventListener("click", function(e) {
            shipType = "SUBMARINE";
           registerCellListener(place(4));
        });
    sendXhr("GET", "/game", {}, function(data) {
        game = data;
    });
};
