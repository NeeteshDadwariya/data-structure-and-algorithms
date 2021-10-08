package linkedlists;

import java.util.Iterator;

/**
 * LinkedList class implements a doubly-linked list.
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType> {
    /**
     * Construct an empty LinkedList.
     */
    public MyLinkedList() {
        doClear();
    }

    private void clear() {
        doClear();
    }

    /**
     * Change the size of this collection to zero.
     */
    public void doClear() {
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null, beginMarker, null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    /**
     * Returns the number of items in this collection.
     *
     * @return the number of items in this collection.
     */
    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Adds an item to this collection, at the end.
     *
     * @param x any object.
     * @return true.
     */
    public boolean add(AnyType x) {
        add(size(), x);
        return true;
    }

    /**
     * Adds an item to this collection, at specified position.
     * Items at or after that position are slid one position higher.
     *
     * @param x   any object.
     * @param idx position to add at.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    public void add(int idx, AnyType x) {
        addBefore(getNode(idx, 0, size()), x);
    }

    /**
     * Adds an item to this collection, at specified position p.
     * Items at or after that position are slid one position higher.
     *
     * @param p Node to add before.
     * @param x any object.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    private void addBefore(Node<AnyType> p, AnyType x) {
        Node<AnyType> newNode = new Node<>(x, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }


    /**
     * Returns the item at position idx.
     *
     * @param idx the index to search in.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType get(int idx) {
        return getNode(idx).data;
    }

    /**
     * Changes the item at position idx.
     *
     * @param idx    the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType set(int idx, AnyType newVal) {
        Node<AnyType> p = getNode(idx);
        AnyType oldVal = p.data;

        p.data = newVal;
        return oldVal;
    }

    /**
     * Gets the Node at position idx, which must range from 0 to size( ) - 1.
     *
     * @param idx index to search at.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
     */
    private Node<AnyType> getNode(int idx) {
        return getNode(idx, 0, size() - 1);
    }

    /**
     * Gets the Node at position idx, which must range from lower to upper.
     *
     * @param idx   index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
     */
    private Node<AnyType> getNode(int idx, int lower, int upper) {
        Node<AnyType> p;

        if (idx < lower || idx > upper)
            throw new IndexOutOfBoundsException("getNode index: " + idx + "; size: " + size());

        if (idx < size() / 2) {
            p = beginMarker.next;
            for (int i = 0; i < idx; i++)
                p = p.next;
        } else {
            p = endMarker;
            for (int i = size(); i > idx; i--)
                p = p.prev;
        }

        return p;
    }

    /**
     * Removes an item from this collection.
     *
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove(int idx) {
        return remove(getNode(idx));
    }

    /**
     * Removes the object contained in Node p.
     *
     * @param p the Node containing the object.
     * @return the item was removed from the collection.
     */
    private AnyType remove(Node<AnyType> p) {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;

        return p.data;
    }

    /**
     * receives two index positions as parameters, and swaps the nodes at
     * these positions by changing the links, provided both positions are
     * within the current size
     *
     * @param source - The source index
     * @param dest   - The destination index
     */
    public void swap(int source, int dest) {

        // Bounds check for input source and destination
        if ((source < 0 || source >= size()) || (dest < 0 || dest >= size())) {
            throw new IndexOutOfBoundsException("Provided source and dest combination is out of range.");
        }

        // If only one element in the list OR source and dest are same, then do nothing.
        if (size() == 1 || (source == dest)) return;

        // First we will retrieve the source and dest nodes using getNode() function.
        Node sourceNode = getNode(source), destNode = getNode(dest);

        // Now, we'll keep track of the before and after nodes of the source and dest, in order to be able to
        // modify the links later.
        Node beforeSourceNode = sourceNode.prev;
        Node afterSourceNode = sourceNode.next;
        Node beforeDestNode = destNode.prev;
        Node afterDestNode = destNode.next;

        // Modifying destNode links
        destNode.prev = beforeSourceNode;
        destNode.next = afterSourceNode;

        // Modifying sourceNode links
        sourceNode.prev = beforeDestNode;
        sourceNode.next = afterDestNode;

        // Modifying links for before and after of destNode
        beforeSourceNode.next = destNode;
        afterSourceNode.prev = destNode;

        // Modifying links for before and after of sourceNode
        beforeDestNode.next = sourceNode;
        afterDestNode.prev = sourceNode;

        //modCount is incremented as to avoid re-modification from another iterator.
        modCount++;
    }

    /**
     * receives an integer (positive or negative) and shifts the list this
     * many positions forward (if positive) or backward (if negative).
     *
     * @param degree - The degree of shift expected - Forward if +ve, backwards if -ve
     */
    public void shift(int degree) {
        // Taking mod of degree as, rotation can only be performed with in the size.
        // This will also take account of -ve values, as mod of those would be positive now.
        degree = Math.floorMod(degree, size());

        // Nothing to be changed
        if (degree == 0 || size() == 0 || size() == 1)
            return;

        // First we will create a cycle inside the list to provide the rotation.
        beginMarker.next.prev = endMarker.prev;
        endMarker.prev.next = beginMarker.next;

        // Now, we can go to the desired degree position, and disconnect the links
        Node newHead = getNode(degree);
        Node newTail = newHead.prev;
        newHead.prev = beginMarker;
        beginMarker.next = newHead;
        newTail.next = endMarker;
        endMarker.prev = newTail;

        // modCount is incremented as to avoid re-modification from another iterator.
        modCount++;
    }

    /**
     * receives an index position and number of elements as parameters, and
     * removes elements beginning at the index position for the number of
     * elements specified, provided the index position is within the size
     * and together with the number of elements does not exceed the size
     *
     * @param pos       - The specified position from where removal should occur
     * @param noOfElems - Specifies the number of elements to be removed.
     */
    public void erase(int pos, int noOfElems) {
        if (pos < 0 || (pos + noOfElems) > size())
            throw new IndexOutOfBoundsException("Combination of pos and noOfElems going beyond size.");

        // Nothing to be done.
        if (noOfElems == 0)
            return;

        Node removalStartNode = getNode(pos);
        Node removalEndNode = getNode(pos + noOfElems - 1);

        // Shifting the links before removalStartNode
        removalStartNode.prev.next = removalEndNode.next;
        removalEndNode.next.prev = removalStartNode.prev;

        // Changing size
        theSize = size() - noOfElems;

        // modCount is incremented as to avoid re-modification from another iterator.
        modCount++;
    }

    /**
     * receives another MyLinkedList and an index position as parameters, and
     * copies the list from the passed list into the list at the specified
     * position, provided the index position does not exceed the size.
     *
     * @param lst2  - The list from which data is to be copied
     * @param index - The destination insertion index in original list
     */
    public void insertList(MyLinkedList<AnyType> lst2, int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException("Provided position is out of bounds for original list");

        // Nothing to be done.
        if (lst2 == null || lst2.isEmpty())
            return;

        Node insertionNode = getNode(index);
        Iterator<AnyType> iterator = lst2.iterator();
        while (iterator.hasNext()) {
            AnyType data = iterator.next();
            Node<AnyType> newNode = new Node(data, insertionNode.prev, insertionNode);
            insertionNode.prev.next = newNode;
            insertionNode.prev = newNode;
        }

        // modCount is incremented as to avoid re-modification from another iterator.
        modCount++;
    }

    /**
     * Returns a String representation of this collection.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");

        for (AnyType x : this)
            sb.append(x + " ");
        sb.append("]");

        return new String(sb);
    }

    /**
     * Obtains an Iterator object used to traverse the collection.
     *
     * @return an iterator positioned prior to the first element.
     */
    public Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }

    /**
     * This is the implementation of the LinkedListIterator.
     * It maintains a notion of a current position and of
     * course the implicit reference to the MyLinkedList.
     */
    private class LinkedListIterator implements Iterator<AnyType> {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        public boolean hasNext() {
            return current != endMarker;
        }

        public AnyType next() {
            if (modCount != expectedModCount)
                throw new java.util.ConcurrentModificationException();
            if (!hasNext())
                throw new java.util.NoSuchElementException();

            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        public void remove() {
            if (modCount != expectedModCount)
                throw new java.util.ConcurrentModificationException();
            if (!okToRemove)
                throw new IllegalStateException();

            MyLinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }
    }

    /**
     * This is the doubly-linked list node.
     */
    private static class Node<AnyType> {
        public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
            data = d;
            prev = p;
            next = n;
        }

        public AnyType data;
        public Node<AnyType> prev;
        public Node<AnyType> next;
    }

    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;
}

