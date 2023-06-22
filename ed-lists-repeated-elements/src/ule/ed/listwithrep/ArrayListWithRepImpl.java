package ule.ed.listwithrep;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.ed.exceptions.EmptyCollectionException;

public class ArrayListWithRepImpl<T> implements ListWithRep<T> {

    // Atributos
    private final int capacityDefault = 5;

    ElemListWithRep<T>[] data;
    private int count;

    @SuppressWarnings("unchecked")
    public ArrayListWithRepImpl() {
        data = new ElemListWithRep[capacityDefault];
        count = 0;
    }

    @SuppressWarnings("unchecked")
    public ArrayListWithRepImpl(int capacity) {
        data = new ElemListWithRep[capacity];
        count = 0;
    }
    ////// FIN ITERATOR

    @SuppressWarnings("unchecked")
    private void expandCapacity() {
        data = Arrays.copyOf(data, data.length * 2);
    }
    ////// FIN ITERATOR

    // Constructores

    @Override
    public void add(T element, int times) {
        if (element == null) {
            throw new NullPointerException();
        } else if (times < 0) {
            throw new IllegalArgumentException();
        } else if (!(times == 0)) {
            if (count == data.length) {
                expandCapacity();
            }

            if (contains(element)) {
                data[search(element)].num += times;
            } else {
                data[count] = new ElemListWithRep<T>(element, times);
                count++;
            }
        }
    }

    @Override
    public void add(T element) {
        add(element, 1);
    }

    @Override
    public int remove(T element, int times) throws EmptyCollectionException {
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

        int amountRemoved;
        int index = search(element);

        if (data[index].num <= times) {
            amountRemoved = data[index].num;
            data[index] = data[count - 1];
            data[count - 1] = null;
            count--;

        } else {
            amountRemoved = times;
            data[index].num -= times;
        }

        return amountRemoved;
    }

    @Override
    public int remove() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("");
        }

        return remove(data[0].elem, data[0].num);
    }

    @Override
    public void clear() {
        for (int i = 0; i < count; i++) {
            data[i] = null;
        }

        count = 0;
    }

    @Override
    public boolean contains(T element) {
        if (element == null) {
            throw new NullPointerException();
        }

        for (int i = 0; i < count; i++) {
            if (data[i].elem.equals(element)) {
                return true;
            }
        }
        return false;
    }

    private int search(T element) {
        int index = 0;

        int counter = 0;
        boolean found = false;

        while ((counter < count) && !found) {
            if (data[counter].elem.equals(element)) {
                index = counter;
                found = true;
            }
            counter++;
        }

        return index;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public long size() {
        long size = 0;

        for (int i = 0; i < count; i++) {
            size += data[i].num;
        }

        return size;
    }

    @Override
    public int count(T elem) {
        if (elem == null) {
            throw new NullPointerException();
        }

        for (int i = 0; i < count; i++) {
            if (data[i].elem.equals(elem)) {
                return data[i].num;
            }
        }

        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListWithRepIterator<T>(data, count);
    }

    @Override
    public Iterator<T> iteratorRep() {
        return new ArrayListWithRepIteratorRep<T>(data, count);
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        int counter = 0;
        int instances = 1;

        buffer.append("(");

        while (counter < count) {
            if (instances == data[counter].num) {
                buffer.append(data[counter].elem.toString()).append(" ");
                instances = 1;
                counter++;
            } else {
                buffer.append(data[counter].elem.toString()).append(" ");
                instances++;
            }
        }

        buffer.append(")");

        return buffer.toString();
    }

    // Clase interna
    @SuppressWarnings("hiding")
    public class ElemListWithRep<T> {
        T elem;
        int num;

        public ElemListWithRep(T elem, int num) {
            this.elem = elem;
            this.num = num;
        }
    }

    ///// ITERADOR //////////
    @SuppressWarnings("hiding")
    public class ArrayListWithRepIterator<T> implements Iterator<T> {

        ElemListWithRep<T>[] data;
        private int count;
        private int current;

        public ArrayListWithRepIterator(ElemListWithRep<T>[] data, int count) {
            this.data = data;
            this.count = count;
            this.current = 0;
        }

        @Override
        public boolean hasNext() {
            return current < count;
        }

        @Override
        public T next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            current++;
            return data[current - 1].elem;
        }

    }

    ///// ITERADOR //////////
    @SuppressWarnings("hiding")
    public class ArrayListWithRepIteratorRep<T> implements Iterator<T> {

        ElemListWithRep<T>[] data;
        private int count;
        private int current;
        private int instances;

        public ArrayListWithRepIteratorRep(ElemListWithRep<T>[] data, int count) {
            this.data = data;
            this.count = count;
            this.current = 0;
            this.instances = 1;
        }

        @Override
        public boolean hasNext() {
            return current < count;
        }

        @Override
        public T next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            if (instances == data[current].num) {
                current++;
                instances = 1;
                return data[current - 1].elem;
            } else {
                instances++;
                return data[current].elem;
            }

        }

    }

}
