
package linkedList;

import java.util.NoSuchElementException;

/**
 * Represents a generic linked list, and list-oriented operations. The list can
 * hold objects of any type.
 */
public class LinkedList<E> {

    // The following constant is used for localizing the line separator character,
    // which may be different in different host platforms.
    static final String nl = System.getProperty("line.separator");

    private Node<E> first; // Points to the first node in the list (just after the dummy node)
    private Node<E> last; // Points to the last node in the list
    private int size; // Number of list elements

    /**
     * Creates a list with 0 elements.
     */
    public LinkedList() {
        // Starts with a dummy node, to avoid handling empty lists.
        Node<E> dummy = new Node<E>(null);
        this.first = dummy;
        this.last = first;
        this.size = 0;
    }

    /**
     * Adds the given element to the end of this list.
     * 
     * @param e the node to add
     */
    public void add(E e) {
        Node<E> newNode = new Node<E>(e);
        last.next = newNode;
        last = newNode;
        if (size == 0) {
            first.next = newNode;
        }
        size++;
    }

    /**
     * Adds the given element to the beginning of this list.
     * 
     * @param e the element to add to the list.
     */
    public void addFirst(E e) {
        Node<E> newNode = new Node<E>(e);
        newNode.next = first.next;
        first.next = newNode;
        size++;
    }

    /**
     * Adds the given element at the n'th location of this list. The index of the
     * list's first element is 0. The index of the list's last element is the list's
     * size.
     * 
     * @param e     the element to add
     * @param index the index
     * @throws IllegalArgumentException if index is negative or larger than the
     *                                  list's size
     */
    public void add(E e, int index) {
        if (index >= size || index < 0) {
            throw new IllegalArgumentException();
        }
        Node<E> newNode = new Node<E>(e);
        Node<E> c = first;
        for (int i = 0; i < index; i++) {
            c = c.next;
        }
        newNode.next = c.next;
        c.next = newNode;
        size++;
        if (index == size)
            last = newNode;
    }

    /**
     * Returns the index of the given element in this list, or -1 if not found.
     * 
     * @param e the returned index will be the index of e.
     * @return the index of the given element in this list
     */
    public int indexOf(E e) {
        ListIterator<E> it = iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next() == e) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * If the given element exists in this list, removes it and returns true.
     * Otherwise, returns false.
     * 
     * @param e the element to remove.
     * @return if the given element exists in this list, removes it and returns
     *         true. Otherwise, returns false.
     */
    public boolean remove(E e) {
        Node<E> prev = first;
        Node<E> current = first.next;
        for (int i = 0; i < size; i++) {
            if (current.e == e) {
                prev.next = current.next;
                if (i == size - 1) {
                    last = prev;
                }
                current = null;
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    /**
     * Returns the first element in this list.
     * 
     * @return the first element in this list.
     * @throws NoSuchElementException if the list is empty
     */
    public E getFirst() {
        if (size == 0)
            throw new NoSuchElementException();
        return first.next.e;
    }

    /**
     * Returns the last element in this list.
     * 
     * @return the last element in this list.
     * @throws NoSuchElementException if the list is empty
     */
    public E getLast() {
        if (size == 0)
            throw new NoSuchElementException();
        return last.e;
    }

    /**
     * Returns the size of this list.
     * 
     * @return the size of this list.
     */
    public int size() {
        return size;
    }

    /**
     * Returns an itertaor on this list.
     * 
     * @return an itertaor on this list.
     */
    public ListIterator<E> iterator() {
        return new ListIterator<E>(first.next);
    }

    /**
     * A textual representation of the elements of this list. Each element is
     * displayed in a separate line.
     */
    public String toString() {
        if (size == 0)
            return "()";
        StringBuilder str = new StringBuilder();
        ListIterator<E> itr = this.iterator();
        while (itr.hasNext()) {
            str.append(itr.next().toString() + nl);
        }
        return str.toString();
    }

    // The main method of this class can be used for testing the
    // LinkedList methods. Clients of the class will normally not use it.
    public static void main(String[] args) {
        // Creates a list of Integer objects, add some elements, and prints the list.
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(3);
        list.add(7);
        list.add(1, 1);
        list.addFirst(9);
        System.out.println(list);
        System.out.println(list.remove(3));
        System.out.println(list);
        testExceptions();
        // As you implement the LinkedList class methods, write your testing
        // code below. If you want, you can use private testing methods.

        // testExceptions();
    }

    // Exception testing method.
    public static void testExceptions() {
        // Creates a list of Integer objects
        LinkedList<Integer> list = new LinkedList<Integer>();

        // After you'll implement the getFirst() method, the statement below
        // should cause the program to crash.
        // To prevent it, wrap the method call with try/catch code.
        try {
            list.getFirst(); // Tries to get an element from the list, which is empty
        } catch (NoSuchElementException e) {
            System.out.println("the list is empty");

        }

        // Adds three elements to the list, and prints it
        list.add(3);
        list.add(7);
        list.add(9);
        System.out.println(list);

        // After you'll implement the add(e,index) method, the statement below
        // should cause the program to crash.
        // To prevent it, wrap the method call with try/catch code.
        try {
            list.add(8, -2); // Tries to insert an element in index -2.
        } catch (IllegalArgumentException e) {
            System.out.println("the index is illegal");
        }
        // After you'll implement the add(e,index) method, the statement below
        // should cause the program to crash.
        // To prevent it, wrap the method call with try/catch code.
        try {
            list.add(8, 10); // Tries to insert an element in index 10.
        } catch (IllegalArgumentException e) {
            System.out.println("the index is bigger then the size list");
        }
    }
}
