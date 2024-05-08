package hw2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IDLListTest {
    @Test
    void basicTest(){
        IDLList<Integer> test = new IDLList<Integer>();
        test.add(4);
        test.add(7);
        test.add(0,5);
        test.append(12);
        assertEquals(test.toString(),"5, 4, 7, 12");
        assertEquals(test.get(1),4);
        assertEquals(test.getHead(),5);
        assertEquals(test.getLast(),12);
        assertEquals(test.size(),4);

        assertEquals(test.remove(),5);
        assertEquals(test.toString(),"4, 7, 12");
        assertEquals(test.size(),3);

        assertEquals(test.removeLast(),12);
        assertEquals(test.toString(),"4, 7");
        assertEquals(test.size(),2);
        

        assertEquals(test.removeAt(1),7);
        assertEquals(test.toString(),"4");
        assertEquals(test.size(),1);

        assertFalse(test.remove(30));
        assertEquals(test.toString(),"4");
        assertEquals(test.size(),1);

        assertTrue(test.remove(4));
        assertEquals(test.toString(),"");
        assertEquals(test.size(),0);
    }
    @Test
    void throwsTest(){
        IDLList<Integer> list = new IDLList<Integer>();
        list.add(4);
        list.add(7);
        list.add(0,5);
        list.append(12);
        assertEquals(list.toString(),"5, 4, 7, 12");
        assertEquals(list.size(),4);

        assertThrows(IndexOutOfBoundsException.class, ()->list.add(-1,6));
        assertThrows(IndexOutOfBoundsException.class, ()->list.add(4,6));
        assertThrows(IndexOutOfBoundsException.class, ()->list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, ()->list.get(7));
    }
    @Test 
    void emptyList(){
        IDLList<Integer> list = new IDLList<Integer>();
        assertThrows(IndexOutOfBoundsException.class, ()->list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, ()->list.get(0));
        assertThrows(IndexOutOfBoundsException.class, ()->list.get(12));
        assertThrows(IllegalStateException.class, ()->list.remove());
        assertThrows(IllegalStateException.class, ()->list.removeLast());
        assertThrows(IndexOutOfBoundsException.class, ()->list.get(-1));
        assertNull(list.getHead());
        assertNull(list.getLast());

    }
}
