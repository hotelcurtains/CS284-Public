package rolodex;

import java.util.ArrayList;

public class Rolodex {

	/**
	 * Stores the position of the cursor, moveable with Rolodex's methods.
	 */
	private Entry cursor;

	/**
	 * Stores references to all alphabetical Separators.
	 */
	private final Entry[] index;

	// Constructor

	/**
	 * Constructs a new Rolodex with ordered alphabetical Separators and no Cards.
	 */
	Rolodex() {
		index = new Entry[26];
		char[] ab = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

		// set head and tail in place
		index[0] = new Separator(null, null, 'A');
		index[25] = new Separator(null, index[0], 'Z');
		index[0].prev = index[25];
		
		for (int i = 1; i < ab.length-1; i++) { // place all body separators
			index[i] = new Separator(null, null, ab[i]);
		}
		for (int i = 0; i < ab.length-1; i++) { // set all body nexts 
			index[i].next = index[i+1];
		}
		for (int i = 1; i < ab.length; i++) { // set all body prevs
			index[i].prev = index[i-1];
		}
	}

	/**
	 * Returns whether any Cards in this Rolodex have this name.
	 * 
	 * @param name The name to search for
	 * @return a boolean, true if there are any Cards in this Rolodex with this name, false otherwise.
	 */
	public Boolean contains(String name) {
	    Entry pointer = index[0];
		while(pointer.next != index[0]){
			if (name.equals(pointer.getName())){
				return true;
			}
			pointer = pointer.next;
		}
		return false;
	}

	/**
	 * Returns the amount of Cards in this Rolodex. Separators are not counted.
	 * @return the amount of Cards in this Rolodex.
	 */
	public int size() {
		int counter = 0;
		Entry pointer = index[0];
		while(pointer.next != index[0]){
			counter += pointer.size();
			pointer = pointer.next;
		}
		return counter;
	}

	/**
	 * Lists all cell numbers associated with the given name.
	 * @param name The name to search for.
	 * @return an ArrayList of all cell numbers in this Rolodex associated with the given name.
	 * @throws IllegalArgumentException if this Rolodex contains no Cards with the given name.
	 */
	public ArrayList<String> lookup(String name) {
		if(this.contains(name)){
			ArrayList<String> out = new ArrayList<String>();
			Entry pointer = index[0];
			while(pointer.next != index[0]){
				if (name.equals(pointer.getName())){
					out.add(((Card)pointer).getCell());
				}
				pointer = pointer.next;
			}
			return out;
		} else {
			throw new IllegalArgumentException("lookup: name not found");
		}
	}

	/**
	 * Adds a new Card into the correct position in this Rolodex with the given information.
	 * @param name The name to put on the Card.
	 * @param cell The cell to put on the Card.
	 * @throws IllegalArgumentException if this exact Card already exists in this Rolodex
	 * @throws IllegalArgumentException if the given name cannot be sorted into this Rolodex (i.e. if it does not start with a letter).
	 */
	public void addCard(String name, String cell) {
		// make sure we'll actually be able to sort the new card -- otherwise throw an exception
		if(Character.isLetter(name.charAt(0))){
			Card in = new Card(null,null,name,cell);
			Entry pointer = index[0];
			// move pointer to correct position
			while(name.compareTo(pointer.next.getName()) >= 0){
				// if at any point we find a total duplicate, throw exception
				if (in.toString().equals(pointer.toString())){
					throw new IllegalArgumentException("addCard: duplicate entry");
				}
				pointer = pointer.next;
			}
			
			// insert new card at this position
			pointer.next.prev = in;
			in.next = pointer.next;
			pointer.next = in;
			in.prev = pointer;

		} else {
			throw new IllegalArgumentException("addCard: name "+ name +" cannot be sorted with first initial "+name.charAt(0));
		}
	}

