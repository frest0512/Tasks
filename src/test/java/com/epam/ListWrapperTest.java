package com.epam;

import com.epam.task2.ListWrapper;
import com.epam.task2.llegalModificationException;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ListWrapperTest {
    @Test
    public void containsTest() {
        List<Integer> list = new ListWrapper<>(new ArrayList<>(Arrays.asList(1, 2, 3, 4)), new ArrayList<>(Arrays.asList(5, 6, 7, 8, null)));
        assertTrue(list.contains(1));
        assertTrue(list.contains(6));
        assertTrue(list.contains(null));
    }

    @Test
    public void containsAllTrueTest() {
        List<Integer> list = new ListWrapper<>(new ArrayList<>(Arrays.asList(1, 2, 3, 4)), new ArrayList<>(Arrays.asList(5, 6, 7, 8, null)));
        ArrayList<Integer> listToCheck = new ArrayList<>();
        listToCheck.add(4);
        listToCheck.add(1);
        listToCheck.add(2);
        listToCheck.add(null);
        listToCheck.add(6);
        assertTrue(list.containsAll(listToCheck));
    }

    @Test
    public void containsAllFalseTest() {
        List<Integer> list = new ListWrapper<>(new ArrayList<>(Arrays.asList(1, 2, 3, 4)), new ArrayList<>(Arrays.asList(5, 6, 7, 8, null)));
        ArrayList<Integer> listToCheck = new ArrayList<>();
        listToCheck.add(4);
        listToCheck.add(1);
        listToCheck.add(20);
        listToCheck.add(null);
        listToCheck.add(6);
        assertFalse(list.containsAll(listToCheck));
    }

    @Test
    public void containsAllFalseWithEmptyListTest() {
        List<Integer> list = new ListWrapper<>(new ArrayList<>(Arrays.asList(1, 2, 3, 4)), new ArrayList<>(Arrays.asList(5, 6, 7, 8, null)));
        ArrayList<Integer> listToCheck = new ArrayList<>();
        assertFalse(list.containsAll(listToCheck));
    }

    @Test
    public void clearTest() {
        List<Integer> list = new ListWrapper<>(new ArrayList<>(), new ArrayList<>(Arrays.asList(5, 6, 7, 8, null)));
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void sizeTest() {
        List<Integer> list = new ListWrapper<>(new ArrayList<>(Arrays.asList(1, 2, 3, 4)), new ArrayList<>(Arrays.asList(5, 6, 7, 8, null)));
        assertEquals(9, list.size());
    }

    @Test
    public void isEmptyFalseTest() {
        List<Integer> list = new ListWrapper<>(new ArrayList<>(Arrays.asList(1, 2, 3, 4)), new ArrayList<>(Arrays.asList(5, 6, 7, 8, null)));
        assertFalse(list.isEmpty());
    }

    @Test
    public void isEmptyTrueTest() {
        assertEquals(true, new ListWrapper<>(new ArrayList<>(), new ArrayList<>()).isEmpty());
    }

    @Test
    public void getTest() {
        List<Integer> list = new ListWrapper<>(new ArrayList<>(Arrays.asList(1, 2, 3, 4)), new ArrayList<>(Arrays.asList(5, 6, 7, 8)));
        assertEquals(new Integer(2), list.get(1));
        assertEquals(new Integer(5), list.get(4));
    }

    @Test
    public void addTest() {
        List<Integer> list = new ListWrapper<>(new ArrayList<>(Arrays.asList(1, 2, 3, 4)), new ArrayList<>(Arrays.asList(5, 6, 7, 8)));
        list.add(20);
        assertTrue(list.contains(20));
    }

    @Test
    public void addNullTest() {
        List<Integer> list = new ListWrapper<>(new ArrayList<>(Arrays.asList(1, 2, 3, 4)), new ArrayList<>(Arrays.asList(5, 6, 7, 8)));
        list.add(null);
        assertTrue(list.contains(null));
    }

    @Test
    public void addWithIndexObjectTest() {
        List<Integer> list = new ListWrapper<>(new ArrayList<>(Arrays.asList(1, 2, 3, 4)), new ArrayList<>(Arrays.asList(5, 6, 7, 8)));
        list.add(5, 20);
        assertEquals(new Integer(20), list.get(5));
    }

    @Test
    public void addNullWithIndexTest() {
        List<Integer> list = new ListWrapper<>(new ArrayList<>(Arrays.asList(1, 2, 3, 4)), new ArrayList<>(Arrays.asList(5, 6, 7, 8)));
        list.add(4, null);
        assertNull(list.get(4));
    }

    @Test
    public void removeWithIndexTest() {
        List<Integer> list = new ListWrapper<>(new ArrayList<>(Arrays.asList(1, 2, 3, 4)), new ArrayList<>(Arrays.asList(5, 6, 7, 8)));
        list.remove(4);
        assertFalse(list.contains(new Integer(5)));
    }

    @Test
    public void removeObjectTest() {
        List<Integer> list = new ListWrapper<>(new ArrayList<>(Arrays.asList(1, 2, 3, 4)), new ArrayList<>(Arrays.asList(5, 6, 7, 8)));
        list.remove(new Integer(5));
        assertFalse(list.contains(new Integer(5)));
    }

    @Test
    public void removeNullTest() {
        List<Integer> list = new ListWrapper<>(new ArrayList<>(Arrays.asList(1, 2, 3, null)), new ArrayList<>(Arrays.asList(5, 6, 7, 8)));
        list.remove(null);
        assertTrue(list.contains(null));
    }

    @Test
    public void toArrayTest() {
        List<Integer> list = new ListWrapper<>(new ArrayList<>(Arrays.asList(1, 2, 3, 4)), new ArrayList<>(Arrays.asList(5, 6, 7, 8)));
        Object[] actual = list.toArray();
        assertArrayEquals(new Object[]{1, 2, 3, 4, 5, 6, 7, 8}, actual);
    }

    @Test
    public void toArrayTypeTest() {
        List<Integer> list = new ListWrapper<>(new ArrayList<>(Arrays.asList(1, 2, 3, 4)), new ArrayList<>(Arrays.asList(5, 6, 7, 8)));
        Integer[] expected = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};
        Integer[] actualChanged = new Integer[expected.length];
        Integer[] actualReturned = list.toArray(actualChanged);
        assertArrayEquals(expected, actualChanged);
        assertArrayEquals(expected, actualReturned);
    }

    @Test
    public void iteratorTest() {
        List<Integer> list = new ListWrapper<>(new ArrayList<>(Arrays.asList(1, 2)), new ArrayList<>(Arrays.asList(3)));
        Iterator<Integer> iter = list.iterator();
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
        if (iter.hasNext()) {
            assertEquals(new Integer(3), iter.next());
        } else {
            fail();
        }
        assertFalse(iter.hasNext());
    }


    @Test(expected = NoSuchElementException.class)
    public void iteratorEceptionTest() {
        List<Integer> list = new ListWrapper<>(new ArrayList<>(), new ArrayList<>());
        Iterator<Integer> iter = list.iterator();
        iter.next();

    }

    @Test
    public void iteratorFalseTest() {
        List<Integer> list = new ListWrapper<>(new ArrayList<>(), new ArrayList<>());
        Iterator<Integer> iter = list.iterator();
        assertFalse(iter.hasNext());
    }

    @Test(expected = llegalModificationException.class)
    public void addExceptionWithIndexTest() {
        List<Integer> list = new ListWrapper<>(new ArrayList<>(Arrays.asList(1, 2, 3, 4)), new ArrayList<>(Arrays.asList(5, 6, 7, 8)));
        list.add(1, 1);
    }

    @Test(expected = llegalModificationException.class)
    public void setExceptionWithIndexTest() {
        List<Integer> list = new ListWrapper<>(new ArrayList<>(Arrays.asList(1, 2, 3, 4)), new ArrayList<>(Arrays.asList(5, 6, 7, 8)));
        list.set(1, 1);
    }

    @Test(expected = llegalModificationException.class)
    public void removeExceptionWithIndexTest() {
        List<Integer> list = new ListWrapper<>(new ArrayList<>(Arrays.asList(1, 2, 3, 4)), new ArrayList<>(Arrays.asList(5, 6, 7, 8)));
        list.remove(1);
    }

    @Test(expected = llegalModificationException.class)
    public void addAllExceptionWithIndexTest() {
        List<Integer> list = new ListWrapper<>(new ArrayList<>(Arrays.asList(1, 2, 3, 4)), new ArrayList<>(Arrays.asList(5, 6, 7, 8)));
        list.addAll(1, new ArrayList<>());
    }

    @Test(expected = llegalModificationException.class)
    public void clearExceptionWithIndexTest() {
        List<Integer> list = new ListWrapper<>(new ArrayList<>(Arrays.asList(1, 2, 3, 4)), new ArrayList<>(Arrays.asList(5, 6, 7, 8)));
        list.clear();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getExceptionTest() {
        List<Integer> list = new ListWrapper<>(new ArrayList<>(Arrays.asList(1, 2, 3, 4)), new ArrayList<>(Arrays.asList(5, 6, 7, 8)));
        list.get(-1);
    }


}
