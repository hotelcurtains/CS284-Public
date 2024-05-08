package hw2;

import java.util.ArrayList;

public class IDLList<E> {

    /**
     * Nodes make up the inner ArrayList of the IDLList.
     * They store data and pointers to the previous and next Nodes in the IDLList.
     */
    private class Node<E> {
        /**
         * The data held by this node.
         */
        public E data;

        /**
         * A pointer to the next Node.
         */
        public Node<E> next;

        /**
         * A pointer to the previous Node.
         */
        public Node<E> prev;

        /**
         * Construct a new Node with data from elem and no next or prev pointers.
         * 
         * @param elem The data for the Node to hold.
         */
        public Node(E elem) {
            data = elem;
        }

        /**
         * Construct a new Node with data from elem along with next and prev pointers.
         * 
         * @param elem The data for the Node to hold.
         * @param prev The previous Node.
         * @param next The next Node.
         */
        public Node(E elem, Node<E> prev, Node<E> next) {
            data = elem;
            this.prev = prev;
            this.next = next;
        }

        /**
         * Get, as a string, the data held by this node along with information about its
         * pointers.
         * 
         * @return The data of this node, including its next and previous pointers.
         *         Continues recursively to display all linked Nodes' pointers and data.
         */
        public String toString() {
            String out = "" + data;
            return out;
        }
    }

    /**
     * The head (first) node of this IDLList.
     */
    Node<E> head;

    /**
     * The tail (last) node of this IDLList.
     */
    Node<E> tail;

    /**
     * The size (length) of this IDLList.
     */
    int size;

    /**
     * An Array-based list of referenced to this IDLList's Nodes.
     */
    ArrayList<Node<E>> indices;

    /**
     * Constructs a new IDLList with size 0 and no nodes.
     */
    public IDLList() {
        size = 0;
        indices = new ArrayList<Node<E>>();
    }

    /**
     * Adds a new node with the given data to this IDLList at the given index.
     * 
     * @param index The index at which to insert elem.
     * @param elem  The data to insert.
     * @return True.
     * @throws IndexOutOfBoundsException if index given is out of bounds of this IDLList.
     */
    public boolean add(int index, E elem) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index "+index+" out of bounds for this IDLList.");
        }

        Node<E> newNode = new Node<E>(elem);
        indices.add(index, newNode);
        size++;
        if (index == 0) {
            head = newNode;
        } else if (index == size) {
            tail = newNode;
        }

        return true;
    }

    /**
     * Adds a new node to this IDLList at index 0.
     * 
     * @param elem The data to insert.
     * @return True.
     */
    public boolean add(E elem) {
        Node<E> newHead = new Node<E>(elem);
        indices.add(newHead);
        size++;
        head = newHead;

        return true;
    }

    /**
     * Appends a new node with the given data to this IDLList at index = size.
     * 
     * @param elem The data to append.
     * @return True.
     */
    public boolean append(E elem) {
        Node<E> newTail = new Node<E>(elem);
        indices.add(size, newTail);
        size++;
        tail = newTail;

        return true;
    }

    /**
     * Returns the data of the node at the given index of this IDLList.
     * 
     * @param index The index to search for.
     * @return the data of the node at the given index of this IDLList.
     * @throws IndexOutOfBoundsException if index is out of bounds of this IDLList.
     */
    public E get(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index "+index+" out of bounds for this IDLList.");
        } else {
            return indices.get(index).data;
        }
    }

    /**
     * Returns the data of the head node of this IDLList.
     * 
     * @return the data of the head node of this IDLList. null if there is no head.
     */
    public E getHead() {
        if (head != null){
            return head.data;
        } else {
            return null;
        }
    }

    /**
     * Returns the data of last (tail) node of this IDLList.
     * 
     * @return the data of last (tail) node of this IDLList.
     */
    public E getLast() {
        if (tail != null){
            return tail.data;
        } else {
            return null;
        }
    }

    /**
     * Returns the size of this IDLList.
     * 
     * @return the size of this IDLList.
     */
    public int size() {
        return size;
    }

    /**
     * Removes the node at the head of this IDL list and returns what its data used
     * to be.
     * 
     * @return The previous head of the list.
     * @throws IllegalStateException if there is no head node in this IDLList.
     */
    public E remove() {
        if (head == null) {
            throw new IllegalStateException("No head node in this IDLList.");
        }
        E oldHead = head.data;
        indices.remove(0);
        head = indices.get(0);
        size--;
        return oldHead;
    }

    /**
     * Removes the last node (tail) of this IDL list and returns what its data used
     * to be.
     * 
     * @return The data of the previous tail of the list.
     * @throws IllegalStateException if there is no tail node in this IDLList.
     */
    public E removeLast() {
        if (tail == null) {
            throw new IllegalStateException("No tail node in this IDLList.");
        }
        E oldTail = tail.data;
        indices.remove(size - 1);
        size--;
        tail = indices.get(size - 1);
        return oldTail;
    }

    /**
     * Removes the node at the given index in this IDLList and returns its data.
     * 
     * @param index The index of the node to remove.
     * @return The data of the node previously at the given index.
     * @throws IllegalStateException if there is no node at the given index in this
     *                               IDLList.
     */
    public E removeAt(int index) {
        if (index < 0 || index >= size){
            throw new IllegalStateException("No node at " + index + " in this IDLList.");
        } else {
        E oldNode = indices.get(index).data;
        indices.remove(index);
        size--;
        return oldNode;
        }
    }

    /**
     * Removes the first node with data equal to elem.
     * 
     * @param elem The data to search for.
     * @return True if the node is found and removed, false if not.
     */
    public boolean remove(E elem) {
        for (int i = 0; i < indices.size(); i++) {
            if (indices.get(i).data == elem) {
                indices.remove(i);
                size--;
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a string representation of this IDLList.
     * 
     * @return A string representation of this IDLList.
     */
    public String toString() {
        String out = "";
        for (Node<E> c : indices) {
            out += c + ", ";
        }
        if (out.length()>1) {
        out = out.substring(0,out.length()-2);
        }
        return out;
    }

}
