package ule.ed.plane;

import static org.junit.Assert.*;

import org.junit.*;

public class AirplaneArrayImplTests {

	private AirplaneArrayImpl plane;

	@Before
	public void testBefore() throws Exception {
		plane = new AirplaneArrayImpl("A001", 3, 4);
	}

	@Test
	public void testContadores_AvionVacio() throws Exception {
		assertEquals(plane.getNumberOfChildren(), 0);
		assertEquals(plane.getNumberOfPassengers(), 0);
		assertEquals(plane.getNumberOfEmptySeats(), 12);
	}

	@Test
	public void testGetPassenger_ColumnaFilaMal() throws Exception {
		assertNull(plane.getPassenger(-1, 3));
	}

	@Test
	public void testContadores_AvionConPasajeros() throws Exception {
		AirplaneArrayImpl bigPlane = new AirplaneArrayImpl("A003", 8, 8);

		assertTrue(bigPlane.addPassenger(3, 4, "10203040X", "Pedro", 17));
		assertTrue(bigPlane.addPassenger(7, 8, "20473087G", "Eduardo", 10));
		assertTrue(bigPlane.addPassenger(2, 2, "10203050C", "Ana", 27));
		assertTrue(bigPlane.addPassenger("10473050B", "Juan", 14));
		assertTrue(bigPlane.addPassenger("10473097Y", "Sara", 15));

		assertEquals(bigPlane.getNumberOfChildren(), 4);
		assertEquals(bigPlane.getNumberOfPassengers(), 5);
		assertEquals(bigPlane.getNumberOfEmptySeats(), 59);
		assertEquals(bigPlane.getNumberOfColumnsWithChildren(), 4);
		assertEquals(bigPlane.getNumberOfColumnWithMoreChildren(), 1);
		assertEquals(bigPlane.getNumberOfRowsWithChildren(), 3);
		assertEquals(bigPlane.getNumberOfRowWithMoreChildren(), 1);
	}

	@Test(expected = NoneChildrenInAvionException.class)
	public void testGetNumberOfColumnWithMoreChildren_AvionVacio() throws Exception {
		assertEquals(plane.getNumberOfColumnWithMoreChildren(), 0);
	}

	@Test(expected = NoneChildrenInAvionException.class)
	public void testGetNumberOfRowWithMoreChildren_AvionVacio() throws Exception {
		assertEquals(plane.getNumberOfRowWithMoreChildren(), 0);
	}

	@Test(expected = NoneChildrenInAvionException.class)
	public void testGetNumberOfColumnsWithChildren_AvionVacio() throws Exception {
		assertEquals(plane.getNumberOfColumnsWithChildren(), 0);
	}

	@Test(expected = NoneChildrenInAvionException.class)
	public void testGetNumberOfRowsWithChildren_AvionVacio() throws Exception {
		assertEquals(plane.getNumberOfRowsWithChildren(), 0);
	}

	@Test
	public void testGetters_AvionVacio() throws Exception {
		assertEquals(plane.getName(), "A001");
		assertEquals(plane.getNumberOfColumns(), 4);
		assertEquals(plane.getNumberOfRows(), 3);
	}

	@Test
	public void testGetters_AvionConPasajeros() throws Exception {
		assertTrue(plane.addPassenger("10203040X", "Pedro", 17));
	}

	@Test
	public void testGetList_AvionVacio() throws Exception {
		assertEquals(plane.getListOfChildPassengers().toString(), "[]");
		assertEquals(plane.getListOfAdultsPassengers().toString(), "[]");
		assertEquals(plane.getListOfEmptySeats().toString(), "[(1,1), (1,2), (1,3), (1,4), (2,1), (2,2), (2,3), (2,4), (3,1), (3,2), (3,3), (3,4)]");
		assertEquals(plane.getListOfOccupiedSeats().toString(), "[]");
	}

	@Test
	public void testGetList_AvionConPasajeros() throws Exception {
		assertTrue(plane.addPassenger(1, 1, "10203040X", "Pedro", 17));
		assertTrue(plane.addPassenger(3, 2, "15263091S", "Eduardo", 23));
		assertTrue(plane.addPassenger("14233040U", "Cristina", 14));
		assertTrue(plane.addPassenger("45263091A", "Paco", 27));
		assertEquals(plane.getListOfChildPassengers().toString(), "[(10203040X-Pedro-17), (14233040U-Cristina-14)]");
		assertEquals(plane.getListOfAdultsPassengers().toString(), "[(45263091A-Paco-27), (15263091S-Eduardo-23)]");
		assertEquals(plane.getListOfEmptySeats().toString(), "[(1,2), (1,4), (2,1), (2,2), (2,3), (3,1), (3,3), (3,4)]");
		assertEquals(plane.getListOfOccupiedSeats().toString(), "[(1,1), (1,3), (2,4), (3,2)]");
	}

