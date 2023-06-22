package ule.edi.doubleLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.edi.exceptions.EmptyCollectionException;

public class DoubleLinkedListImpl<T> implements DoubleList<T> {


    //	referencia al primer aux de la lista
    private DoubleNode<T> front;

    //	referencia al Último aux de la lista
    private DoubleNode<T> last;


    @SafeVarargs
    public DoubleLinkedListImpl(T... v) {
        for (T elem : v) {
            this.addLast(elem);
        }
    }

    @Override
    public void addFirst(T elem) {
        if (elem == null) {
            throw new NullPointerException();
        }

        DoubleNode<T> node = new DoubleNode<T>(elem);

        if (isEmpty()) {
            front = node;
            last = node;
        } else {
            node.next = front;
            front.prev = node;
            front = node;
        }
    }

    @Override
    public void addLast(T elem) {
        if (elem == null) {
            throw new NullPointerException();
        }

        DoubleNode<T> node = new DoubleNode<T>(elem);

        if (isEmpty()) {
            front = node;
        } else {
            node.prev = last;
            last.next = node;
        }

        last = node;
    }

    @Override
    public void addPos(T elem, int position) {
        if (elem == null) {
            throw new NullPointerException();
        } else if (position <= 0) {
            throw new IllegalArgumentException();
        }

        DoubleNode<T> aux1 = front;
        DoubleNode<T> aux2;
        DoubleNode<T> node = new DoubleNode<T>(elem);

        if (position == 1 || isEmpty()) {
            addFirst(elem);
        } else if (position > size()) {
            addLast(elem);
        } else {
            while (position > 1) {
                aux1 = aux1.next;
                position--;
            }

            aux2 = aux1.prev;

            aux2.next = node;
            aux1.prev = node;

            node.prev = aux2;
            node.next = aux1;
        }
    }

    @Override
    public void addBefore(T elem, T target) {
        addPos(elem, getPosFirst(target));
    }

    @Override
    public T getElemPos(int position) {
        if (!(position >= 1 && position <= size())) {
            throw new IllegalArgumentException();
        }

        DoubleNode<T> aux = front;

        while (position > 1) {
            position--;
            aux = aux.next;
        }

        return aux.elem;
    }

    @Override
    public int getPosFirst(T elem) {
        if (elem == null) {
            throw new NullPointerException();
        }

        DoubleNode<T> aux = front;
        boolean found = false;
        int position = 0;

        while (aux != null && !found) {
            if (aux.elem.equals(elem)) {
                found = true;
            }

            position++;
            aux = aux.next;
        }

        if (!found) {
            throw new NoSuchElementException();
        }

        return position;
    }

    @Override
    public int getPosLast(T elem) {
        if (elem == null) {
            throw new NullPointerException();
        }

        DoubleNode<T> aux = last;
        boolean found = false;
        int position = size();

        while (aux != null && !found) {
            if (aux.elem.equals(elem)) {
                found = true;
            } else {
                position--;
            }

            aux = aux.prev;
        }

        if (!found) {
            throw new NoSuchElementException();
        }

        return position;
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        T deletedElement = null;

        if (isEmpty()) {
            throw new EmptyCollectionException("");
        } else if (size() == 1) {
            deletedElement = front.elem;
            clear();
        } else {
            DoubleNode<T> aux = last.prev;
            deletedElement = aux.next.elem;

            aux.next = null;
            last = aux;
        }

        return deletedElement;
    }

