package ule.ed.recursivelist;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.*;

public class LinkedEDListTest {
  private LinkedEDList<String> lista;
  private LinkedEDList<String> listaElem;

  @Before
  public void test() {
    lista = new LinkedEDList<String>();
    listaElem = new LinkedEDList<String>();
    listaElem.addLast("1");
    listaElem.addLast("2");
    listaElem.addLast("3");
    listaElem.addLast("4");
    listaElem.addLast("5");
  }

  @Test
  public void test_Vacia() {
    assertEquals(0, lista.size());
  }

  @Test(expected = NullPointerException.class)
  public void test_AddLast() {
    lista.addLast("2");
    Assert.assertFalse(lista.isEmpty());
    Assert.assertEquals("(2 )", lista.toString());
    lista.addLast("3");
    Assert.assertEquals("(2 3 )", lista.toString());
    lista.addLast("7");
    Assert.assertEquals("(2 3 7 )", lista.toString());
    lista.addLast(null);
  }

  @Test(expected = EmptyCollectionException.class)
  public void test_RemoveLast() throws EmptyCollectionException {
    lista.addLast("1");
    Assert.assertEquals("(1 )", lista.toString());
    Assert.assertEquals("1", lista.removelast().toString());
    Assert.assertTrue(lista.isEmpty());
    Assert.assertEquals("5", listaElem.removelast().toString());
    Assert.assertEquals("(1 2 3 4 )", listaElem.toString());
    lista.removelast();
  }

  @Test
  public void test_RemovePenult() throws EmptyCollectionException {
    lista.addLast("1");
    Assert.assertEquals("(1 )", lista.toString());
    lista.addLast("2");
    Assert.assertEquals("(1 2 )", lista.toString());
    Assert.assertEquals("1", lista.removePenult().toString());

    Assert.assertEquals("4", listaElem.removePenult().toString());
    Assert.assertEquals("(1 2 3 5 )", listaElem.toString());
  }

  @Test(expected = EmptyCollectionException.class)
  public void test_RemovePenult_Empty() throws EmptyCollectionException {
    lista.removePenult();
  }

  @Test(expected = NoSuchElementException.class)
  public void test_RemovePenult_1Element() throws EmptyCollectionException {
    lista.addLast("1");
    Assert.assertEquals("(1 )", lista.toString());
    lista.removePenult();
  }  

  @Test
  public void test_RemoveFistElem() throws EmptyCollectionException {
    lista.addLast("1");
    lista.addLast("2");
    lista.addLast("1");
    lista.addLast("3");
    lista.addLast("1");
    Assert.assertEquals("(1 2 1 3 1 )", lista.toString());
    Assert.assertEquals("1", lista.removeFirstElem("1"));
    Assert.assertEquals("(2 1 3 1 )", lista.toString());
    Assert.assertEquals("1", lista.removeFirstElem("1"));
    Assert.assertEquals("(2 3 1 )", lista.toString());
  }

  @Test(expected = EmptyCollectionException.class)
  public void test_RemoveFirstElem_Empty() throws EmptyCollectionException {
    lista.removeFirstElem("A");
  }

  @Test(expected = NoSuchElementException.class)
  public void test_RemoveFirst_NoSuchElement() throws EmptyCollectionException {
    listaElem.removeFirstElem("A");
  }

  @Test(expected = NullPointerException.class)
  public void test_RemoveFirstElem_NullElement() throws EmptyCollectionException {
    listaElem.removeFirstElem(null);
  }

  @Test
  public void test_RemoveLastElem() throws EmptyCollectionException {
    lista.addLast("2");
    lista.addLast("3");
    lista.addLast("1");
    lista.addLast("3");
    lista.addLast("1");
    Assert.assertEquals("(2 3 1 3 1 )", lista.toString());
    Assert.assertEquals("1", lista.removeLastElem("1"));
    Assert.assertEquals("(2 3 1 3 )", lista.toString());
    Assert.assertEquals("2", lista.removeLastElem("2"));
    Assert.assertEquals("(3 1 3 )", lista.toString());
  }

  @Test(expected = EmptyCollectionException.class)
  public void test_RemoveLastElem_Empty() throws EmptyCollectionException {
    lista.removeLastElem("A");
  }

