package battleship;

import static org.junit.Assert.*;

import org.junit.Test;

public class OceanTest {
	
	public Ocean test_OceanTest() {
		Ocean ocean = new Ocean();
		Ship[][] ships = ocean.getShipArray();
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				ships[i][j] = new EmptySea();
			}
		}
		Ship battleship = new Battleship();
		battleship.setBowRow(2);
		battleship.setBowColumn(5);
		battleship.setHorizontal(true);
		ships[2][5] = battleship;
		ships[2][6] = battleship;
		ships[2][7] = battleship;
		ships[2][8] = battleship;
		return ocean;
	}
	
	@Test
	public void test_Ocean() {
		Ocean ocean = new Ocean();
		assertEquals(ocean.getShotsFired(), 0);
		assertEquals(ocean.getHitCount(), 0);
		assertEquals(ocean.getShipsSunk(), 0);	
	}
	
	@Test
	public void test_placeAllShipsRandomly() {
		Ocean ocean1 = new Ocean();
		ocean1.placeAllShipsRandomly();
		Ocean ocean2 = new Ocean();
		ocean2.placeAllShipsRandomly();
		Ship[][] ship1 = ocean1.getShipArray();
		Ship[][] ship2 = ocean2.getShipArray();
		boolean shipsEqual = true;
		for(int i = 0; i < ship1.length; i++) {
			for(int j = 0; j < ship1.length; j++) {
				if(ship1[i][j] != ship2[i][j]) {
					shipsEqual = false;
				}
			}
		}
		assertFalse(shipsEqual);
	}
	
	@Test
	public void test_placeAllShipsRandomly2() {
		Ocean ocean1 = new Ocean();
		ocean1.placeAllShipsRandomly();
		Ocean ocean2 = new Ocean();
		ocean2.placeAllShipsRandomly();
		Ship[][] ship1 = ocean1.getShipArray();
		Ship[][] ship2 = ocean2.getShipArray();
		boolean shipsEqual = true;
		for(int i = 0; i < ship1.length; i++) {
			for(int j = 0; j < ship1.length; j++) {
				if(ship1[i][j] != ship2[i][j]) {
					shipsEqual = false;
				}
			}
		}
		assertFalse(shipsEqual);
	}
	
	@Test
	public void test_getShotsFired() {
		Ocean ocean = new Ocean();
		assertEquals(ocean.getShotsFired(), 0);	
	}
	
	@Test
	public void test_getShotsFired2() {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		ocean.shootAt(1, 1);
		ocean.shootAt(4, 6);
		ocean.shootAt(0, 9);
		assertEquals(ocean.getShotsFired(), 3);	
	}
	
	@Test
	public void test_getShotsFired3() {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		ocean.shootAt(1, 1);
		ocean.shootAt(4, 6);
		ocean.shootAt(0, 9);
		ocean.shootAt(2, 3);
		ocean.shootAt(9, 2);
		ocean.shootAt(0, 9);
		assertEquals(ocean.getShotsFired(), 6);	
	}
	
	@Test
	public void test_isOccupied() {
		Ocean ocean = test_OceanTest();
		assertFalse(ocean.isOccupied(0, 0));
	}
	
	@Test
	public void test_isOccupied2() {
		Ocean ocean = test_OceanTest();
		assertFalse(ocean.isOccupied(0, 10));
	}
	
	@Test
	public void test_isOccupied3() {
		Ocean ocean = test_OceanTest();
		assertTrue(ocean.isOccupied(2, 7));
	}
	
	@Test
	public void test_hasShipSunkAt() {
		Ocean ocean = test_OceanTest();
		assertFalse(ocean.hasSunkShipAt(2, 7));
	}
	
	@Test
	public void test_hasShipSunkAt2() {
		Ocean ocean = test_OceanTest();
		ocean.shootAt(2, 5);
		ocean.shootAt(2, 6);
		ocean.shootAt(2, 7);
		assertFalse(ocean.hasSunkShipAt(2, 7));
	}
	
	@Test
	public void test_hasShipSunkAt3() {
		Ocean ocean = test_OceanTest();
		ocean.shootAt(2, 5);
		ocean.shootAt(2, 6);
		ocean.shootAt(2, 7);
		ocean.shootAt(2, 8);
		assertTrue(ocean.hasSunkShipAt(2, 7));
	}
	
	@Test
	public void test_hasShipSunkAt4() {
		Ocean ocean = test_OceanTest();
		ocean.shootAt(2, 5);
		ocean.shootAt(2, 6);
		assertFalse(ocean.hasSunkShipAt(5, 8));
	}
	
	@Test
	public void test_getShipTypeAt() {
		Ocean ocean = test_OceanTest();
		assertEquals(ocean.getShipTypeAt(5, 3), "empty sea");
	}
	
	@Test
	public void test_getShipTypeAt2() {
		Ocean ocean = test_OceanTest();
		assertEquals(ocean.getShipTypeAt(2, 7), "battleship");
	}
	
	@Test
	public void test_shootAt() {
		Ocean ocean = test_OceanTest();
		assertFalse(ocean.shootAt(9, 9));
	}
	
	@Test
	public void test_shootAt2() {
		Ocean ocean = test_OceanTest();
		assertTrue(ocean.shootAt(2, 6));
	}
	
	@Test
	public void test_shootAt3() {
		Ocean ocean = test_OceanTest();
		assertFalse(ocean.shootAt(2, 9));
	}
	
	@Test
	public void test_getShipsSunk() {
		Ocean ocean = test_OceanTest();
		ocean.shootAt(2, 5);
		ocean.shootAt(2, 8);
		ocean.shootAt(8, 2);
		ocean.shootAt(9, 7);
		assertEquals(ocean.getShipsSunk(), 0);
	}
	
	@Test
	public void test_getShipsSunk2() {
		Ocean ocean = test_OceanTest();
		ocean.shootAt(2, 5);
		ocean.shootAt(2, 8);
		ocean.shootAt(8, 2);
		ocean.shootAt(9, 7);
		ocean.shootAt(2, 6);
		ocean.shootAt(2, 8);
		assertEquals(ocean.getShipsSunk(), 0);
	}
	
	@Test
	public void test_getShipsSunk3() {
		Ocean ocean = test_OceanTest();
		ocean.shootAt(2, 5);
		ocean.shootAt(2, 8);
		ocean.shootAt(8, 2);
		ocean.shootAt(9, 7);
		ocean.shootAt(2, 6);
		ocean.shootAt(2, 8);
		ocean.shootAt(2, 7);
		assertEquals(ocean.getShipsSunk(), 1);
	}
	
	@Test
	public void test_isGameOver() {
		Ocean ocean = test_OceanTest();
		ocean.shootAt(2, 5);
		ocean.shootAt(2, 8);
		ocean.shootAt(8, 2);
		ocean.shootAt(9, 7);
		ocean.shootAt(2, 6);
		ocean.shootAt(2, 8);
		assertFalse(ocean.isGameOver());
	}
	
	@Test
	public void test_isGameOver2() {
		Ocean ocean = test_OceanTest();
		ocean.shootAt(2, 5);
		ocean.shootAt(2, 8);
		ocean.shootAt(8, 2);
		ocean.shootAt(9, 7);
		ocean.shootAt(2, 6);
		ocean.shootAt(2, 8);
		ocean.shootAt(2, 7);
		assertTrue(ocean.isGameOver());
	}
	
	@Test
	public void test_getHitCount() {
		Ocean ocean = test_OceanTest();
		ocean.shootAt(2, 5);
		ocean.shootAt(2, 8);
		ocean.shootAt(8, 2);
		ocean.shootAt(9, 7);
		ocean.shootAt(2, 6);
		ocean.shootAt(2, 8);
		assertEquals(ocean.getHitCount(), 4);
	}
	
	@Test
	public void test_getHitCount2() {
		Ocean ocean = test_OceanTest();
		ocean.shootAt(2, 5);
		ocean.shootAt(2, 8);
		ocean.shootAt(8, 2);
		ocean.shootAt(9, 7);
		ocean.shootAt(2, 6);
		ocean.shootAt(2, 8);
		ocean.shootAt(1, 4);
		ocean.shootAt(2, 7);
		ocean.shootAt(5, 5);
		assertEquals(ocean.getHitCount(), 5);
	}
	
	@Test
	public void test_getHitCount3() {
		Ocean ocean = new Ocean();
		assertEquals(ocean.getHitCount(), 0);
	}

}