    @Override
    public T removePos(int pos) throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("");
        } else if (!(pos >= 1 && pos <= size())) {
            throw new IllegalArgumentException();
        }

        T removedElement = null;

        if (pos == 1) {
            removedElement = front.elem;
            DoubleNode<T> aux = front.next;
            if (aux == null) {
                removeLast();
            } else {
                front = aux;
                aux.prev = null;
            }
        } else if (pos == size()) {
            removedElement = removeLast();
        } else {
            DoubleNode<T> aux = front;

            while (pos > 1) {
                aux = aux.next;
                pos--;
            }

            removedElement = aux.elem;

            aux.prev.next = aux.next;
            aux.next.prev = aux.prev;
        }

        return removedElement;
    }

    @Override
    public int removeN(T elem, int times) throws EmptyCollectionException {
        if (elem == null) {
            throw new NullPointerException();
        } else if (times < 1) {
            throw new IllegalArgumentException();
        } else if (isEmpty()) {
            throw new EmptyCollectionException("");
        } else if (!contains(elem)) {
            throw new NoSuchElementException();
        }

        DoubleNode<T> aux = front;
        int position = 1;

        if (times > countElements(elem)) {
            times = countElements(elem);
        }

        int deletedElements = times;

        while (times > 0) {
            if (aux.elem.equals(elem)) {
                removePos(position);
                position = 1;
                aux = front;
                times--;
            } else {
                aux = aux.next;
                position++;
            }
        }

        return deletedElements;
    }

    @Override
    public T removeSecond() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("");
        } else if (size() == 1) {
            throw new NoSuchElementException();
        }

        return removePos(2);
    }

    private int countElements(T elem) {
        int count = 0;
        DoubleNode<T> aux = front;

        while (aux != null) {
            if (aux.elem.equals(elem)) {
                count++;
            }
            aux = aux.next;
        }

        return count;
    }

    @Override
    public boolean isEmpty() {
        return front == null;
    }


    @Override
    public void clear() {
        front = null;
        last = null;
    }

    @Override
    public DoubleList<T> copy() {
        DoubleLinkedListImpl<T> copy = new DoubleLinkedListImpl<T>();
        DoubleNode<T> aux = front;

        while (aux != null) {
            copy.addLast(aux.elem);
            aux = aux.next;
        }

        return copy;
    }

    @Override
    public boolean contains(T elem) {
        if (elem == null) {
            throw new NullPointerException();
        } else if (isEmpty()) {
            return false;
        }

        DoubleNode<T> aux = front;

        while (aux != null) {
            if (aux.elem.equals(elem)) {
                return true;
            }
            aux = aux.next;
        }

        return false;
    }

    @Override
    public int size() {
        DoubleNode<T> aux = front;
        int size = 0;

        while (aux != null) {
            size++;
            aux = aux.next;
        }

        return size;
    }

    @Override
    public int maxRepeated() {
        DoubleNode<T> aux = front;
        int max = 0;

        while (aux != null) {
            if (countElements(aux.elem) > max) {
                max = countElements(aux.elem);
            }
            aux = aux.next;
        }

        return max;
    }

    @Override
    public boolean sameContent(DoubleList<T> other) {
        DoubleLinkedListImpl<T> otherList = (DoubleLinkedListImpl<T>) other;
        DoubleNode<T> aux = front;
        boolean same = true;

        if (size() != otherList.size()) {
            same = false;
        } else {
            while (aux != null && same) {
                if (!(countElements(aux.elem) == otherList.countElements(aux.elem))) {
                    same = false;
                }
                aux = aux.next;
            }
        }

        return same;
    }

    @Override
    public String toString() {
        DoubleNode<T> aux = front;
        StringBuilder output = new StringBuilder();
        output.append("(");

        while (aux != null) {
            output.append(aux.elem.toString()).append(" ");
            aux = aux.next;
        }

        output.append(")");
        return output.toString();
    }

    @Override
    public String toStringReverse() {
        DoubleNode<T> aux = last;
        StringBuilder output = new StringBuilder();
        output.append("(");

        while (aux != null) {
            output.append(aux.elem.toString()).append(" ");
            aux = aux.prev;
        }

        output.append(")");
        return output.toString();
    }

    @Override
    public String toStringFromUntil(int from, int until) {
        if (from < 1 || until < 1 || until < from) {
            throw new IllegalArgumentException();
        }

        DoubleNode<T> aux = front;
        StringBuilder output = new StringBuilder();
        int position = 1;
        output.append("(");

        while (aux != null) {
            if (position >= from && position <= until) {
                output.append(aux.elem.toString()).append(" ");
            }
            position++;
            aux = aux.next;
        }

        output.append(")");
        return output.toString();
    }

    @Override
    public String toStringFromUntilReverse(int from, int until) {
        if (from < 1 || until < 1 || from < until) {
            throw new IllegalArgumentException();
        }

        DoubleNode<T> aux = last;
        StringBuilder output = new StringBuilder();
        int position = size();
        output.append("(");

        while (aux != null) {
            if (position >= until && position <= from) {
                output.append(aux.elem.toString()).append(" ");
            }
            position--;
            aux = aux.prev;
        }

        output.append(")");
        return output.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new DoubleLinkedListIterator<>(front);
    }

    @Override
    public Iterator<T> reverseIterator() {
        return new DoubleLinkedListIteratorReverse<>(last);
    }

    @Override
    public Iterator<T> oddPositionsIterator() {
        return new DoubleLinkedListIteratorOddPositions<>(front);
    }

    @Override
    public Iterator<T> progressReverseIterator() {
        return new DoubleLinkedListIteratorProgressReverse<>(last);
    }

    @SuppressWarnings("hiding")
    private class DoubleLinkedListIterator<T> implements Iterator<T> {
        DoubleNode<T> node;

        public DoubleLinkedListIterator(DoubleNode<T> front) {
            this.node = front;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }


        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T result = node.elem;
            node = node.next;

            return result;
        }
    }

    @SuppressWarnings("hiding")
    private class DoubleLinkedListIteratorReverse<T> implements Iterator<T> {
        DoubleNode<T> node;

        public DoubleLinkedListIteratorReverse(DoubleNode<T> last) {
            this.node = last;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T result = node.elem;
            node = node.prev;

            return result;
        }
    }

    @SuppressWarnings("hiding")
    private class DoubleLinkedListIteratorOddPositions<T> implements Iterator<T> {
        DoubleNode<T> node;

        public DoubleLinkedListIteratorOddPositions(DoubleNode<T> front) {
            this.node = front;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T result = node.elem;
            if (node.next == null) {
                node = null;
            } else {
                node = node.next.next;
            }

            return result;
        }
    }

    @SuppressWarnings("hiding")
    private class DoubleLinkedListIteratorProgressReverse<T> implements Iterator<T> {
        DoubleNode<T> node;
        int skip;

        public DoubleLinkedListIteratorProgressReverse(DoubleNode<T> last) {
            this.node = last;
            this.skip = 0;
        }

        @Override
        public boolean hasNext() {
            DoubleNode<T> aux = node;
            for (int i = 0; i < skip && aux != null; i++) {
                aux = aux.prev;
            }
            return aux != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            for (int i = 0; i < skip; i++) {
                node = node.prev;
            }

            T result = node.elem;

            skip++;
            return result;
        }
    }
    @SuppressWarnings("hiding")
    private class DoubleNode<T> {

        T elem;
        DoubleNode<T> next;
        DoubleNode<T> prev;

        DoubleNode(T element) {
            this.elem = element;
            this.next = null;
            this.prev = null;
        }
    }

}