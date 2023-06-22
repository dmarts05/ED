package ule.ed.recursivelist;

import java.util.NoSuchElementException;

public class LinkedEDList<T> implements EDList<T> {

  // referencia al primer de la lista
  private Node<T> front;

  private class Node<T> {

    Node(T element) {
      this.elem = element;
      this.next = null;
    }

    T elem;

    Node<T> next;
  }

  @Override
  public int size() {
    return sizeRec(front);
  }

  private int sizeRec(Node<T> aux) {
    int size = 0;

    if (aux != null) {
      size = 1 + sizeRec(aux.next);
    }

    return size;
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public void addLast(T elem) {
    if (elem == null) {
      throw new NullPointerException();
    } else if (front == null) {
      front = new Node<T>(elem);
    } else {
      addLastRec(front, elem);
    }
  }

  private void addLastRec(Node<T> aux, T elem) {
    if (aux.next != null) {
      addLastRec(aux.next, elem);
    } else {
      aux.next = new Node<T>(elem);
    }
  }

  @Override
  public String toString() {
    return "(" + toStringRec(front) + ")";
  }

  private String toStringRec(Node<T> aux) {
    StringBuilder list = new StringBuilder();

    if (aux != null) {
      list.append(aux.elem.toString()).append(" ").append(toStringRec(aux.next));
    }

    return list.toString();
  }

  private void addFirst(Node<T> node) {
    if (isEmpty()) {
      front = node;
    } else {
      node.next = front;
      front = node;
    }
  }

  @Override
  public void addAntePenult(T elem) {
    if (elem == null) {
      throw new NullPointerException();
    }

    Node<T> node = new Node<T>(elem);

    if (size() < 3) { // Add as first node when there is less than 3 elements
      addFirst(node);
    } else if (size() == 3) { // Add as second node when there is only 3 elements
      node.next = front.next;
      front.next = node;
    } else {
      addAntePenultRec(front, elem);
    }
  }

  private void addAntePenultRec(Node<T> aux, T elem) {
    if (aux.next.next.next != null) {
      addAntePenultRec(aux.next, elem);
    } else {
      Node<T> node = new Node<T>(elem);
      node.next = aux.next;
      aux.next = node;
    }
  }

  @Override
  public void addPos(T elem, int position) {
    if (elem == null) {
      throw new NullPointerException();
    } else if (position <= 0) {
      throw new IllegalArgumentException();
    }
    Node<T> node = new Node<T>(elem);

    if (isEmpty() || position == 1) {
      addFirst(node);
    } else if (position > size()) {
      addLast(elem);
    } else {
      addPosRec(front, elem, position);
    }
  }

  private void addPosRec(Node<T> aux, T elem, int position) {
    // Aux will take the value of the node before the one its occupying the position
    // we received as argument
    if ((position - 1) > 1) {
      position--;
      addPosRec(aux.next, elem, position);
    } else {
      Node<T> node = new Node<T>(elem);
      node.next = aux.next;
      aux.next = node;
    }
  }

  @Override
  public T getElemPos(int position) {
    if (position < 1 || position > size()) {
      throw new IllegalArgumentException();
    } else if (position == 1) {
      return front.elem;
    } else {
      return getElemPosRec(front, position);
    }
  }

  private T getElemPosRec(Node<T> aux, int position) {
    if (position > 1) {
      position--;
      return getElemPosRec(aux.next, position);
    }

    return aux.elem;
  }

  @Override
  public int getPosFirst(T elem) {
    if (elem == null) {
      throw new NullPointerException();
    }

    return getPosFirstRec(front, elem, 1);
  }

  private int getPosFirstRec(Node<T> aux, T elem, int position) {
    if (aux != null && !aux.elem.equals(elem)) {
      position++;
      return getPosFirstRec(aux.next, elem, position);
    }

    if (aux == null) {
      throw new NoSuchElementException();
    }

    return position;
  }

  @Override
  public int getPosLast(T elem) {
    if (elem == null) {
      throw new NullPointerException();
    }

    return getPosLastRec(front, elem, 1, 0);
  }

  private int getPosLastRec(Node<T> aux, T elem, int counter, int position) {
    if (aux != null) {
      if (aux.elem.equals(elem)) {
        return getPosLastRec(aux.next, elem, ++counter, counter - 1);
      } else {
        return getPosLastRec(aux.next, elem, ++counter, position);
      }
    }

    if (position == 0) {
      throw new NoSuchElementException();
    }

    return position;
  }

  @Override
  public T removelast() throws EmptyCollectionException {
    T removed = null;
    if (isEmpty()) {
      throw new EmptyCollectionException("");
    } else if (size() == 1) {
      removed = front.elem;
      front = null;
    } else {
      removed = removeLastRec(front);
    }
    return removed;
  }

  private T removeLastRec(Node<T> aux) {
    if (aux.next.next == null) {
      T removed = aux.next.elem;
      aux.next = null;
      return removed;
    }

    return removeLastRec(aux.next);
  }

  @Override
  public T removePenult() throws EmptyCollectionException {
    T removed = null;
    if (isEmpty()) {
      throw new EmptyCollectionException("");
    } else if (size() == 1) {
      throw new NoSuchElementException();
    } else if (size() == 2) {
      removed = front.elem;
      front = front.next;
    } else {
      removed = removePenultRec(front);
    }
    return removed;
  }

  private T removePenultRec(Node<T> aux) {
    if (aux.next.next.next == null) {
      T removed = aux.next.elem;
      aux.next = aux.next.next;
      return removed;
    }

    return removePenultRec(aux.next);
  }

  @Override
  public T removeFirstElem(T elem) throws EmptyCollectionException {
    T removed = null;

    if (elem == null) {
      throw new NullPointerException();
    } else if (isEmpty()) {
      throw new EmptyCollectionException("");
    } else {
      removed = removeElemPosRec(front, elem, getPosFirst(elem));
    }
    return removed;
  }

  private T removeElemPosRec(Node<T> aux, T elem, int position) {
    T removed = null;
    if ((position - 1) > 1) {
      position--;
      return removeElemPosRec(aux.next, elem, position);
    } else if (position == 1) {
      removed = front.elem;
      front = front.next;
    } else {
      removed = aux.next.elem;
      aux.next = aux.next.next;
    }

    return removed;
  }

  @Override
  public T removeLastElem(T elem) throws EmptyCollectionException {
    T removed = null;

    if (elem == null) {
      throw new NullPointerException();
    } else if (isEmpty()) {
      throw new EmptyCollectionException("");
    } else {
      removed = removeElemPosRec(front, elem, getPosLast(elem));
    }
    return removed;
  }

  @Override
  public EDList<T> reverse() {
    LinkedEDList<T> listReverse = new LinkedEDList<T>();
    reverseRec(front, listReverse);
    return listReverse;
  }

  private void reverseRec(Node<T> aux, LinkedEDList<T> listReverse) {
    if (aux == null) {
      return;
    }

    reverseRec(aux.next, listReverse);
    listReverse.addLast(aux.elem);
  }

  @Override
  public String toStringFromUntilReverse(int from, int until) {
    LinkedEDList<T> reverseList = new LinkedEDList<T>();

    if (from < 1 || until < 1 || until > from) {
      throw new IllegalArgumentException();
    } else if (from > size()) {
      from = size();
    }

    return toStringFromUntilReverseRec(front, until, from, 1, reverseList);
  }

  private String toStringFromUntilReverseRec(Node<T> aux, int from, int until, int position,
      LinkedEDList<T> reverseList) {

    if (position >= from && position <= until) {
      reverseList.addFirst(new Node<T>(aux.elem));
      return toStringFromUntilReverseRec(aux.next, from, until, ++position, reverseList);
    } else if (aux != null) {
      return toStringFromUntilReverseRec(aux.next, from, until, ++position, reverseList);
    }

    return reverseList.toString();

  }

  @Override
  public String toStringEvenOdd() {
    StringBuilder output = new StringBuilder();
    return output.append("(").append(toStringEvenOddRec(front, 1)).append(")").toString();
  }

  private String toStringEvenOddRec(Node<T> aux, int pos) {

    StringBuilder output = new StringBuilder();

    if (aux != null) {
      if (pos % 2 == 0) {
        output.append(aux.elem.toString()).append(" ");
      }

      output.append(toStringEvenOddRec(aux.next, pos + 1));

      if (pos % 2 != 0) {
        output.append(aux.elem.toString()).append(" ");
      }

    }

    return output.toString();
  }

}