  @Test(expected = NoSuchElementException.class)
  public void test_RemoveLastElem_NoSuchElement() throws EmptyCollectionException {
    listaElem.removeLastElem("A");
  }

  @Test(expected = NullPointerException.class)
  public void test_RemoveLastElem_NullElement() throws EmptyCollectionException {
    listaElem.removeLastElem(null);
  }

  @Test(expected = NullPointerException.class)
  public void test_AddAntePenult() {
    lista.addAntePenult("1");
    Assert.assertEquals("(1 )", lista.toString());
    lista.addAntePenult("2");
    Assert.assertEquals("(2 1 )", lista.toString());
    lista.addAntePenult("3");
    Assert.assertEquals("(3 2 1 )", lista.toString());
    lista.addAntePenult("4");
    Assert.assertEquals("(3 4 2 1 )", lista.toString());
    lista.addAntePenult("5");
    Assert.assertEquals("(3 4 5 2 1 )", lista.toString());
    lista.addAntePenult(null);
  }

  @Test
  public void test_AddPos() {
    listaElem.addPos("8", 3);
    Assert.assertEquals("(1 2 8 3 4 5 )", listaElem.toString());
    listaElem.addPos("7", 1);
    Assert.assertEquals("(7 1 2 8 3 4 5 )", listaElem.toString());
    listaElem.addPos("6", 20);
    Assert.assertEquals("(7 1 2 8 3 4 5 6 )", listaElem.toString());
    lista.addPos("A", 5);
    Assert.assertEquals("(A )", lista.toString());
  }

  @Test(expected = NullPointerException.class)
  public void test_AddPos_NullElement() {
    listaElem.addPos(null, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_AddPos_BadPosition() {
    listaElem.addPos("8", 0);
  }

  @Test
  public void test_GetElemPos() {
    Assert.assertEquals("1", listaElem.getElemPos(1).toString());
    Assert.assertEquals("3", listaElem.getElemPos(3).toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_GetElemPos_NegativePos() {
    lista.getElemPos(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_GetElemPos_HighPos() {
    listaElem.getElemPos(12);
  }

  @Test(expected = NullPointerException.class)
  public void test_GetPosFirst() {
    listaElem.addLast("3");
    Assert.assertEquals(3, listaElem.getPosFirst("3"));
    listaElem.getPosFirst(null);
  }

  @Test(expected = NoSuchElementException.class)
  public void test_GetPosFirst_NoSuchElement() {
    listaElem.getPosFirst("6");
  }

  @Test(expected = NullPointerException.class)
  public void test_GetPosLast() {
    listaElem.addLast("3");
    listaElem.addLast("1");
    Assert.assertEquals(6, listaElem.getPosLast("3"));
    listaElem.getPosLast(null);
  }

  @Test(expected = NoSuchElementException.class)
  public void test_GetPosLast_NoSuchElement() {
    listaElem.getPosFirst("6");
  }

  @Test
  public void test_Reverse() {
    EDList<String> listReverse = listaElem.reverse();
    Assert.assertEquals("(5 4 3 2 1 )", listReverse.toString());
    Assert.assertEquals("(1 2 3 4 5 )", listaElem.toString());
    Assert.assertEquals("()", lista.reverse().toString());
  }

  @Test
  public void test_toStringFromUntilReverse() {
    Assert.assertEquals("(3 2 1 )", listaElem.toStringFromUntilReverse(3, 1));
    Assert.assertEquals("(5 4 3 )", listaElem.toStringFromUntilReverse(20, 3));
    Assert.assertEquals("()", lista.toStringFromUntilReverse(5, 2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_toStringFromUntilReverse_NegativeFrom() {
    listaElem.toStringFromUntilReverse(-1, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_toStringFromUntilReverse_NegativeUntil() {
    listaElem.toStringFromUntilReverse(2, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_toStringFromUntilReverse_HigherUntil() {
    listaElem.toStringFromUntilReverse(3, 5);
  }

  @Test
  public void test_toStringEvenOdd() {
    Assert.assertEquals("(2 4 5 3 1 )", listaElem.toStringEvenOdd());
  }

}
