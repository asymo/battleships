package battleship;

public class Destroyer extends Ship {
	
	/**
	 * Constructor for the destroyer class. Sets the length of the ship to 2.
	 */
	public Destroyer() {
		super(2);
	}
	
	/*
	 * (non-Javadoc)
	 * @see battleship.Ship#getShipType()
	 */
	@Override
	public String getShipType() {
		return "destroyer";
	}

}