class TestLinkedList {
    public static void main(String[] args) {
        MyLinkedList<Integer> lst = new MyLinkedList<>();

        for (int i = 0; i < 10; i++)
            lst.add(i);
        for (int i = 20; i < 30; i++)
            lst.add(0, i);

        System.out.println(lst);

        //Testing swap
        testSwap(lst, 2, 10);
        testSwap(lst, 0, lst.size() - 1);
        //testSwap(lst, -1, lst.size() - 1);        //Throws IndexOutOfBoundsException
        //testSwap(lst, 0, lst.size());             //Throws IndexOutOfBoundsException

        //Testing shift
        testShift(lst, 2);
        testShift(lst, 0);
        testShift(lst, -3);

        //Testing erase
        testErase(lst, 2, 3);
        testErase(lst, 0, 1);
        testErase(lst, lst.size() - 1, 1);
        //testErase(lst, lst.size() - 1, 2);        //Throws IndexOutOfBoundsException
        //testErase(lst, 0, lst.size());            //Empty list
        //testErase(lst, 0, 1);                     //Throws IndexOutOfBoundsException

        //Testing insertList
        MyLinkedList<Integer> lst2 = new MyLinkedList<>();
        //Adding elements to the lst2
        for (int i = 50; i <= 60; i++) lst2.add(i);
        testInsertList(lst, lst2, 3);
    }

    public static void testSwap(MyLinkedList<Integer> lst, int source, int dest) {
        lst.swap(source, dest);
        System.out.println(String.format("After swapping index %s with %s:\n%s", source, dest, lst));
    }

    public static void testShift(MyLinkedList<Integer> lst, int degree) {
        lst.shift(degree);
        System.out.println(String.format("After shifting with degree %s:\n%s", degree, lst));
    }

    public static void testErase(MyLinkedList<Integer> lst, int pos, int noOfElems) {
        lst.erase(pos, noOfElems);
        System.out.println(String.format("After erasing with pos=%s, noOfElems=%s:\n%s", pos, noOfElems, lst));
    }

    public static void testInsertList(MyLinkedList<Integer> lst, MyLinkedList<Integer> lst2, int index) {
        lst.insertList(lst2, index);
        System.out.println(String.format("After inserting with 2nd list at Index %s: \n%s", index, lst));
    }
}