	@Test
	public void testCheckNearbySeats() throws Exception {
		AirplaneArrayImpl bigPlane = new AirplaneArrayImpl("A003", 8, 8);

		// Insertar pasajero que tiene libres up, down, left y right
		assertTrue(bigPlane.addPassenger(3, 4, "10203040X", "Pedro", 17));
		assertTrue(bigPlane.addPassenger(4, 3, "30203040X", "Sandra", 21));
		assertTrue(bigPlane.addPassenger(5, 4, "80203040A", "Bea", 21));
		assertTrue(bigPlane.addPassenger(4, 5, "81203040B", "Luis", 23));
		assertTrue(bigPlane.addPassenger(7, 6, "51203040C", "Eduardo", 29));
		assertTrue(bigPlane.addPassenger(7, 8, "81203940Y", "Sara", 24));
		assertTrue(bigPlane.addPassenger(6, 2, "11203940C", "Kevin", 27));
		assertTrue(bigPlane.addPassenger(8, 2, "81203940B", "Sandra", 24));

		// Insertar pasajero que tiene libres down, left y right
		assertFalse(bigPlane.addPassenger(6, 4, "15263091S", "Eduardo", 23));

		// Insertar pasajero que tiene libres up, left y right
		assertFalse(bigPlane.addPassenger(2, 4, "14233040U", "Cristina", 14));

		// Insertar pasajero que tiene libres up, down y right
		assertFalse(bigPlane.addPassenger(4, 6, "45263091A", "Paco", 27));

		// Insertar pasajero que tiene libres up, down y left
		assertFalse(bigPlane.addPassenger(4, 2, "34203040Y", "Alicia", 41));

		// Insertar pasajero que tiene libres up y down
		assertFalse(bigPlane.addPassenger(7, 7, "98203040U", "Juan", 87));

		// Insertar pasajero que tiene libres down y right
		assertFalse(bigPlane.addPassenger(5, 5, "58203040G", "Juan", 78));

		// Insertar pasajero que tiene libres down y left
		assertFalse(bigPlane.addPassenger(5, 3, "78203040H", "Ivan", 78));

		// Insertar pasajero que tiene libres up y right
		assertFalse(bigPlane.addPassenger(3, 5, "38608040S", "Eduardo", 38));

		// Insertar pasajero que tiene libres up y left
		assertFalse(bigPlane.addPassenger(3, 3, "68608040G", "Cristina", 38));

		// Insertar pasajero que tiene libres left y right
		assertFalse(bigPlane.addPassenger(7, 2, "98638040N", "Alicia", 48));

		// Insertar pasajero que no tiene nada libre
		assertFalse(bigPlane.addPassenger(4, 4, "78638040P", "Alicia", 58));

		// Los 3 casos restantes son revisados por otros tests

	}

	// Test insertar pasajero sin fila y columna
	@Test
	public void testInsertaPasajero() throws Exception {

		assertTrue(plane.addPassenger("10203040X", "Pedro", 17)); // inserta en fila=1, columna=1
		assertTrue(plane.isOccupiedSeat(1, 1));
		assertEquals(plane.getNumberOfChildren(), 1);
		assertEquals(plane.getNumberOfPassengers(), 1);
		assertEquals(plane.getNumberOfColumnWithMoreChildren(), 1);

	}

	// Test insertar pasajero con fila y columna (posición ocupada)
	@Test
	public void testInsertaPasajeroPosNoValida() throws Exception {

		assertTrue(plane.addPassenger(1, 1, "10203040X", "Pedro", 17)); // inserta en fila=1, columna=1
		assertTrue(plane.isOccupiedSeat(1, 1));
		assertEquals(plane.getNumberOfChildren(), 1);
		assertEquals(plane.getNumberOfPassengers(), 1);
		assertEquals(plane.getNumberOfColumnWithMoreChildren(), 1);
		assertFalse(plane.addPassenger(1, 2, "10203040X", "Pedro", 17)); // inserta en fila=1, columna=1
		assertFalse(plane.addPassenger(1, 1, "10203040Z", "Sara", 34));
		assertFalse(plane.addPassenger(-1, -1, "10203040A", "Juan", 13));
		assertFalse(plane.addPassenger(0, 0, "34203040Y", "Alicia", 41));
		assertFalse(plane.addPassenger(9, 9, "98203040U", "Juan", 87));

	}

