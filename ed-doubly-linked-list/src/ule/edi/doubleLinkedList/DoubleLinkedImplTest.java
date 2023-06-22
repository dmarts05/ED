package ule.edi.doubleLinkedList;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;

import ule.edi.exceptions.EmptyCollectionException;

public class DoubleLinkedImplTest {
    DoubleLinkedListImpl<String> lv;
    DoubleLinkedListImpl<String> listaConElems;
    DoubleLinkedListImpl<String> listaConstructor;

    @Before
    public void antesDe() {
        lv = new DoubleLinkedListImpl<String>();
        listaConElems = new DoubleLinkedListImpl<String>();
        listaConElems.addFirst("D");
        listaConElems.addFirst("B");
        listaConElems.addFirst("A");
        listaConElems.addFirst("C");
        listaConElems.addFirst("B");
        listaConElems.addFirst("A");
        listaConstructor = new DoubleLinkedListImpl<String>("1", "2", "3");
    }

    @Test
    public void isEmptyTest() {
        Assert.assertTrue(lv.isEmpty());
        Assert.assertEquals(0, lv.size());
        Assert.assertFalse(listaConElems.isEmpty());
        Assert.assertEquals(6, listaConElems.size());

    }

    @Test
    public void clearTest() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addFirst("2");
        lista.addLast("3");
        lista.addLast("7");
        Assert.assertEquals("(2 3 7 )", lista.toString());
        lista.clear();
        Assert.assertTrue(lista.isEmpty());
        Assert.assertEquals("()", lista.toString());
    }

    @Test
    public void containsTest() {
        Assert.assertFalse(lv.contains("A"));
        Assert.assertTrue(listaConElems.contains("A"));
        Assert.assertTrue(listaConElems.contains("B"));
        Assert.assertTrue(listaConElems.contains("B"));
        Assert.assertTrue(listaConElems.contains("D"));
        Assert.assertFalse(listaConElems.contains("Z"));

    }

    @Test
    public void addFirstTest() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        Assert.assertTrue(lista.isEmpty());
        lista.addFirst("2");
        Assert.assertFalse(lista.isEmpty());
        Assert.assertEquals("(2 )", lista.toString());
        lista.addFirst("3");
        Assert.assertEquals("(3 2 )", lista.toString());
        lista.addFirst("7");
        Assert.assertEquals("(7 3 2 )", lista.toString());
    }

    @Test(expected = NullPointerException.class)
    public void addElementoNuloFirstTest() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addFirst(null);
    }

    @Test
    public void addLastTest() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        Assert.assertTrue(lista.isEmpty());
        lista.addFirst("2");
        Assert.assertFalse(lista.isEmpty());
        Assert.assertEquals("(2 )", lista.toString());
        lista.addLast("3");
        Assert.assertEquals("(2 3 )", lista.toString());
        lista.addLast("7");
        Assert.assertEquals("(2 3 7 )", lista.toString());
    }

    @Test(expected = NullPointerException.class)
    public void addElementoNuloLastTest() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addLast(null);
    }

    @Test
    public void testaddPos() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addFirst("2");
        lista.addLast("3");
        lista.addLast("7");
        Assert.assertEquals("(2 3 7 )", lista.toString());
        lista.addPos("2", 3);
        Assert.assertEquals("(2 3 2 7 )", lista.toString());
        lista.addPos("1", 1);
        Assert.assertEquals("(1 2 3 2 7 )", lista.toString());
        lista.addPos("5", 6);
        Assert.assertEquals("(1 2 3 2 7 5 )", lista.toString());
        lista.addPos("6", 10);
        Assert.assertEquals("(1 2 3 2 7 5 6 )", lista.toString());
    }

    @Test
    public void testaddPosEmptyList() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addPos("A", 10);
        Assert.assertEquals("(A )", lista.toString());
    }

    @Test(expected = NullPointerException.class)
    public void testaddPosNulo() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addPos(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testaddPosPosicionNegativa() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addPos("3", -1);
    }

    @Test
    public void testaddBefore() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addFirst("2");
        lista.addLast("3");
        lista.addLast("7");
        Assert.assertEquals("(2 3 7 )", lista.toString());
        lista.addBefore("1", "2");
        Assert.assertEquals("(1 2 3 7 )", lista.toString());
        lista.addBefore("2", "2");
        Assert.assertEquals("(1 2 2 3 7 )", lista.toString());
        lista.addBefore("2", "7");
        Assert.assertEquals("(1 2 2 3 2 7 )", lista.toString());
    }

    @Test(expected = NullPointerException.class)
    public void testaddBeforeTargetNulo() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addFirst("2");
        lista.addLast("3");
        lista.addLast("7");
        lista.addBefore("3", null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testaddBeforeInexistente() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addFirst("2");
        lista.addLast("3");
        lista.addLast("7");
        lista.addBefore("3", "4");
    }

    @Test
    public void testGetElemPos() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addFirst("2");
        lista.addLast("3");
        lista.addLast("7");
        lista.addBefore("1", "2");
        lista.addBefore("2", "2");
        lista.addBefore("2", "7");
        Assert.assertEquals("(1 2 2 3 2 7 )", lista.toString());
        Assert.assertEquals("2", lista.getElemPos(2));
        Assert.assertEquals("7", lista.getElemPos(6));
        Assert.assertEquals("1", lista.getElemPos(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetElemPosExceso() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addFirst("2");
        lista.addLast("3");
        lista.addLast("7");
        Assert.assertEquals("2", lista.getElemPos(20));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetElemPosDefecto() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addFirst("2");
        lista.addLast("3");
        lista.addLast("7");
        Assert.assertEquals("2", lista.getElemPos(0));
    }

    @Test
    public void testGetPosFirst() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addFirst("2");
        Assert.assertEquals("(2 )", lista.toString());
        lista.addLast("3");
        Assert.assertEquals("(2 3 )", lista.toString());
        lista.addLast("1");
        Assert.assertEquals("(2 3 1 )", lista.toString());
        lista.addBefore("1", "2");
        Assert.assertEquals("(1 2 3 1 )", lista.toString());
        lista.addBefore("2", "2");
        Assert.assertEquals("(1 2 2 3 1 )", lista.toString());
        lista.addBefore("2", "1");
        Assert.assertEquals("(2 1 2 2 3 1 )", lista.toString());
        Assert.assertEquals(2, lista.getPosFirst("1"));
        Assert.assertEquals(1, lista.getPosFirst("2"));
    }

    @Test(expected = NullPointerException.class)
    public void testGetPosFirstNulo() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addFirst("2");
        lista.addLast("3");
        lista.addLast("1");
        Assert.assertEquals(1, lista.getPosFirst(null));
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetPosFirstInexistente() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addFirst("2");
        lista.addLast("3");
        lista.addLast("1");
        Assert.assertEquals(1, lista.getPosFirst("R"));
    }

    @Test
    public void testGetPosLast() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addFirst("2");
        lista.addLast("3");
        lista.addLast("1");
        lista.addBefore("1", "2");
        lista.addBefore("2", "2");
        lista.addBefore("2", "1");
        Assert.assertEquals("(2 1 2 2 3 1 )", lista.toString());
        Assert.assertEquals(6, lista.getPosLast("1"));
        Assert.assertEquals(4, lista.getPosLast("2"));
    }

    @Test(expected = NullPointerException.class)
    public void testGetPosLastNulo() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addFirst("2");
        lista.addLast("3");
        lista.addLast("1");
        Assert.assertEquals(1, lista.getPosLast(null));
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetPosLastInexistente() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addFirst("2");
        lista.addLast("3");
        lista.addLast("1");
        Assert.assertEquals(1, lista.getPosLast("R"));
    }

    @Test(expected = EmptyCollectionException.class)
    public void testRemoveLast() throws EmptyCollectionException {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addFirst("2");
        lista.addLast("3");
        lista.addLast("7");
        Assert.assertEquals("(2 3 7 )", lista.toString());
        Assert.assertEquals("7", lista.removeLast());
        Assert.assertEquals("(2 3 )", lista.toString());
        Assert.assertEquals("3", lista.removeLast());
        Assert.assertEquals("(2 )", lista.toString());
        Assert.assertEquals("2", lista.removeLast());
        Assert.assertEquals("()", lista.toString());
        Assert.assertEquals("2", lista.removeLast());
    }

    @Test(expected = EmptyCollectionException.class)
    public void testRemovePos() throws EmptyCollectionException {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addFirst("2");
        lista.addLast("3");
        lista.addLast("1");
        lista.addFirst("4");
        Assert.assertEquals("4", lista.removePos(1));
        Assert.assertEquals("(2 3 1 )", lista.toString());
        Assert.assertEquals("3", lista.removePos(2));
        Assert.assertEquals("(2 1 )", lista.toString());
        Assert.assertEquals("1", lista.removePos(2));
        Assert.assertEquals("(2 )", lista.toString());
        Assert.assertEquals("2", lista.removePos(1));
        Assert.assertEquals("()", lista.toString());
        lista.removePos(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemovePosDefecto() throws EmptyCollectionException {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addFirst("2");
        lista.addLast("3");
        lista.addLast("1");
        Assert.assertEquals(1, lista.removePos(5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemovePosExceso() throws EmptyCollectionException {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addFirst("2");
        lista.addLast("3");
        lista.addLast("1");
        Assert.assertEquals(1, lista.removePos(-5));
    }

    @Test
    public void testRemoveN() throws EmptyCollectionException {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addFirst("2");
        lista.addLast("3");
        lista.addLast("1");
        lista.addBefore("1", "2");
        lista.addBefore("2", "2");
        lista.addBefore("2", "1");
        Assert.assertEquals("(2 1 2 2 3 1 )", lista.toString());
        Assert.assertEquals(3, lista.removeN("2", 5));
        Assert.assertEquals("(1 3 1 )", lista.toString());
        Assert.assertEquals(2, lista.removeN("1", 3));
        Assert.assertEquals("(3 )", lista.toString());
        Assert.assertEquals(1, lista.removeN("3", 1));
        Assert.assertEquals("()", lista.toString());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveNNulo() throws EmptyCollectionException {
        listaConElems.removeN(null, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNTimesInvalido() throws EmptyCollectionException {
        listaConElems.removeN("A", -1);
    }

    @Test(expected = EmptyCollectionException.class)
    public void testRemoveNListaVacia() throws EmptyCollectionException {
        lv.removeN("A", 3);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveNInexistente() throws EmptyCollectionException {
        listaConElems.removeN("1", 3);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveSecond() throws EmptyCollectionException {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addFirst("2");
        lista.addLast("3");
        Assert.assertEquals("(2 3 )", lista.toString());
        Assert.assertEquals("3", lista.removeSecond());
        lista.removeSecond();
    }

    @Test(expected = EmptyCollectionException.class)
    public void testRemoveSecondListaVacia() throws EmptyCollectionException {
        lv.removeSecond();
    }

    @Test
    public void testContains() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addFirst("2");
        lista.addLast("3");
        lista.addLast("1");
        Assert.assertTrue(lista.contains("1"));
        Assert.assertTrue(lista.contains("2"));
        Assert.assertFalse(lista.contains("4"));
    }

    @Test(expected = NullPointerException.class)
    public void testContainsNulo() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addFirst("2");
        lista.addLast("3");
        lista.addLast("1");
        lista.contains(null);
    }

    @Test
    public void testSize() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        Assert.assertEquals(0, lista.size());
        lista.addFirst("2");
        lista.addLast("3");
        lista.addLast("1");
        Assert.assertEquals(3, lista.size());
    }

    @Test
    public void testCopy() {
        DoubleLinkedListImpl<String> lista1 = new DoubleLinkedListImpl<String>();
        lista1.addFirst("2");
        lista1.addLast("3");
        lista1.addLast("1");
        DoubleLinkedListImpl<String> lista2 = (DoubleLinkedListImpl<String>) lista1.copy();
        Assert.assertEquals(lista1.toString(), lista2.toString());
    }

    @Test
    public void testSameContent() {
        DoubleLinkedListImpl<String> lista1 = new DoubleLinkedListImpl<String>();
        lista1.addLast("A");
        lista1.addLast("B");
        lista1.addLast("C");
        lista1.addLast("D");
        lista1.addLast("E");
        Assert.assertEquals("(A B C D E )", lista1.toString());
        DoubleLinkedListImpl<String> lista2 = new DoubleLinkedListImpl<String>();
        lista2.addLast("B");
        lista2.addLast("A");
        lista2.addLast("C");
        lista2.addLast("E");
        lista2.addLast("D");
        Assert.assertEquals("(B A C E D )", lista2.toString());
        DoubleLinkedListImpl<String> lista3 = new DoubleLinkedListImpl<String>();
        lista3.addLast("B");
        lista3.addLast("A");
        lista3.addLast("F");
        lista3.addLast("E");
        lista3.addLast("D");
        Assert.assertEquals("(B A F E D )", lista3.toString());

        Assert.assertTrue(lista1.sameContent(lista2));
        Assert.assertFalse(lista1.sameContent(listaConElems));
        Assert.assertFalse(lista1.sameContent(lista3));
    }

    @Test
    public void testMaxRepeated() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addFirst("2");
        lista.addLast("3");
        lista.addLast("1");
        Assert.assertEquals("(2 3 1 )", lista.toString());
        lista.addBefore("1", "2");
        Assert.assertEquals("(1 2 3 1 )", lista.toString());
        lista.addBefore("2", "2");
        Assert.assertEquals("(1 2 2 3 1 )", lista.toString());
        lista.addBefore("2", "1");
        Assert.assertEquals("(2 1 2 2 3 1 )", lista.toString());
        Assert.assertEquals("(1 3 2 2 1 2 )", lista.toStringReverse());
        Assert.assertEquals(3, lista.maxRepeated());
        lista.clear();
        Assert.assertEquals(0, lista.maxRepeated());
    }

    @Test
    public void testToString() {
        Assert.assertEquals("()", lv.toString());
    }

    @Test
    public void testToStringFromUntil() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addFirst("2");
        lista.addLast("3");
        lista.addLast("1");
        lista.addBefore("1", "2");
        lista.addBefore("2", "2");
        lista.addBefore("2", "1");
        Assert.assertEquals("(2 1 2 2 3 1 )", lista.toString());
        Assert.assertEquals("(2 1 2 )", lista.toStringFromUntil(1, 3));
        Assert.assertEquals("(2 1 2 2 3 1 )", lista.toStringFromUntil(1, 6));
        Assert.assertEquals("(2 1 2 2 3 1 )", lista.toStringFromUntil(1, 10));
        Assert.assertEquals("(2 3 1 )", lista.toStringFromUntil(4, 7));
        Assert.assertEquals("()", lista.toStringFromUntil(8, 10));
    }

    @Test
    public void testToStringFromUntilReverse() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addLast("A");
        lista.addLast("B");
        lista.addLast("C");
        lista.addLast("D");
        lista.addLast("E");
        Assert.assertEquals("(A B C D E )", lista.toString());
        Assert.assertEquals("(C B A )", lista.toStringFromUntilReverse(3, 1));
        Assert.assertEquals("(E D C )", lista.toStringFromUntilReverse(10, 3));
        Assert.assertEquals("(E )", lista.toStringFromUntilReverse(20, 5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testToStringFromUntilFromNegativo1() {
        listaConElems.toStringFromUntil(-3, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testToStringFromUntilFromNegativo2() {
        listaConElems.toStringFromUntil(3, -4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testToStringFromUntilFromInvertido() {
        listaConElems.toStringFromUntil(4, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testToStringFromUntilFromReverseNegativo1() {
        listaConElems.toStringFromUntilReverse(-5, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testToStringFromUntilFromReverseNegativo2() {
        listaConElems.toStringFromUntilReverse(4, -3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testToStringFromUntilFromReverseInvertido() {
        listaConElems.toStringFromUntilReverse(3, 4);
    }

    @Test(expected = NoSuchElementException.class)
    public void testIterator() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addLast("A");
        lista.addLast("B");
        lista.addLast("C");
        lista.addLast("D");
        lista.addLast("E");
        Assert.assertEquals("(A B C D E )", lista.toString());
        Iterator<String> iterator = lista.iterator();
        StringBuilder output = new StringBuilder("(");
        while (iterator.hasNext()) {
            output.append(iterator.next()).append(" ");
        }
        output.append(")");
        Assert.assertEquals("(A B C D E )", output.toString());
        Assert.assertFalse(iterator.hasNext());
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testReverseIterator() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addLast("A");
        lista.addLast("B");
        lista.addLast("C");
        lista.addLast("D");
        lista.addLast("E");
        Assert.assertEquals("(A B C D E )", lista.toString());
        Iterator<String> iterator = lista.reverseIterator();
        StringBuilder output = new StringBuilder("(");
        while (iterator.hasNext()) {
            output.append(iterator.next()).append(" ");
        }
        output.append(")");
        Assert.assertEquals("(E D C B A )", output.toString());
        Assert.assertFalse(iterator.hasNext());
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testOddPositionsIterator() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addLast("A");
        lista.addLast("B");
        lista.addLast("C");
        lista.addLast("D");
        lista.addLast("E");
        Assert.assertEquals("(A B C D E )", lista.toString());
        Iterator<String> iterator = lista.oddPositionsIterator();
        StringBuilder output = new StringBuilder("(");
        while (iterator.hasNext()) {
            output.append(iterator.next()).append(" ");
        }
        output.append(")");
        Assert.assertEquals("(A C E )", output.toString());
        Assert.assertFalse(iterator.hasNext());
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testProgressIterator() {
        DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
        lista.addFirst("2");
        lista.addLast("3");
        Assert.assertEquals("(2 3 )", lista.toString());
        lista.addLast("1");
        Assert.assertEquals("(2 3 1 )", lista.toString());
        lista.addBefore("1", "2");
        Assert.assertEquals("(1 2 3 1 )", lista.toString());
        lista.addBefore("2", "2");
        Assert.assertEquals("(1 2 2 3 1 )", lista.toString());
        lista.addBefore("2", "1");
        Assert.assertEquals("(2 1 2 2 3 1 )", lista.toString());
        lista.addLast("4");
        lista.addLast("6");
        Assert.assertEquals("(2 1 2 2 3 1 4 6 )", lista.toString());
        Iterator<String> iterator = lista.progressReverseIterator();
        StringBuilder nuevo = new StringBuilder("(");
        while (iterator.hasNext()) {
            nuevo.append(iterator.next()).append(" ");
        }
        nuevo.append(")");
        Assert.assertEquals("(6 4 3 1 )", nuevo.toString());
        Assert.assertFalse(iterator.hasNext());
        iterator.next();
    }

}