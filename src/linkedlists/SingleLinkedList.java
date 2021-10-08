package linkedlists;

import java.util.NoSuchElementException;

public class SingleLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public void addBack(int data) {

    }

    public void addFront(int data) {

    }

    public void removeFront() {
        int old_data;

        //Empty List
        if (head == null) {

        }
    }

    public int get(int i) {

        if (i < 0 || i >= size)
            throw new IndexOutOfBoundsException();

        Node temp = head;
        for (int k = 0; k < i; k++)
            temp = head.next;

        return temp.data;
    }

    public int removeBack() {
        int old_data;

        //Empty List
        if (head == null)
            throw new NoSuchElementException();

        //One node
        if (head == tail) {
            old_data = head.data;
            head = tail = null;
        }

        Node temp = head;
        while (temp.next != tail) {
            temp = temp.next;
        }

        old_data = tail.data;
        temp.next = null;
        tail = temp;

        size--;
        return old_data;
    }

    public void reverse() {
        //Empty node
        if (head == null || head == tail)
            return;

        Node p1 = null;
        Node p2 = head;
        Node p3 = head.next;

        while (p3 != null) {
            p2.next = p1;
            p1 = p2;
            p2 = p3;
            p3 = p3.next;
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append("[");

        Node temp = head;
        while (temp != null) {
            sb.append(temp.data);
            temp = temp.next;
        }

        return sb.toString();
    }

    private static class Node {
        int data;
        Node next;

        Node(int d, Node n) {
            data = d;
            next = n;
        }
    }

    public static void main(String args[]) {
        SingleLinkedList list = new SingleLinkedList();

        for (int i = 0; i < 5; i++)
            list.addFront(i);

        System.out.println(list);

        for (int i = 0; i < 5; i++)
            System.out.println(list.get(i));

        System.out.println(list.get(6));

        /*
               list.reverse();

               System.out.println(list);


               for (int i=0; i<5; i++)
                  list.addBack(i);

               System.out.println(list);

               int x = list.removeBack();
               int y = list.removeFront();

               System.out.println(list);
        */
    }
}