	/**
	 * Removes a Card with the given name and cell.
	 * @param name The name on the card to remove.
	 * @param cell The cell on the card to remove.
	 * @throws IllegalArgumentException if this Rolodex contains no Cards with the given name.
	 * @throws IllegalArgumentException if this Rolodex contains any Cards with this name, but none with this name AND this cell.
	 */
	public void removeCard(String name, String cell) {
		if (this.contains(name)) {
			Entry pointer = index[0];
			Card temp = new Card(null,null,name,cell);
			while(pointer.next != index[0]){
				if(temp.toString().equals(pointer.toString())){
					pointer.prev.next = pointer.next;
					pointer.next.prev = pointer.prev;
					return;
				}
				pointer = pointer.next;
			}
			throw new IllegalArgumentException("removeCard: cell for that name does not exist");
		} else {
			throw new IllegalArgumentException("removeCard: name does not exist");
		}	
	}


	/**
	 * Removes all Cards associated with the given name.
	 * @param name The name to search for.
	 * @throws IllegalArgumentException if this Rolodex contains no Cards with the given name.
	 */
	public void removeAllCards(String name) {
		if (this.contains(name)) {
			Entry pointer = index[0];
			while(pointer.next != index[0]){
				if(name.equals(pointer.getName())){
					pointer.prev.next = pointer.next;
					pointer.next.prev = pointer.prev;
				}
				pointer = pointer.next;
			}
		} else {
			throw new IllegalArgumentException("removeAllCards: name does not exist");
		}
	}

	/**
	 * Returns a list of all entries in this Rolodex.
	 * @return a listing of every Entry in this Rolodex, separated by line breaks.
	 */
	public String toString() {
		Entry current = index[0];

		StringBuilder b = new StringBuilder();
		while (current.next!=index[0]) {
			b.append(current.toString()+"\n");
			current=current.next;
		}
		b.append(current.toString()+"\n");		
		return b.toString();
	}
	
	// Cursor operations

	/**
	 * Initializes the cursor, placing it at the first separator (A).
	 */
	public void initializeCursor() {
		cursor = index[0];
	}

	/**
	 * Advances the cursor to the next Separator.
	 */
	public void nextSeparator() {
		while (!cursor.next.isSeparator()) {
			cursor = cursor.next;
		}
		cursor = cursor.next;
	}

	/**
	 * Advances this cursor to the next Entry.
	 */
	public void nextEntry() {
		cursor = cursor.next;
	}

	/**
	 * Returns the String representation of the Entry this cursor is currently on.
	 * @return the String representation of the Entry this cursor is currently on.
	 */
	public String currentEntryToString() {
		return cursor.toString();
	}


	public static void main(String[] args) {
		Rolodex r = new Rolodex();
		System.out.println(r);
		System.out.println(r.size());				//0

		r.addCard("Chloe", "123");
		r.addCard("Chad", "23");
		r.addCard("Cris", "3");
		r.addCard("Cris", "34");
		r.addCard("Cris", "5");
		//r.addCard("Cris", "4");					//IllegalArgumentException
		r.addCard("Maddie", "23");
		//r.addCard("6789", "23");					//IllegalArgumentException

		System.out.println(r);
		System.out.println(r.contains("Chad"));		//true
		System.out.println(r.contains("khaki"));	//false
		System.out.println(r.size());				//6
		System.out.println(r.lookup("Cris"));		//[3, 34, 5]
		//System.out.println(r.lookup("Barry"));	//IllegalArgumentException
		System.out.println(r.contains("Chloe"));	//true
		System.out.println(r.contains("Albert"));	//false

		r.removeCard("Cris","5");
		System.out.println(r);


		r.removeAllCards("Cris");
		//r.removeAllCards("Jerry"); 				//IllegalArgumentException
		System.out.println(r);

		r.removeAllCards("Chad");
		r.removeAllCards("Chloe");

		r.addCard("Chloe", "123");
		r.addCard("Chad", "23");
		r.addCard("Cris", "3");
		r.addCard("Cris", "4");
		System.out.println(r);

		r.initializeCursor();
		r.nextSeparator();
		System.out.println(r.currentEntryToString());	//Separator B
		r.nextEntry();
		System.out.println(r.currentEntryToString());	//Separator C
		r.nextEntry();
		System.out.println(r.currentEntryToString());	//Name: Chad, Cell: 23
	}

}