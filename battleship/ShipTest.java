package battleship;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShipTest {

	@Test
	public void test_setBowRow() {
		Ship battleship = new Battleship();
		battleship.setBowRow(5);
		assertEquals(battleship.getBowRow(), 5);
	}
	
	@Test
	public void test_setBowColumn() {
		Ship battleship = new Battleship();
		battleship.setBowColumn(2);
		assertEquals(battleship.getBowColumn(), 2);
	}
	
	@Test
	public void test_setHorizontal() {
		Ship battleship = new Battleship();
		battleship.setHorizontal(false);
		assertEquals(battleship.isHorizontal(), false);
	}
	
	@Test
	public void test_getLengthBattleship() {
		Ship battleship = new Battleship();
		assertEquals(battleship.getLength(), 4);
	}
	
	@Test
	public void test_getLengthCruiser() {
		Ship cruiser = new Cruiser();
		assertEquals(cruiser.getLength(), 3);
	}
	
	@Test
	public void test_getLengthDestroyer() {
		Ship destroyer = new Destroyer();
		assertEquals(destroyer.getLength(), 2);
	}
	
	@Test
	public void test_getLengthSubmarine() {
		Ship submarine = new Submarine();
		assertEquals(submarine.getLength(), 1);
	}
	
	@Test
	public void test_shootAt1() {
		Ship battleship = new Battleship();
		battleship.setBowColumn(1);
		battleship.setBowRow(1);
		battleship.setHorizontal(true);
		assertTrue(battleship.shootAt(1, 4));
	}
	
	@Test
	public void test_shootAt2() {
		Ship battleship = new Battleship();
		battleship.setBowColumn(1);
		battleship.setBowRow(1);
		battleship.setHorizontal(true);
		assertTrue(battleship.shootAt(1, 4));
	}
	
	@Test
	public void test_shootAt3() {
		Ship battleship = new Battleship();
		battleship.setBowColumn(1);
		battleship.setBowRow(1);
		battleship.setHorizontal(true);
		assertTrue(battleship.shootAt(2, 1));
	}
	
	@Test
	public void test_shootAt4() {
		Ship sea = new EmptySea();
		sea.setBowColumn(1);
		sea.setBowRow(1);
		sea.setHorizontal(true);
		assertFalse(sea.shootAt(1, 1));
	}
	
	@Test
	public void test_isHit() {
		Ship ship = new Battleship();
		ship.setBowColumn(5);
		ship.setBowRow(4);
		ship.setHorizontal(false);
		assertFalse(ship.isHit(5, 5));
	}
	
	@Test
	public void test_isHit2() {
		Ship ship = new Battleship();
		ship.setBowColumn(5);
		ship.setBowRow(4);
		ship.setHorizontal(false);
		ship.shootAt(5, 5);
		assertTrue(ship.isHit(5, 5));
	}
	
	@Test
	public void test_isSunk() {
		Ship ship = new Cruiser();
		ship.setBowColumn(2);
		ship.setBowRow(6);
		ship.setHorizontal(true);
		ship.shootAt(6, 3);
		assertFalse(ship.isSunk());
	}
	
	@Test
	public void test_isSunk2() {
		Ship ship = new Cruiser();
		ship.setBowColumn(2);
		ship.setBowRow(6);
		ship.setHorizontal(true);
		ship.shootAt(6, 3);
		ship.shootAt(6, 2);
		ship.shootAt(6, 4);
		assertTrue(ship.isSunk());
	}
	
	@Test
	public void test_isRealShip() {
		Ship ship = new Submarine();
		assertTrue(ship.isRealShip());
	}
	
	@Test
	public void test_isRealShip2() {
		Ship ship = new EmptySea();
		assertFalse(ship.isRealShip());
	}
	
	@Test
	public void test_getBowRow() {
		Ship ship = new Destroyer();
		ship.setBowRow(4);
		assertEquals(ship.getBowRow(),4);
	}
	
	@Test
	public void test_getBowColumn() {
		Ship ship = new Destroyer();
		ship.setBowColumn(2);
		assertEquals(ship.getBowColumn(),2);
	}
	
	@Test
	public void test_isHorizontal() {
		Ship ship = new Destroyer();
		ship.setHorizontal(true);
		assertEquals(ship.isHorizontal(),true);
	}
	
	@Test
	public void test_isHorizontal2() {
		Ship ship = new Destroyer();
		ship.setHorizontal(false);
		assertEquals(ship.isHorizontal(),false);
	}
	
	@Test
	public void test_getShipType() {
		Ship ship = new Battleship();
		assertEquals(ship.getShipType(),"battleship");
	}
	
	@Test
	public void test_getShipType2() {
		Ship ship = new Cruiser();
		assertEquals(ship.getShipType(),"cruiser");
	}
	
	@Test
	public void test_getShipType3() {
		Ship ship = new Destroyer();
		assertEquals(ship.getShipType(),"destroyer");
	}
	
	@Test
	public void test_getShipType4() {
		Ship ship = new Submarine();
		assertEquals(ship.getShipType(),"submarine");
	}
	
	@Test
	public void test_getShipType5() {
		Ship ship = new EmptySea();
		assertEquals(ship.getShipType(),"empty sea");
	}
}
