package battleship;

/**
 * Represents a place on the board which is empty sea
 * 
 * @author Christopher J Symons
 *
 */

public class EmptySea extends Ship {
	
	/**
	 * Constructor for the EmptySea class
	 */
	public EmptySea() {
		super(1);
	}
	
	/*
	 * (non-Javadoc)
	 * @see battleship.Ship#shootAt(int, int)
	 */
	@Override 
	public boolean shootAt(int row, int column) {
		hit[0] = true;
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see battleship.Ship#isSunk()
	 */
	@Override
	public boolean isSunk() {
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see battleship.Ship#isRealShip()
	 */
	@Override
	public boolean isRealShip() {
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see battleship.Ship#getShipType()
	 */
	@Override
	public String getShipType() {
		return "empty sea";
	}

}
