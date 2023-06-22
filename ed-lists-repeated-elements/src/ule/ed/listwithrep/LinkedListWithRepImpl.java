package ule.ed.listwithrep;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.ed.exceptions.EmptyCollectionException;

public class LinkedListWithRepImpl<T> implements ListWithRep<T> {

    // Atributos
    int count;
    private ListWithRepNode<T> front;


    public LinkedListWithRepImpl() {
        this.count = 0;
        this.front = null;
    }

    /////////////
    @Override
    public void add(T element) {
        add(element, 1);
    }
    ////// FIN ITERATOR

    @Override
    public void add(T element, int times) {
        if (element == null) {
            throw new NullPointerException();
        } else if (times < 0) {
            throw new IllegalArgumentException();
        } else if (times == 0) {
            return;
        }

        ListWithRepNode<T> node = new ListWithRepNode<T>(element, times);
        ListWithRepNode<T> aux = front;

        if (isEmpty()) {
            front = node;
            count++;
        } else if (contains(element)) {
            search(element).num += times;
        } else {
            while (aux.next != null) {
                aux = aux.next;
            }

            aux.next = node;
            count++;
        }
    }
    ////// FIN ITERATOR

    @Override
    public int remove(T element, int times) throws EmptyCollectionException {
        boolean found = false;
        ListWithRepNode<T> previous, current;
        int amountRemoved;

        if (element == null) {
            throw new NullPointerException();
        } else if (times < 0) {
            throw new IllegalArgumentException();
        } else if (times == 0) {
            return 0;
        } else if (isEmpty()) {
            throw new EmptyCollectionException("");
        } else if (!contains(element)) {
            throw new NoSuchElementException();
        }

        if (front.elem.equals(element)) {
            if (front.num <= times) {
                amountRemoved = front.num;
                front = front.next;
                count--;
            } else {
                amountRemoved = times;
                front.num -= times;
            }
        } else {
            previous = front;
            current = front.next;

            for (int i = 1; i < count && !found; i++) {
                if (current.elem.equals(element)) {
                    found = true;
                } else {
                    previous = current;
                    current = current.next;
                }
            }

            if (current.num <= times) {
                amountRemoved = current.num;
                previous.next = current.next;
                count--;
            } else {
                amountRemoved = times;
                current.num -= times;
            }
        }

        return amountRemoved;
    }

    @Override
    public int remove() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("");
        }

        return remove(front.elem, front.num);
    }

    @Override
    public boolean contains(T element) {
        ListWithRepNode<T> aux = front;

        if (element == null) {
            throw new NullPointerException();
        }

        while (aux != null) {
            if (aux.elem.equals(element)) {
                return true;
            }

            aux = aux.next;
        }

        return false;
    }

    private ListWithRepNode<T> search(T element) {
        ListWithRepNode<T> aux = front;
        ListWithRepNode<T> found = null;

        while (aux != null) {
            if (aux.elem.equals(element)) {
                found = aux;
            }

            aux = aux.next;
        }

        return found;
    }

    @Override
    public long size() {
        ListWithRepNode<T> aux = front;
        int size = 0;

        while (aux != null) {
            size += aux.num;
            aux = aux.next;
        }

        return size;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public void clear() {
        while (front != null) {
            front = front.next;
        }

        count = 0;
    }

    @Override
    public int count(T element) {
        if (element == null) {
            throw new NullPointerException();
        }

        if (contains(element)) {
            return search(element).num;
        }

        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListWithRepIterator<T>(front, count);
    }

    @Override
    public Iterator<T> iteratorRep() {
        return new LinkedListWithRepIteratorRep<T>(front, count);
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        ListWithRepNode<T> aux = front;
        int instances = 1;

        buffer.append("(");

        while (aux != null) {
            if (instances == aux.num) {
                buffer.append(aux.elem.toString()).append(" ");
                instances = 1;
                aux = aux.next;
            } else {
                buffer.append(aux.elem.toString()).append(" ");
                instances++;
            }
        }

        buffer.append(")");

        return buffer.toString();
    }

    // Clase interna
    @SuppressWarnings("hiding")
    public class ListWithRepNode<T> {
        T elem;
        int num;
        ListWithRepNode<T> next;

        public ListWithRepNode(T elem, int num) {
            this.elem = elem;
            this.num = num;
        }

    }

    ///// ITERADOR //////////
    @SuppressWarnings("hiding")
    public class LinkedListWithRepIterator<T> implements Iterator<T> {
        private int count;
        private ListWithRepNode<T> current;

        public LinkedListWithRepIterator(ListWithRepNode<T> front, int count) {
            this.current = front;
            this.count = count;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T result = current.elem;
            current = current.next;

            return result;
        }
    }

    ///// ITERADOR //////////
    @SuppressWarnings("hiding")
    public class LinkedListWithRepIteratorRep<T> implements Iterator<T> {
        private int count;
        private ListWithRepNode<T> current;
        private int instances;

        public LinkedListWithRepIteratorRep(ListWithRepNode<T> front, int count) {
            this.current = front;
            this.count = count;
            this.instances = 1;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            ListWithRepNode<T> aux = current;

            if (instances == current.num) {
                instances = 1;
                current = current.next;
                return aux.elem;
            } else {
                instances++;
            }

            return aux.elem;
        }
    }

}
