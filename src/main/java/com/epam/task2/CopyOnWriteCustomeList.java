package com.epam.task2;

import com.epam.task1.util.CustomeList;

import java.util.*;
import java.util.function.Predicate;

public class CopyOnWriteCustomeList<T> implements List<T> {


    private transient volatile Object[] storage;

    public CopyOnWriteCustomeList(List<T> storage) {
        this.storage = storage.toArray();
    }

    public CopyOnWriteCustomeList() {
        this.storage = new Object[0];
    }

    @Override
    public boolean add(T e) {
        Object[] localStorage = storage;
        int len = localStorage.length;
        Object[] newStorage = Arrays.copyOf(localStorage, len + 1);
        newStorage[len] = e;
        storage = newStorage;
        return true;
    }

    @Override
    public int size() {
        return storage.length;
    }

    @Override
    public boolean isEmpty() {
        return storage.length == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(storage, storage.length);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < storage.length) {
            return (T[]) Arrays.copyOf(storage, storage.length, a.getClass());
        }
        System.arraycopy(storage, 0, a, 0, storage.length);
        if (a.length > storage.length) {
            a[storage.length] = null;
        }
        return a;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null || c.isEmpty()) {
            return false;
        }
        for (Object object : c) {
            if (indexOf(object) < 0) {
                return false;
            }
        }
        return true;

    }

    @Override
    public void clear() {
        storage = new Object[0];
    }

    @Override
    public T get(int index) {
        checkRangeAndThrowException(index);
        return (T) storage[index];
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < storage.length; i++) {
                if (storage[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < storage.length; i++) {
                if ((storage[i].equals(o))) {
                    return i;
                }
            }

        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = storage.length - 1; i >= 0; i--) {
                if (storage[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = storage.length - 1; i >= 0; i--) {
                if (((T) storage[i]).equals(o)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c == null || c.isEmpty()) {
            return false;
        }
        int listSize = c.size();
        if (listSize == 0)
            return false;
        Object[] cs = c.toArray();
        Object[] localStorage = storage;
        int len = localStorage.length;
        if (len == 0 && cs.getClass() == Object[].class)
            storage = cs;
        else {
            Object[] newElements = Arrays.copyOf(localStorage, len + cs.length);
            System.arraycopy(cs, 0, newElements, len, cs.length);
            storage = newElements;
        }
        return true;
    }


    @Override
    public T set(int index, T element) {
        checkRangeAndThrowException(index);
        Object[] elements = storage;
        T oldValue = get(index);
        if (oldValue != element) {
            int len = elements.length;
            Object[] newElements = Arrays.copyOf(elements, len);
            newElements[index] = element;
            storage = newElements;
        } else {
            storage = elements;
        }
        return oldValue;
    }

    @Override
    public void add(int index, T element) {
        checkRangeAndThrowException(index);

        Object[] elements = storage;
        int len = elements.length;
        checkRangeAndThrowException(index);
        Object[] newElements;
        int numMoved = len - index;
        if (numMoved == 0)
            newElements = Arrays.copyOf(elements, len + 1);
        else {
            newElements = new Object[len + 1];
            System.arraycopy(elements, 0, newElements, 0, index);
            System.arraycopy(elements, index, newElements, index + 1,
                    numMoved);
        }
        newElements[index] = element;
        storage = newElements;
    }


    @Override
    public T remove(int index) {
        Object[] elements = storage;
        int len = elements.length;
        T oldValue = get(index);
        int numMoved = len - index - 1;
        if (numMoved == 0) {
            storage = Arrays.copyOf(elements, len - 1);
        } else {
            Object[] newElements = new Object[len - 1];
            System.arraycopy(elements, 0, newElements, 0, index);
            System.arraycopy(elements, index + 1, newElements, index,
                    numMoved);
            storage = newElements;
        }
        return oldValue;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        int sizeToCompare = storage.length;
        if (index < 0 || index >= storage.length) {
            return false;
        }
        remove(index);
        return sizeToCompare != storage.length;

    }

    public Iterator<T> iterator(Predicate<T> predicate) {
        return new ConditionIterator(predicate);
    }

    @Override
    public Iterator<T> iterator() {
        return iterator(p -> true);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (c.isEmpty())
            return false;
        checkRangeAndThrowException(index);
        Object[] cs = c.toArray();
        Object[] localStorage = storage;
        int len = localStorage.length;
        int numMoved = len - index;
        Object[] newStorage;
        if (numMoved == 0)
            newStorage = Arrays.copyOf(localStorage, len + cs.length);
        else {
            newStorage = new Object[len + cs.length];
            System.arraycopy(localStorage, 0, newStorage, 0, index);
            System.arraycopy(localStorage, index,
                    newStorage, index + cs.length,
                    numMoved);
        }
        System.arraycopy(cs, 0, newStorage, index, cs.length);
        storage = newStorage;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null || c.isEmpty()) {
            return false;
        }
        CustomeList<T> newStorage = new CustomeList<T>();
        for (int i = 0; i < storage.length; i++) {
            if (!c.contains(storage[i])) {
                newStorage.add((T) storage[i]);
            }
        }
        boolean res = storage.length != newStorage.size();
        storage = newStorage.toArray();
        return res;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null || c.isEmpty()) {
            return false;
        }
        CustomeList<T> newStorage = new CustomeList<T>();
        for (int i = 0; i < storage.length; i++) {
            if (c.contains(storage[i])) {
                newStorage.add((T) storage[i]);
            }
        }
        storage = newStorage.toArray();
        return true;
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }


    private class ConditionIterator implements Iterator<T> {
        private Predicate<T> predicate;
        private int point = -1;
        private Object[] localStorage = storage;

        public ConditionIterator(Predicate<T> predicate) {
            this.predicate = predicate;
        }

        @Override
        public boolean hasNext() {
            if (point + 1 >= localStorage.length) {
                return false;
            }
            do {
                point++;
            } while (point < localStorage.length && !predicate.test((T) localStorage[point]));

            return point < localStorage.length;
        }

        @Override
        public T next() {
            return (T) localStorage[point];
        }

    }

    private void checkRangeAndThrowException(int index) {
        if (index >= storage.length || index < 0) {
            throw new IndexOutOfBoundsException("Index = " + index + System.lineSeparator() + " size = " + storage.length);
        }
    }


    @Override
    public String toString() {
        return "CopyOnWriteList " + Arrays.toString(Arrays.copyOfRange(storage, 0, storage.length));
    }

}
