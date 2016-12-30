package com.epam.task3;

import java.util.ArrayList;
import java.util.Collection;

public class ListSet<T> extends ArrayList<T> {

    public ListSet(Collection<? extends T> c) {
        super(c);
        if (checkIfListContainsSameElements(c)) {
            throw new DuplicateElementException();
        }

    }

    @Override
    public boolean add(T element) {
        if (contains(element)) {
            throw new DuplicateElementException();
        } else {
            return super.add(element);
        }

    }

    @Override
    public void add(int index, T element) {
        if (contains(element)) {
            throw new DuplicateElementException();
        } else {
            super.add(index, element);
        }
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean flag = true;
        for (T el : c) {
            if (contains(el)) {
                flag = false;
            }
        }
        if (flag) {
            return super.addAll(c);
        }
        throw new DuplicateElementException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        boolean flag = true;
        for (T el : c) {
            if (contains(el)) {
                flag = false;
            }
        }
        if (flag) {
            return super.addAll(index, c);
        }
        throw new DuplicateElementException();
    }

    @Override
    public T set(int index, T element) {
        if (contains(element)) {
            if (indexOf(element) == index) {
                return super.set(index, element);
            }
            throw new DuplicateElementException();
        } else {
            super.set(index, element);
            return get(index);
        }
    }

    private boolean checkIfListContainsSameElements(Collection<? extends T> c) {
        boolean flag = false;
        int cursor = 0;
        for (T el : c) {
            if (c.contains(el) && indexOf(el) != cursor) {
                flag = true;
            }
            cursor++;
        }
        return flag;
    }

}
