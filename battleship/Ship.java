package battleship;

/**
 * Abstract superclass for the different ship types
 * 
 * @author Christopher J Symons
 *
 */

public abstract class Ship {
	
	/**
	 * The row where the bow of the ship is located
	 */
	private int bowRow;
	
	/**
	 * The coulumn in which the bow of the ship is located
	 */
	private int bowColumn;
	
	/**
	 * The length of the ship
	 */
	private int length;
	
	 /**
	  * Whether the ship is place horizontally 
	  */
	private boolean horizontal;
	
	/**
	 * An array of whether each part of the ship has been hit. Each index represents one unit of the ships length
	 */
	protected boolean[] hit;
	
	/**
	 * Constructor for the ship class
	 * @param length of the ship
	 */
	public Ship(int length) {
		this.length = length;
		hit = new boolean[length];
		for(int i = 0; i < hit.length; i++) {
			hit[i] = false;
		}
	}
	
	/**
	 * Sets the row where the bow is located
	 * @param row
	 */
	public void setBowRow(int row) {
		this.bowRow = row;
	}
	
	/**
	 * Sets the columns where the bow of the ship is located
	 * @param column
	 */
	public void setBowColumn(int column) {
		this.bowColumn = column;
	}
	
	/**
	 * Sets whether the ship is horizontal or vertical
	 * @param horizontal, true if ship is horizontal, false otherwise
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	
	/**
	 * Takes the coordinates given and shoots at that location. Returns true if a ship is located in that location and is hit
	 * @param row of shot
	 * @param column of shot
	 * @return boolean, returns true if shot hits a ship, false if hits emptySea
	 */
	public boolean shootAt(int row, int column) {
		if(row - getBowRow() < 0 || row - getBowRow() > getLength() - 1) {
			throw new IllegalArgumentException("Ship not in this location");
		}
		if(column - getBowColumn() < 0 || column - getBowColumn() > getLength() - 1) {
			throw new IllegalArgumentException("Ship not in this location");
		}
		if(isHorizontal()) {
			hit[column - getBowColumn()] = true;
		} else {
			hit[row - getBowRow()] = true;
		}
		return true;
	}
	
	/**
	 * Determines if the part of the ship has been hit
	 * @param row of the grid
	 * @param column of the grid
	 * @return true if that element of the ship was hit, false if not
	 */
	public boolean isHit(int row, int column) {
		if(isHorizontal()) {
			return isSectionHit(column, getBowColumn());
		} else {
			return isSectionHit(row, getBowRow());
		}
	}
	
	/**
	 * Determines if one element of a ship is hit
	 * @param x column or row that has been shot at
	 * @param y column or row of the ships bow
	 * @return true if the element is hit, false if it is not hit
	 */
	private boolean isSectionHit(int x, int y) {
		if(isRealShip()) {
			if(hit[x - y] == true) {
				return true;
			} else {
				return false;
			}
		} else {
			if(hit[0] == true) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	/**
	 * Returns a boolean on whether the ship has been sunk
	 * @return true if ship has been sunk, false if the ship is still floating
	 */
	public boolean isSunk() {
		boolean sunk = true;
		for(int i = 0; i < getLength(); i++) {
			if(hit[i] == false) {
				sunk = false;
			}
		}
		return sunk;
	}
	
	/**
	 *  Returns true if ship is a real ship and not emptySea
	 * @return true for a real ship, false for emptySea
	 */
	public boolean isRealShip() {
		return true;
	}
	
	/**
	 * Gets the row where the bow of the ship is located
	 * @return bowRow
	 */
	public int getBowRow() {
		return bowRow;
	}
	
	/**
	 * Gets the column where the ships bow is located
	 * @return bowColumn
	 */
	public int getBowColumn() {
		return bowColumn;
	}
	
	/**
	 * Gets the length of the ship
	 * @return length
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * Determines if the ship is horizontal or not
	 * @return true if ship is horizontal, false if it is vertical
	 */
	public boolean isHorizontal() {
		return horizontal;
	}
	
	/**
	 * Abstract method to return the type of the ship
	 * @return the ship type
	 */
	public abstract String getShipType();

}
