package battleship;

public class Battleship extends Ship {

	/**
	 * Constructor for the battleship class. Sets the length of the ship to 4.
	 */
	public Battleship() {
		super(4);
	}
	
	/*
	 * (non-Javadoc)
	 * @see battleship.Ship#getShipType()
	 */
	@Override
	public String getShipType() {
		return "battleship";
	}
	
}
