package battleship;

import java.util.Arrays;
import java.util.Random;

/**
 * Ocean class that displays the ocean as a 10x10 grid, showing if the a shot
 * has been fired and missed or if a ship has been hit or sunk.
 * 
 * @author Christopher J Symons
 *
 */

public class Ocean {

	/**
	 * An array of the ships placed in the ocean.
	 */
	private Ship[][] ships;

	/**
	 * The amount of shots that have been fired.
	 */
	private int shotsFired;

	/**
	 * How many shots have hit a ship.
	 */
	private int hitCount;

	/**
	 * How many ships have been sunk.
	 */
	private int shipsSunk;

	/**
	 * Constructs a new ocean
	 */
	public Ocean() {
		shotsFired = 0;
		hitCount = 0;
		shipsSunk = 0;
		ships = new Ship[10][10];
	}

	/**
	 * Places the ships randomly in the ocean without overlapping or touching
	 * another ship.
	 */
	public void placeAllShipsRandomly() {

		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				ships[i][j] = new EmptySea();
			}
		}

		int battleshipMax = 1;
		int cruiserMax = 2;
		int destroyerMax = 3;
		int submarineMax = 4;
		int totalShips = battleshipMax + cruiserMax + destroyerMax + submarineMax;
		int battleshipCreated = 0;
		int cruiserCreated = 0;
		int destroyerCreated = 0;
		int submarineCreated = 0;
		boolean shipsOnOcean = false;

		while (!shipsOnOcean) {
			/*
			 * Determines which boats have been created
			 */
			Ship ship;
			if (battleshipCreated < battleshipMax) {
				ship = new Battleship();
				battleshipCreated++;
			} else if (cruiserCreated < cruiserMax) {
				ship = new Cruiser();
				cruiserCreated++;
			} else if (destroyerCreated < destroyerMax) {
				ship = new Destroyer();
				destroyerCreated++;
			} else {
				if (submarineCreated < submarineMax) {
					ship = new Submarine();
					submarineCreated++;
				} else {
					shipsOnOcean = true;
					break;
				}

			}

			boolean shipPlaced = false;
			while (!shipPlaced) {

				/*
				 * Randomly selects a row, column and a boolean for is the ship
				 * is horizontal
				 */
				Random random = new Random();
				boolean horizontal = random.nextBoolean();
				int col = random.nextInt(ships[0].length - ship.getLength());
				int row = random.nextInt(ships.length - ship.getLength());

				shipPlaced = checkGrid(row, col, horizontal, totalShips);

				/*
				 * add ship to Ship[][]
				 */
				if (shipPlaced) {
					ship.setBowRow(row);
					ship.setBowColumn(col);
					ship.setHorizontal(horizontal);
					ships[row][col] = ship;
					for (int j = 1; j < ship.getLength(); j++) {
						if (horizontal) {
							ships[row][col + j] = ships[row][col];
						} else {
							ships[row + j][col] = ships[row][col];
						}
					}
				}
			}

		}

	}
	
	/**
	  * checks grid around ship to see if it is empty sea
	  * @param row random row which has been generated
	  * @param col random column which has been generated
	  * @param horizontal true if ship is horizontal, false if vertical 
	  * @param totalShips the total amount of ships to be created
	  * @return boolean true if ship can be placed at the location specified
	  */
	private boolean checkGrid(int row, int col, boolean horizontal, int totalShips) {
		boolean shipPlaced = false;
		for (int i = 0; i < totalShips; i++) {
			int newRow = row;
			int newCol = col;
			if (horizontal) {
				newCol += i;
			} else {
				newRow += i;
			}
			boolean a, b, c, d;
			a = isOccupied(newRow - 1, newCol);
			b = isOccupied(newRow + 1, newCol);
			c = isOccupied(newRow, newCol - 1);
			d = isOccupied(newRow, newCol + 1);
			if (i == 0) {
				if ((a || b || c || d) == false) {
					shipPlaced = true;
				} else {
					shipPlaced = false;
					break;
				}
			} else if (i < totalShips && horizontal) {
				if ((a || b || d) == false) {
					shipPlaced = true;
				} else {
					shipPlaced = false;
					break;
				}
			} else {
				if ((b || c || d) == false) {
					shipPlaced = true;
				} else {
					shipPlaced = false;
					break;
				}
			}
		}
		return shipPlaced;
	}

	/**
	 * Returns true if current location contains a ship
	 * 
	 * @param row,
	 *            the row selected
	 * @param column,
	 *            the columns selected
	 * @return true if space is occupied and false if empty
	 */
	public boolean isOccupied(int row, int column) {
		if (row < 0 || column < 0 || row > 9 || column > 9) {
			return false;
		} else {
			if (ships[row][column].isRealShip()) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * Determined whether a ship has been sunk if a shot occurred
	 * 
	 * @param row,
	 *            the row selected
	 * @param column,
	 *            the columns selected
	 * @return true if the hit causes the ship to sink, false if the ship still
	 *         floats
	 */
	public boolean hasSunkShipAt(int row, int column) {
		return ships[row][column].isSunk();
	}

	/**
	 * Returns the typse of sip that is located at a specified location
	 * 
	 * @param row,
	 *            the row selected
	 * @param column,
	 *            the columns selected
	 * @return the name of the ship
	 */
	public String getShipTypeAt(int row, int column) {
		return ships[row][column].getShipType();
	}

	/**
	 * Shoots at the location provided.
	 * 
	 * @param row,
	 *            the row selected
	 * @param column,
	 *            the columns selected
	 * @return true if location contain a real ship, false if it is empty sea
	 */
	public boolean shootAt(int row, int column) {
		shotsFired++;
		boolean alreadySunk = ships[row][column].isSunk();
		boolean hit;
		try {
			hit = ships[row][column].shootAt(row, column);
		}
		catch (IllegalArgumentException exception) {
			System.out.println(exception);
		}
		hit = ships[row][column].shootAt(row, column);
		if (hit) {
			hitCount++;
			if(ships[row][column].isSunk() && !alreadySunk) {
				shipsSunk++;
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Get the amount of shots fired
	 * 
	 * @return shotsFired
	 */
	public int getShotsFired() {
		return shotsFired;
	}

	/**
	 * Gets the amount of shots that have hit a ship
	 * 
	 * @return hitCount
	 */
	public int getHitCount() {
		return hitCount;
	}

	/**
	 * Gets how many ships have been sunk
	 * 
	 * @return shipsSunk
	 */
	public int getShipsSunk() {
		return shipsSunk;
	}

	/**
	 * Gets the array of Ships
	 * 
	 * @return an array of ships
	 */
	public Ship[][] getShipArray() {
		return ships;
	}

	/**
	 * Determines if the game is over
	 * 
	 * @return true if all ships have been sunk or false if not
	 */
	public boolean isGameOver() {
		for(int i = 0; i < ships.length; i++) {
			for(int j = 0; j < ships[i].length; j++) {
				if(ships[i][j].isRealShip()) {
					if(!ships[i][j].isSunk()) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Prints the ocean displaying the row and column number(0-9) on the sides
	 * and whether each location is empty sea, hit a ship or a sunken ship.
	 */
	public void print() {
		for (int i = 0; i < ships.length + 1; i++) {
			if (i == 0) {
				for (int j = 0; j < ships.length + 1; j++) {
					if (j == 0) {
						System.out.print(" ");
					} else {
						System.out.print(j - 1);
					}
				}
			} else {
				for (int j = 0; j < ships.length + 1; j++) {
					if (j == 0) {
						System.out.print(i - 1);
					} else {
						Ship ship = ships[i - 1][j - 1];
						if (ship.isRealShip()) {
							if (ship.isSunk()) {
								System.out.print("x");
							} else if (ship.isHit(i - 1, j - 1)) {
								System.out.print("S");
							} else {
								System.out.print(".");
							}
						} else {
							if (ship.isHit(i - 1, j - 1)) {
								System.out.print("-");
							} else {
								System.out.print(".");
							}
						}
					}
				}
			}
			System.out.println();
		}
	}

}