	// Test insertar pasajero con fila y columna comprobando lista de posiciones
	// ocupadas
	@Test
	public void testInsertaPasajero_ListOccupied() throws Exception {

		assertTrue(plane.addPassenger(1, 1, "10203040X", "Pedro", 17)); // inserta en fila=1, columna=1
		assertTrue(plane.isOccupiedSeat(1, 1));
		assertEquals(plane.getNumberOfChildren(), 1);
		assertEquals(plane.getNumberOfPassengers(), 1);
		assertEquals(plane.getNumberOfColumnWithMoreChildren(), 1);
		assertEquals(plane.getListOfOccupiedSeats().toString(), "[(1,1)]");
	}

	@Test
	public void testInsertaPasajero_Repetido() throws Exception {

		assertTrue(plane.addPassenger(1, 1, "10203040X", "Pedro", 17)); // inserta en fila=1, columna=1
		assertTrue(plane.isOccupiedSeat(1, 1));
		assertFalse(plane.addPassenger(2, 2, "10203040X", "Pedro", 17)); // inserta en fila=1, columna=1

	}

	@Test 
	public void testInsertaPasajero_PosicionMalEspecificada() throws Exception {
		assertFalse(plane.addPassenger(0, 0, "10203040X", "Pedro", 17));
		assertFalse(plane.addPassenger(-1, 1, "10203040X", "Pedro", 17));
		assertFalse(plane.addPassenger(9, -9, "10203040X", "Pedro", 17));
		assertFalse(plane.addPassenger(9, 9, "14233040U", "Cristina", 14));
		assertFalse(plane.addPassenger(1, 9, "14233040U", "Cristina", 14));
		assertFalse(plane.addPassenger(9, 1, "14233040U", "Cristina", 14));
	}

	@Test
	public void testInsertarPasajero_AvionLleno() throws Exception {
		AirplaneArrayImpl smallPlane = new AirplaneArrayImpl("A002", 2, 2);
		assertTrue(smallPlane.addPassenger("10203040X", "Pedro", 17));
		assertTrue(smallPlane.addPassenger("15263091S", "Eduardo", 23));
		assertFalse(smallPlane.addPassenger("14233040U", "Cristina", 14));
	}

	@Test
	public void testIsOccupied() throws Exception {
		assertTrue(plane.addPassenger(1, 1, "10203040X", "Pedro", 17)); // inserta en fila=1, columna=1
		assertTrue(plane.isOccupiedSeat(1, 1));
		assertFalse(plane.isOccupiedSeat(2, 2));
		assertFalse(plane.isOccupiedSeat(0, 0));
		assertFalse(plane.isOccupiedSeat(-1, -1));
		assertFalse(plane.isOccupiedSeat(9, 9));

	}

	@Test
	public void testRemoverPasajero_Existente() {
		assertTrue(plane.addPassenger(1, 1, "10203040X", "Pedro", 17));
		assertTrue(plane.emptySeat(1, 1));
	}

	@Test
	public void testRemoverPasajero_Inexistente() {
		assertFalse(plane.emptySeat(1, 1));
	}	

	@Test
	public void testRemoverPasajero_PosicionMalEspecificada() throws Exception {
		assertFalse(plane.emptySeat(0, 0));
		assertFalse(plane.emptySeat(-1, -1));
		assertFalse(plane.emptySeat(9, 9));
	}

	@Test
	public void testToStringAvion() throws Exception {

		assertTrue(plane.addPassenger(1, 1, "10203040X", "Pedro", 17)); // inserta en fila=1, columna=1
		assertTrue(plane.isOccupiedSeat(1, 1));
		assertTrue(plane.addPassenger(2, 2, "10203050C", "Ana", 27)); // inserta en fila=1, columna=1
		assertEquals(plane.toString(), "[(10203040X-Pedro-17)(null)(null)(null)\n" +
				"(null)(10203050C-Ana-27)(null)(null)\n" +
				"(null)(null)(null)(null)\n" +
				"]");
	}
}
