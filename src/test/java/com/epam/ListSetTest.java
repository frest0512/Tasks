package com.epam;

import com.epam.task3.DuplicateElementException;
import com.epam.task3.ListSet;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Dmitriy on 31.10.16.
 */
public class ListSetTest {
    @Test(expected = DuplicateElementException.class)
    public void constructorExceptionTest() {
        new ListSet<>(Arrays.asList(1, 2, 3, 4, 4));
    }

    @Test(expected = DuplicateElementException.class)
    public void addExceptionTest() {
        ArrayList<Integer> list = new ListSet<>(Arrays.asList(1, 2, 3, 4, 4));
        list.add(2);
    }

    @Test(expected = DuplicateElementException.class)
    public void addOnIndexExceptionTest() {
        ArrayList<Integer> list = new ListSet<>(Arrays.asList(1, 2, 3, 4, 4));
        list.add(1, 2);
    }

    @Test(expected = DuplicateElementException.class)
    public void setOnIndexExceptionTest() {
        ArrayList<Integer> list = new ListSet<>(Arrays.asList(1, 2, 3, 4, 4));
        list.set(3, 2);
    }

    @Test(expected = DuplicateElementException.class)
    public void addAllExceptionTest() {
        ArrayList<Integer> list = new ListSet<>(Arrays.asList(1, 2, 3, 4, 4));
        list.addAll(new ArrayList<>(Arrays.asList(3, 4, 5)));
    }

    @Test(expected = DuplicateElementException.class)
    public void addAllOnIndexExceptionTest() {
        ArrayList<Integer> list = new ListSet<>(Arrays.asList(1, 2, 3, 4, 4));
        list.addAll(1, new ArrayList<>(Arrays.asList(3, 4, 5)));
    }

    @Test
    public void constructorOKTest() {
        ArrayList<Integer> actual = new ListSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Integer[] expected = new Integer[]{1, 2, 3, 4, 5};
        assertArrayEquals(expected, actual.toArray());
    }

    @Test
    public void containsTrueTest() {
        ArrayList<Integer> actual = new ListSet<>(Arrays.asList(1, 2, 3, 4, 5));
        assertTrue(actual.contains(new Integer(3)));
    }

    @Test
    public void containsFalseTest() {
        ArrayList<Integer> actual = new ListSet<>(Arrays.asList(1, 2, 3, 4, 5));
        assertFalse(actual.contains(new Integer(10)));
    }

    @Test
    public void addTrueTest() {
        ArrayList<Integer> actual = new ListSet<>(Arrays.asList(1, 2, 3, 4, 5));
        assertTrue(actual.add(10));
        assertTrue(actual.contains(new Integer(10)));
    }

    @Test
    public void addOnIndexTest() {
        ArrayList<Integer> actual = new ListSet<>(Arrays.asList(1, 2, 3, 4, 5));
        actual.add(1, 10);
        assertTrue(actual.contains(new Integer(10)));
        assertEquals(new Integer(10), actual.get(1));
    }

    @Test
    public void setOnIndexTest() {
        ArrayList<Integer> actual = new ListSet<>(Arrays.asList(1, 2, 3, 4, 5));
        actual.set(1, 10);
        assertTrue(actual.contains(new Integer(10)));
        assertEquals(new Integer(10), actual.get(1));
    }

    @Test
    public void addAllTrueTest() {
        ArrayList<Integer> list = new ListSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Integer[] actual = new Integer[]{1, 2, 3, 4, 5, 8, 9, 0};
        assertTrue(list.addAll(new ArrayList<>(Arrays.asList(8, 9, 0))));
        assertArrayEquals(actual, list.toArray());
    }

    @Test
    public void addAllTrueOnIndexTest() {
        ArrayList<Integer> list = new ListSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Integer[] actual = new Integer[]{1, 8, 9, 0, 2, 3, 4, 5};
        assertTrue(list.addAll(1, new ArrayList<>(Arrays.asList(8, 9, 0))));
        assertArrayEquals(actual, list.toArray());
    }
}
