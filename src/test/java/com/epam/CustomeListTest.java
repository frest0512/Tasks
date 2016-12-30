package com.epam;

import com.epam.task1.util.CustomeList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class CustomeListTest {

    @Test
    public void toArrayTypeTest() {
        List<Integer> list = new CustomeList<Integer>(Arrays.asList(1, 2, 3, 4));
        Integer[] expected = new Integer[]{1, 2, 3, 4};
        Integer[] actualChanged = new Integer[expected.length];
        Integer[] actualREturned = list.toArray(actualChanged);
        assertArrayEquals(expected, actualChanged);
        assertArrayEquals(expected, actualREturned);
        assertArrayEquals(actualChanged, actualREturned);
    }

    @Test
    public void addALlTest() {
        List<Integer> list = new CustomeList<Integer>(Arrays.asList(1, 2, 3, 4));
        boolean result = list.addAll(1, new ArrayList<>(Arrays.asList(9, 8, 7, 6)));
        assertTrue(result);
        assertArrayEquals(new Object[]{1, 9, 8, 7, 6, 2, 3, 4}, list.toArray());
    }

    @Test
    public void removeALlTest() {
        List<Integer> list = new CustomeList<Integer>(Arrays.asList(1, 2, 3, 4));
        boolean result = list.removeAll(new ArrayList<>(Arrays.asList(1, 2, 3)));
        assertTrue(result);
        assertArrayEquals(new Object[]{4}, list.toArray());
    }

    @Test
    public void addTest() {
        List<Integer> list = new CustomeList<Integer>(Arrays.asList(1, 2, 3, 4));
        list.add(20);
        assertTrue(list.contains(20));
    }

    @Test
    public void addNullTest() {
        List<Integer> list = new CustomeList<Integer>(Arrays.asList(1, 2, 3, 4));
        list.add(null);
        assertTrue(list.contains(null));
    }

    @Test
    public void addWithIndexObjectTest() {
        List<Integer> list = new CustomeList<Integer>(Arrays.asList(1, 2, 3, 4));
        list.add(1, 20);
        assertTrue(list.contains(20));
    }

    @Test
    public void addWithIndexNullTest() {
        List<Integer> list = new CustomeList<Integer>(Arrays.asList(1, 2, 3, 4));
        list.add(1, null);
        assertNull(list.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addWithIndexExceptionTest() {
        List<Integer> list = new CustomeList<Integer>(Arrays.asList(1, 2, 3, 4));
        list.add(-1, null);
    }

    @Test
    public void getTest() {
        List<Integer> list = new CustomeList<Integer>(Arrays.asList(1, 2, 3, 4));
        Integer i = list.get(1);
        assertEquals(new Integer(2), i);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getExceptionMoreThenSizeTest() {
        List<Integer> list = new CustomeList<Integer>(Arrays.asList(1, 2, 3, 4));
        list.get(100);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getExceptionLessThenSizeTest() {
        List<Integer> list = new CustomeList<Integer>(Arrays.asList(1, 2, 3, 4));
        list.get(-10);
    }

    @Test
    public void removeIndexTrueTest() {
        List<Integer> list = new CustomeList<Integer>(Arrays.asList(1, 2, 3, 4));
        Integer i = list.remove(1);
        assertFalse(list.contains(i));
    }

    @Test
    public void removeIndexFalseTest() {
        List<Integer> list = new CustomeList<Integer>(Arrays.asList(1, 2, 3, 4));
        Integer actual = list.remove(1);
        assertEquals(new Integer(2), actual);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIndexLessZiroTest() {
        List<Integer> list = new CustomeList<Integer>(Arrays.asList(1, 2, 3, 4));
        list.remove(-5);
    }

    @Test
    public void removeObjectTest() {
        List<Integer> list = new CustomeList<Integer>(Arrays.asList(1, 2, 3, 4));
        list.remove(new Integer(2));
        assertFalse(list.contains(2));
    }

    @Test
    public void removNullTrueTest() {
        List<Integer> list = new CustomeList<Integer>(Arrays.asList(null, 1, 2, 3, 4));
        boolean actual = list.remove(null);
        assertTrue(actual);
    }

    @Test
    public void removNullFalseTest() {
        List<Integer> list = new CustomeList<Integer>(Arrays.asList(1, 2, 3, 4));
        boolean actual = list.remove(null);
        assertFalse(actual);
    }

    @Test
    public void iteratorTest() {
        CustomeList<Integer> list = new CustomeList<Integer>(Arrays.asList(1, 2, 3));
        Iterator<Integer> iter = list.iterator();
        if (iter.hasNext())
            assertEquals(new Integer(1), iter.next());
        else
            fail();
        if (iter.hasNext())
            assertEquals(new Integer(2), iter.next());
        else
            fail();
        if (iter.hasNext())
            assertEquals(new Integer(3), iter.next());
        else
            fail();
        if (iter.hasNext())
            fail();
    }

    @Test
    public void iteratorFilterTest() {
        CustomeList<Integer> list = new CustomeList<Integer>(Arrays.asList(1, 2, 3));
        Iterator<Integer> iter = list.iterator((obj) -> obj == 2);
        if (iter.hasNext())
            assertEquals(new Integer(2), iter.next());
        else
            fail();
        if (iter.hasNext())
            fail();
    }

    @Test
    public void sizeTest() {
        CustomeList<Integer> list = new CustomeList<Integer>(Arrays.asList(1, 2, 3));
        assertEquals(3, list.size());
    }

    @Test
    public void isEmptyFalseTest() {
        CustomeList<Integer> list = new CustomeList<Integer>(Arrays.asList(1, 2, 3));
        assertFalse(list.isEmpty());
    }

    @Test
    public void isEmptyTrueTest() {
        assertEquals(true, new CustomeList<>().isEmpty());
    }

    @Test
    public void containsTrueTest() {
        CustomeList<Integer> list = new CustomeList<Integer>(Arrays.asList(1, 2, 3));
        assertTrue(list.contains(2));
    }

    @Test
    public void containsFalseTest() {
        CustomeList<Integer> list = new CustomeList<Integer>(Arrays.asList(1, 2, 3));
        assertFalse(list.contains(100));
    }

    @Test
    public void toArrayObjectTest() {
        CustomeList<Integer> list = new CustomeList<Integer>(Arrays.asList(1, 2, 3));
        Object[] actual = list.toArray();
        assertEquals(new Integer(1), actual[0]);
        assertEquals(new Integer(2), actual[1]);
        assertEquals(new Integer(3), actual[2]);
    }

    @Test
    public void clearTest() {
        CustomeList<Integer> list = new CustomeList<Integer>(Arrays.asList(1, 2, 3));
        list.clear();
        assertEquals(0, list.size());
    }

}
