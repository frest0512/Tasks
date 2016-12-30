package com.epam.task1.util;

import java.util.*;
import java.util.function.Predicate;

public class CustomeList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] storage;
    private int size;

    public CustomeList(List<T> storage) {
        this.storage = storage.toArray();
        size = this.storage.length;
    }

    public CustomeList() {
        this.storage = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public boolean add(T e) {
        checkAndIncrease(1);
        storage[size] = e;
        size++;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[]) Arrays.copyOf(storage, size, a.getClass());
        }
        System.arraycopy(storage, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
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
    public boolean addAll(Collection<? extends T> c) {
        if (c == null || c.isEmpty()) {
            return false;
        }
        int listSize = c.size();
        checkAndIncrease(size + listSize);
        System.arraycopy(c.toArray(), 0, storage, size, listSize);
        return true;
    }

    @Override
    public void clear() {
        size = 0;
        storage = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public T get(int index) {
        checkRangeAndThrowException(index);
        return (T) storage[index];
    }

    @Override
    public T set(int index, T element) {
        checkRangeAndThrowException(index);
        storage[index] = element;
        return get(index);
    }

    @Override
    public void add(int index, T element) {
        checkRangeAndThrowException(index);
        System.arraycopy(storage, index, storage, index + 1, size - index - 1);
        storage[index] = element;
        size++;

    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (storage[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
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
            for (int i = size - 1; i >= 0; i--) {
                if (storage[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (((T) storage[i]).equals(o)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public T remove(int index) {
        checkRangeAndThrowException(index);
        T obj = get(index);
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        storage[--size] = null;

        return obj;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        int sizeToCompare = size;
        if (index < 0 || index >= size) {
            return false;
        }

        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        storage[--size] = null;
        return sizeToCompare != size;
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
        if (c == null || c.isEmpty()) {
            return false;
        }
        Object[] arrayToInsert = c.toArray();
        int numberOfAddingElements = arrayToInsert.length;
        checkAndIncrease(size + numberOfAddingElements);

        System.arraycopy(storage, index, storage, index + numberOfAddingElements, size - index);
        System.arraycopy(arrayToInsert, 0, storage, index, numberOfAddingElements);
        size += numberOfAddingElements;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null || c.isEmpty()) {
            return false;
        }

        boolean flag = true;
        for (Object object : c) {
            boolean result = remove(object);
            if (!result) {
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null || c.isEmpty()) {
            return false;
        }
        CustomeList<T> newStorage = new CustomeList<T>();
        for (int i = 0; i < size; i++) {
            if (c.contains(storage[i])) {
                newStorage.add((T) storage[i]);
            }
        }
        storage = newStorage.toArray();
        size = storage.length;
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

    private void checkAndIncrease(int numberToInsert) {

        if (size + numberToInsert > storage.length) {
            int newSize = size + numberToInsert;
            T[] newStorage = (T[]) new Object[newSize];
            System.arraycopy(storage, 0, newStorage, 0, size);
            storage = newStorage;
        }
    }

    private class ConditionIterator implements Iterator<T> {
        private Predicate<T> predicate;
        private int point = -1;

        public ConditionIterator(Predicate<T> predicate) {
            this.predicate = predicate;
        }

        @Override
        public boolean hasNext() {
            if (point + 1 >= size) {
                return false;
            }
            do {
                point++;
            } while (point < size && !predicate.test((T) storage[point]));

            return point < size;
        }

        @Override
        public T next() {
            return (T) storage[point];
        }

    }

    private void checkRangeAndThrowException(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {
        return "CustomeList " + Arrays.toString(Arrays.copyOfRange(storage, 0, size)) + "";
    }

}
