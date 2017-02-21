package battleship;

public class Submarine extends Ship{

	/**
	 * Constructor for the submarine class. Sets the length of the ship to 1.
	 */
	public Submarine() {
		super(1);
	}
	
	/*
	 * (non-Javadoc)
	 * @see battleship.Ship#getShipType()
	 */
	@Override
	public String getShipType() {
		return "submarine";
	}
}
