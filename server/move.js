const move = (grid, myPosition, enemyPosition, currentDirection) => {
  let ratingS = 0;
  let ratingL = 0;
  let ratingR = 0;

  let isFar =
    Math.sqrt(
      pow(relativeX(myPosition, enemyPosition), 2) +
        Math.pow(relativeY(myPosition, enemyPosition), 2)
    ) < 5;

  moveL = "s";
  moveR = "s";
  bikeS = new Bike(0, 0);
  bikeL = new Bike(0, 0);
  bikeR = new Bike(0, 0);

  switch (currentDirection) {
    case "u":
      moveL = "l";
      moveR = "r";
      bikeS = new Bike(myPosition.row - 1, myPosition.col);
      bikeL = new Bike(myPosition.row, myPosition.col - 1);
      bikeR = new Bike(myPosition.row, myPosition.col + 1);
      break;
    case "d":
      moveL = "r";
      moveR = "l";
      bikeS = new Bike(myPosition.row + 1, myPosition.col);
      bikeL = new Bike(myPosition.row, myPosition.col + 1);
      bikeR = new Bike(myPosition.row, myPosition.col - 1);
      break;
    case "r":
      moveL = "u";
      moveR = "d";
      bikeS = new Bike(myPosition.row, myPosition.col + 1);
      bikeL = new Bike(myPosition.row - 1, myPosition.col);
      bikeR = new Bike(myPosition.row + 1, myPosition.col);
      break;
    case "l":
      moveL = "d";
      moveR = "u";
      bikeS = new Bike(myPosition.row, myPosition.col - 1);
      bikeL = new Bike(myPosition.row + 1, myPosition.col);
      bikeR = new Bike(myPosition.row - 1, myPosition.col);
      break;
  }

  if (isFar) {
    ratingS +=
      distanceToNearestObstacle(grid, myPosition, currentDirection) *
      farWallDistance;
    ratingL +=
      distanceToNearestObstacle(grid, myPosition, moveL) * farWallDistance;
    ratingR +=
      distanceToNearestObstacle(grid, myPosition, moveR) * farWallDistance;
    ratingS += freeSpacesAtIndex(grid, bikeS) * farFreespace;
    ratingL += freeSpacesAtIndex(grid, bikeL) * farFreespace;
    ratingR += freeSpacesAtIndex(grid, bikeR) * farFreespace;
    ratingS += relativeX(bikeS, enemyPosition) * farRelativeEnemyX[0];
    ratingL += relativeX(bikeL, enemyPosition) * farRelativeEnemyX[1];
    ratingR += relativeX(bikeR, enemyPosition) * farRelativeEnemyX[2];
    ratingS += relativeY(bikeS, enemyPosition) * farRelativeEnemyY[0];
    ratingL += relativeY(bikeL, enemyPosition) * farRelativeEnemyY[1];
    ratingR += relativeY(bikeR, enemyPosition) * farRelativeEnemyY[2];
  } else {
    ratingS +=
      distanceToNearestObstacle(grid, myPosition, currentDirection) *
      closeWallDistance;
    ratingL +=
      distanceToNearestObstacle(grid, myPosition, moveL) * closeWallDistance;
    ratingR +=
      distanceToNearestObstacle(grid, myPosition, moveR) * closeWallDistance;
    ratingS += freeSpacesAtIndex(grid, bikeS) * closeFreespace;
    ratingL += freeSpacesAtIndex(grid, bikeL) * closeFreespace;
    ratingR += freeSpacesAtIndex(grid, bikeR) * closeFreespace;
    ratingS += relativeX(bikeS, enemyPosition) * closeRelativeEnemyX[0];
    ratingL += relativeX(bikeL, enemyPosition) * closeRelativeEnemyX[1];
    ratingR += relativeX(bikeR, enemyPosition) * closeRelativeEnemyX[2];
    ratingS += relativeY(bikeS, enemyPosition) * closeRelativeEnemyY[0];
    ratingL += relativeY(bikeL, enemyPosition) * closeRelativeEnemyY[1];
    ratingR += relativeY(bikeR, enemyPosition) * closeRelativeEnemyY[2];
  }

  // return based on highest rating values
  if (ratingS >= ratingL && ratingS >= ratingR) {
    return "s";
  } else if (ratingL >= ratingS && ratingL >= ratingR) {
    return "l";
  } else {
    return "r";
  }
};

const distanceToNearestObstacle = (state, curr, direction) => {
  switch (direction) {
    case "u":
      for (var i = curr.row - 1; i >= 0; i--) {
        if (state[i][curr.col] != ".") {
          return Math.abs(curr.row - i - 1);
        }
      }
    case "d":
      for (var i = curr.row + 1; i < state.length; i++) {
        if (state[i][curr.col] != ".") {
          return Math.abs(i - curr.row - 1);
        }
      }
    case "l":
      for (var i = curr.col - 1; i >= 0; i--) {
        if (state[curr.row][i] != ".") {
          return Math.abs(curr.col - i - 1);
        }
      }
    case "r":
      for (var i = curr.col + 1; i < state[0].length; i++) {
        if (state[curr.row][i] != ".") {
          return Math.abs(i - curr.col - 1);
        }
      }
    default:
      return -1;
  }
};

/*
Calculates the free spaces in a 3x3 square with the center as the Bike passed.
 */
const freeSpacesAtIndex = (state, b) => {
  var x = b.row;
  var y = b.col;

  var res = 0;

  for (var r = x - 1; r < x + 2; r++) {
    for (var c = y - 1; c < y + 2; c++) {
      // validate bounds
      if (r < 0 || r >= state.length || c < 0 || c >= state.length) continue;

      if (state[r][c] == ".") res++;
    }
  }

  return res;
};

/*
Calculates relative x position with respect to b1. Assumes the move is valid.
 */
const relative = (b1, b2) => b2.col - b1.col;

/*
Calculates relative y position with respect to b1. Assumes the move is valid.
 */
const relativeY = (b1, b2) => b2.row - b1.row;

export default {
  moveFuction: move
};
