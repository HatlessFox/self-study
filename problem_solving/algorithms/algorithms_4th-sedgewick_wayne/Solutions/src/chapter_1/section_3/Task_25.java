package chapter_1.section_3;

import chapter_1.section_3.common.CommonLinkedList;
import chapter_1.section_3.common.CommonLinkedList.Node;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.3.25
 * 
 * Write a method insertAfter() that takes two linked-list Node arguments and inserts the second 
 * after the first on its list (and does nothing if either argument is null).
 */
public class Task_25 {


    private static Node<String> getNode(CommonLinkedList<String> ll, int i) {
        if (i <= 0)
            return null;
        Node<String> ptr = ll.first;
        while (ptr != null && 0 < (--i)) {
            ptr = ptr.next;
        }
        return ptr;
    }
    
    private static void insertAfter(Node<String> node, Node<String> insertee) {
        if (node == null || insertee == null)
            return;
        insertee.next = node.next;
        node.next = insertee;
    }
    
    private static void insertAfter(Node<String> node, String data, CommonLinkedList<String> ll) {
        if (node == null)
            return;
        Node<String> insertee = new Node<>(data);
        insertAfter(node, insertee);
        if (node == ll.last) {
            ll.last = insertee;
        }
    }
    
    public static void main(String[] args) {
        CommonLinkedList<String> ll = new CommonLinkedList<>();
        while (true) {
            StdOut.print("Enter op (a - add, i - insert after): ");
            switch (StdIn.readString()) {
            case "a": ll.add(StdIn.readString()); break;
            case "i": insertAfter(getNode(ll, StdIn.readInt()), StdIn.readString(), ll); break;
            default: StdOut.println("[Error] Unknown operation"); continue;
            }
            ll.forEach(s -> StdOut.print(s + " "));
            StdOut.println();
        }
    }
}
