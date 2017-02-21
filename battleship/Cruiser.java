package battleship;

public class Cruiser extends Ship {

	/**
	 * Constructor for the cruiser class. Sets the length of the ship to 3.
	 */
	public Cruiser() {
		super(3);
	}
	
	/*
	 * (non-Javadoc)
	 * @see battleship.Ship#getShipType()
	 */
	@Override
	public String getShipType() {
		return "cruiser";
	}
	
}
