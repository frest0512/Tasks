package com.epam;

import com.epam.task2.CopyOnWriteCustomeList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Dmitriy on 30.10.16.
 */
public class CopyOnWriteListTest {


    @Test
    public void toArrayTest() {
        List<Integer> list = new CopyOnWriteCustomeList<Integer>(Arrays.asList(1, 2, 3, 4));
        assertArrayEquals(new Object[]{1, 2, 3, 4}, list.toArray());
    }

    @Test
    public void toArrayTypeTest() {
        List<Integer> list = new CopyOnWriteCustomeList<Integer>(Arrays.asList(1, 2, 3, 4));
        Integer[] expected = new Integer[]{1, 2, 3, 4};
        Integer[] actualChanged = new Integer[expected.length];
        Integer[] actualREturned = list.toArray(actualChanged);
        assertArrayEquals(expected, actualChanged);
        assertArrayEquals(expected, actualREturned);
        assertArrayEquals(actualChanged, actualREturned);
    }

    @Test
    public void containsTest() {
        CopyOnWriteCustomeList<Integer> list = new CopyOnWriteCustomeList<>(Arrays.asList(1, 2));
        assertTrue(list.contains(new Integer(2)));
    }

    @Test
    public void sizeTest() {
        CopyOnWriteCustomeList<Integer> list = new CopyOnWriteCustomeList<Integer>(Arrays.asList(1, 2, 3));
        assertEquals(3, list.size());
    }

    @Test
    public void isEmptyFalseTest() {
        CopyOnWriteCustomeList<Integer> list = new CopyOnWriteCustomeList<Integer>(Arrays.asList(1, 2, 3));
        assertFalse(list.isEmpty());
    }

    @Test
    public void isEmptyTrueTest() {
        assertTrue(new CopyOnWriteCustomeList<>().isEmpty());
    }


    @Test
    public void addAllTest() {
        CopyOnWriteCustomeList<Integer> list = new CopyOnWriteCustomeList<>(Arrays.asList(1, 2));
        Iterator<Integer> iter = list.iterator();
        ArrayList<Integer> toAdd = new ArrayList<>();
        toAdd.add(new Integer(3));
        toAdd.add(new Integer(4));
        list.addAll(toAdd);
        met(iter);
        assertTrue(list.contains(new Integer(3)));
        assertTrue(list.contains(new Integer(4)));
    }

    @Test
    public void addTest() {
        CopyOnWriteCustomeList<Integer> list = new CopyOnWriteCustomeList<>(Arrays.asList(1, 2));
        Iterator<Integer> iter = list.iterator();
        list.add(20);
        met(iter);
        assertTrue(list.contains(new Integer(20)));
    }

    @Test
    public void addNullTest() {
        CopyOnWriteCustomeList<Integer> list = new CopyOnWriteCustomeList<>(Arrays.asList(1, 2));
        Iterator<Integer> iter = list.iterator();
        list.add(null);
        met(iter);
        assertTrue(list.contains(null));

    }

    @Test
    public void addIndexTest() {
        CopyOnWriteCustomeList<Integer> list = new CopyOnWriteCustomeList<>(Arrays.asList(1, 2));
        Iterator<Integer> iter = list.iterator();
        list.add(1, 20);
        met(iter);
        assertTrue(list.contains(new Integer(20)));
    }

    @Test
    public void addNullIndexTest() {
        CopyOnWriteCustomeList<Integer> list = new CopyOnWriteCustomeList<>(Arrays.asList(1, 2));
        Iterator<Integer> iter = list.iterator();
        list.add(1, null);
        met(iter);
        assertTrue(list.contains(null));

    }

    @Test
    public void removeIndexTest() {
        CopyOnWriteCustomeList<Integer> list = new CopyOnWriteCustomeList<>(Arrays.asList(1, 2));
        Iterator<Integer> iter = list.iterator();
        list.remove(1);
        met(iter);
        assertFalse(list.contains(new Integer(2)));
    }

    @Test
    public void removeObjectTest() {
        CopyOnWriteCustomeList<Integer> list = new CopyOnWriteCustomeList<>(Arrays.asList(1, 2));
        Iterator<Integer> iter = list.iterator();
        list.remove(new Integer(1));
        met(iter);
        assertFalse(list.contains(new Integer(1)));
    }

    @Test
    public void removeAllTest() {
        CopyOnWriteCustomeList<Integer> list = new CopyOnWriteCustomeList<>(Arrays.asList(1, 2));
        Iterator<Integer> iter = list.iterator();
        ArrayList<Integer> toRemove = new ArrayList<>();
        toRemove.add(new Integer(2));
        toRemove.add(new Integer(4));
        list.removeAll(toRemove);
        met(iter);
        assertFalse(list.contains(new Integer(2)));
    }

    @Test
    public void retainAllTest() {
        CopyOnWriteCustomeList<Integer> list = new CopyOnWriteCustomeList<>(Arrays.asList(1, 2));
        Iterator<Integer> iter = list.iterator();
        ArrayList<Integer> toRetain = new ArrayList<>();
        toRetain.add(new Integer(2));
        toRetain.add(new Integer(4));
        list.retainAll(toRetain);
        met(iter);
        assertTrue(list.contains(new Integer(2)));
        assertTrue(list.size() == 1);
    }


    public void met(Iterator<?> iter) {
        if (iter.hasNext()) {
            assertEquals(new Integer(1), iter.next());
        } else {
            fail();
        }
        if (iter.hasNext()) {
            assertEquals(new Integer(2), iter.next());
        } else {
            fail();
        }
        assertFalse(iter.hasNext());
    }
}
