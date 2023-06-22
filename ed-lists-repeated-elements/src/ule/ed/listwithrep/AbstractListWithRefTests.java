package ule.ed.listwithrep;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;

import ule.ed.exceptions.EmptyCollectionException;

public abstract class AbstractListWithRefTests {

	protected abstract <T> ListWithRep<T> createListWithRep();
	

	private ListWithRep<String> S1;

	private ListWithRep<String> S2;
	
	@Before
	public void setupListWithReps() {

		this.S1 = createListWithRep();
		
		this.S2 = createListWithRep();
		
		S2.add("ABC", 5);
		S2.add("123", 5);
		S2.add("XYZ", 10);
	}

	@Test
	public void testConstructionIsEmpty() {
		assertTrue(S1.isEmpty());
		assertFalse(S2.isEmpty());
	}
	
	@Test
	//Las nuevas instancias del TAD tienen tamaño cero: 
	public void testConstructionCardinality() {
		assertEquals(S1.size(), 0);
	}

	@Test
	public void testToStringInEmpty() {
		assertTrue(S1.isEmpty());
		assertEquals(S1.toString(), "()");
	}
	
	@Test
	public void testToString1elem() {
		assertTrue(S1.isEmpty());
		S1.add("A",3);
		assertEquals(S1.toString(), "(A A A )");
	}
	
	@Test
	//Añadir elementos con una multiplicidad incrementa su contador y el tamaño de la cola: ")
	public void testAddWithCount() {
		S1.add("ABC", 5);
		assertEquals(S1.count("ABC"), 5);
		assertEquals(S1.size(), 5);
		S1.add("ABC", 5);
		assertEquals(S1.count("ABC"), 10);
		assertEquals(S1.size(), 10);
		S1.add("123", 5);		
		assertEquals(S1.count("123"), 5);
		assertEquals(S1.count("ABC"), 10);
		assertEquals(S1.size(), 15);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testAddNegativeInstances() {
		S1.add("ABC", -1);
	}

	@Test (expected = NullPointerException.class)
	public void testAddWithTimesNullElement() {
		S1.add(null, 5);
	}

	@Test (expected = NullPointerException.class)
	public void testAddNullElement() {
		S1.add(null);
	}

	@Test
	public void testAddZeroInstances() {
		S1.add("ABC", 0);
		assertTrue(S1.isEmpty());
	}

	@Test
	public void testAddRepeatedElementNoTimes() {
		S1.add("ABC");
		S1.add("ABC");
		S1.add("123");
		S1.add("123");
		assertEquals(S1.count("ABC"), 2);
		assertEquals(S1.count("123"), 2);
	}

	@Test
	//Se pueden eliminar cero instancias de un elemento con remove(x, 0): ")
	public void testRemoveZeroInstances() throws EmptyCollectionException {
		assertEquals(0,S2.remove("ABC", 0));
	}

	@Test (expected = EmptyCollectionException.class)
	public void testRemoveEmptyList() throws EmptyCollectionException{
		S1.remove();
	}

	@Test (expected = EmptyCollectionException.class)
	public void testRemoveWithTimesEmptyList() throws EmptyCollectionException{
		S1.remove("ABC", 3);
	}

	@Test (expected = NullPointerException.class)
	public void testRemoveNullElement() throws EmptyCollectionException{
		S1.add("ABC");
		S1.remove(null, 3);
	}

	@Test (expected = NoSuchElementException.class)
	public void testRemoveNonexistentElement() throws EmptyCollectionException{
		S1.add("ABC");
		S1.remove("123", 3);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testRemoveNegativeInstances() throws EmptyCollectionException{
		S1.add("ABC", 5);
		S1.remove("ABC", -3);
	}

	@Test
	public void testRemove() throws  EmptyCollectionException {
		assertEquals(S2.remove("XYZ", 7), 7);
		assertEquals(S2.count("XYZ"), 3);
		assertEquals(S2.remove("XYZ", 7), 3);
		assertFalse(S2.contains("XYZ"));
		assertEquals(S2.remove(), 5);
		assertFalse(S2.contains("ABC"));
		assertEquals(S2.remove("123", 2), 2);
		assertEquals(S2.count("123"), 3);
		assertEquals(S2.remove("123", 3), 3);
		assertFalse(S2.contains("123"));
	}

	@Test
	public void testClear() {
		assertFalse(S2.isEmpty());
		S2.clear();
		assertTrue(S2.isEmpty());
	}

	@Test (expected = NullPointerException.class)
	public void testCountNullElement() {
		S2.count(null);
	}

	@Test
	public void testCount() {
		assertEquals(S2.count("XYZ"), 10);
		assertEquals(S2.count("NONEXISTENT"), 0);
	}

	@Test (expected = NullPointerException.class)
	public void testContainsNullElement() {
		S2.contains(null);
	}
	
	@Test
    public void testIterator() {
                  String e1 = "Hola";
                  String e2 = "Probando";
                  String e3 = "el";
                  String e4 = "iterador";
                  
                  S1.add(e1);
                  S1.add(e2, 1);
                  S1.add(e3, 2);    
                  S1.add(e4, 1);
                  
                  Iterator <String> oIt = S1.iterator();
                  StringBuilder cadena = new StringBuilder();
                  while(oIt.hasNext()) {
                      cadena.append(oIt.next()).append(" ");
                  }
                 
                  assertEquals("Hola Probando el iterador ", cadena.toString());
    }

	@Test (expected = NoSuchElementException.class)
	public void testIteratorHasNext() {
		Iterator <String> it = S1.iterator();
		it.next();
	}

	@Test
	public void testIteratorRep() {
		String e1 = "Hola";
		String e2 = "Probando";
		String e3 = "el";
		String e4 = "iterador";

		S1.add(e1);
		S1.add(e2, 1);
		S1.add(e3, 3);
		S1.add(e4, 4);

		Iterator <String> oIt = S1.iteratorRep();
		StringBuilder cadena = new StringBuilder();
		while(oIt.hasNext()) {
			cadena.append(oIt.next()).append(" ");
		}

		assertEquals("Hola Probando el el el iterador iterador iterador iterador ", cadena.toString());
	}

	@Test (expected = NoSuchElementException.class)
	public void testIteratorRepHasNext() {
		Iterator <String> it = S1.iteratorRep();
		it.next();
	}

}
