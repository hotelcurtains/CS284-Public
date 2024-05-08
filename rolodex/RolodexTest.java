package rolodex;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class RolodexTest {
    @Test
    void emptyTest(){
        Rolodex r = new Rolodex();
        String emptyRolodex = "Separator A\n" + //
                        "Separator B\n" + //
                        "Separator C\n" + //
                        "Separator D\n" + //
                        "Separator E\n" + //
                        "Separator F\n" + //
                        "Separator G\n" + //
                        "Separator H\n" + //
                        "Separator I\n" + //
                        "Separator J\n" + //
                        "Separator K\n" + //
                        "Separator L\n" + //
                        "Separator M\n" + //
                        "Separator N\n" + //
                        "Separator O\n" + //
                        "Separator P\n" + //
                        "Separator Q\n" + //
                        "Separator R\n" + //
                        "Separator S\n" + //
                        "Separator T\n" + //
                        "Separator U\n" + //
                        "Separator V\n" + //
                        "Separator W\n" + //
                        "Separator X\n" + //
                        "Separator Y\n" + //
                        "Separator Z\n";
        assertEquals(r.toString(),emptyRolodex);
        assertFalse(r.contains("Joey"));
        assertEquals(r.size(),0);
        assertThrows(IllegalArgumentException.class, ()->r.lookup("Barry"));
        assertThrows(IllegalArgumentException.class, ()->r.removeCard("Macy", "867"));
        assertThrows(IllegalArgumentException.class, ()->r.removeAllCards("Elanor"));

        //assertEquals(r.toString(),emptyRolodex);
        //assertFalse(test.remove(30));
        //assertTrue(test.remove(4));
        //assertNull(list.getLast());
        //assertThrows(IllegalArgumentException.class, ()->r.removeCard("Macy", "867"));
    }
    
    @Test
    void practiceTest(){
        Rolodex r = new Rolodex();
        r.addCard("Macy", "5309");
        assertThrows(IllegalArgumentException.class, ()->r.removeCard("Macy", "867"));
        assertFalse(r.contains("Joey"));
        assertTrue(r.contains("Macy"));
        assertEquals(r.size(),1);
        ArrayList<String> compare1 = new ArrayList<String>();
        compare1.add("5309");
        assertEquals(r.lookup("Macy"),compare1);
        System.out.println("output");
    }
    @Test
    void provided(){
        Rolodex r = new Rolodex();
		assertEquals(r.size(),0);

		r.addCard("Chloe", "123");
		r.addCard("Chad", "23");
		r.addCard("Cris", "3");
		r.addCard("Cris", "34");
		r.addCard("Cris", "5");
        assertThrows(IllegalArgumentException.class, ()->r.addCard("Cris", "3"));
		r.addCard("Maddie", "23");
        assertThrows(IllegalArgumentException.class, ()->r.addCard("6789", "23"));

		assertTrue(r.contains("Chad"));
        assertFalse(r.contains("khaki"));
        assertEquals(r.size(),6);
        ArrayList<String> compare2 = new ArrayList<String>();
        compare2.add("3");
        compare2.add("34");
        compare2.add("5");
		assertEquals(r.lookup("Cris"), compare2);
        assertThrows(IllegalArgumentException.class, ()->r.lookup("Barry"));

		assertTrue(r.contains("Chloe"));
		assertFalse(r.contains("Albert"));

		r.removeCard("Cris","5");

		r.removeAllCards("Cris");
        assertThrows(IllegalArgumentException.class, ()->r.removeAllCards("Jerry"));

		r.removeAllCards("Chad");
		r.removeAllCards("Chloe");

		r.addCard("Chloe", "123");
		r.addCard("Chad", "23");
		r.addCard("Cris", "3");
		r.addCard("Cris", "4");

		r.initializeCursor();
		r.nextSeparator();
		assertEquals(r.currentEntryToString(),"Separator B");
		r.nextEntry();
		assertEquals(r.currentEntryToString(),"Separator C");
		r.nextEntry();
		assertEquals(r.currentEntryToString(),"Name: Chad, Cell: 23");
        r.nextSeparator();
		assertEquals(r.currentEntryToString(),"Separator D");
    }
}
