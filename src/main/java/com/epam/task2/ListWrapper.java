package com.epam.task2;

import java.util.*;


public class ListWrapper<T> implements List<T> {

    private List<T> unmodifiable;
    private List<T> modifiable;


    public ListWrapper(List<T> unmodifiable, List<T> modifiable) {
        this.unmodifiable = unmodifiable;
        this.modifiable = modifiable;

    }

    @Override
    public int size() {
        return modifiable.size() + unmodifiable.size();
    }

    @Override
    public boolean isEmpty() {
        return modifiable.size() + unmodifiable.size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return modifiable.contains(o) || unmodifiable.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new WrapperIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] toReturn = new Object[unmodifiable.size() + modifiable.size()];
        System.arraycopy(unmodifiable.toArray(), 0, toReturn, 0, unmodifiable.size());
        System.arraycopy(modifiable.toArray(), 0, toReturn, unmodifiable.size(), modifiable.size());
        return toReturn;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length != unmodifiable.size() + modifiable.size()) {
            T[] toReturn = (T[]) new Object[unmodifiable.size() + modifiable.size()];
            System.arraycopy(unmodifiable.toArray(), 0, toReturn, 0, unmodifiable.size());
            System.arraycopy(modifiable.toArray(), 0, toReturn, unmodifiable.size(), modifiable.size());
            return toReturn;
        } else {
            System.arraycopy(unmodifiable.toArray(), 0, a, 0, unmodifiable.size());
            System.arraycopy(modifiable.toArray(), 0, a, unmodifiable.size(), modifiable.size());
            return a;
        }
    }

    @Override
    public boolean add(T e) {
        return modifiable.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return modifiable.remove(o);
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

        return modifiable.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        checkIfIndexInRangeOfModifiable(index);
        return modifiable.addAll(index, c);
    }

    private void checkIfIndexInRangeOfModifiable(int index) {
        if (index < unmodifiable.size()) {
            throw new llegalModificationException();
        }

    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Object[] el = c.toArray();
        for (int i = 0; i < el.length; i++) {
            if (unmodifiable.contains(el[i])) {
                throw new llegalModificationException();
            }
        }
        return modifiable.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Object[] el = c.toArray();
        for (int i = 0; i < el.length; i++) {
            if (!unmodifiable.contains(el[i])) {
                throw new llegalModificationException();
            }
        }
        return modifiable.retainAll(c);
    }

    @Override
    public void clear() {
        if (unmodifiable.size() > 0) {
            throw new llegalModificationException();
        } else {
            modifiable.clear();
        }
    }

    @Override
    public T get(int index) {
        return (index >= unmodifiable.size()) ? modifiable.get(index - unmodifiable.size()) : unmodifiable.get(index);
    }

    @Override
    public T set(int index, T element) {
        checkIfIndexInRangeOfModifiable(index);
        return modifiable.set(index - unmodifiable.size(), element);
    }

    @Override
    public void add(int index, T element) {
        checkIfIndexInRangeOfModifiable(index);
        modifiable.add(index - unmodifiable.size(), element);

    }

    @Override
    public T remove(int index) {
        checkIfIndexInRangeOfModifiable(index);
        return modifiable.remove(index - unmodifiable.size());
    }

    @Override
    public int indexOf(Object o) {
        int indexUnmodifiable = unmodifiable.indexOf(o);
        int indexModifiable = modifiable.indexOf(o);
        return indexUnmodifiable < 0 ? indexModifiable : indexUnmodifiable;
    }

    @Override
    public int lastIndexOf(Object o) {
        int indexUnmodifiable = unmodifiable.lastIndexOf(o);
        int indexModifiable = modifiable.lastIndexOf(o);
        return indexUnmodifiable < 0 ? indexModifiable : indexUnmodifiable;
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


    @Override
    public String toString() {
        return "ListWrapper unmodifiable = " + unmodifiable.toString() + " modifiable = " + modifiable.toString();
    }


    class WrapperIterator<T> implements Iterator<T> {
        private int cursor = 0;
        private Iterator modIterator = modifiable.iterator();
        private Iterator unmodIterator = unmodifiable.iterator();


        @Override
        public boolean hasNext() {
            return modIterator.hasNext() || unmodIterator.hasNext();

        }

        @Override
        public T next() {
            if (cursor >= unmodifiable.size() + modifiable.size()) {
                throw new NoSuchElementException();
            }
            if (unmodIterator.hasNext()) {
                cursor++;
                return (T) unmodIterator.next();
            } else if (modIterator.hasNext()) {
                cursor++;
                return (T) modIterator.next();
            } else {
                return (T) modIterator.next();
            }
        }

    }

}
