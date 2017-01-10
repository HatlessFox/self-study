package chapter_1.section_3;

import chapter_1.section_3.common.CommonLinkedList;
import chapter_1.section_3.common.CommonLinkedList.Node;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.3.24
 * 
 * Write a method removeAfter() that takes a linked-list Node as argument and removes the node 
 * following the given one (and does nothing if the argument or the next field in the argument node
 * is null)
 */
public class Task_24 {

    private static Node<String> getNode(CommonLinkedList<String> ll, int i) {
        if (i <= 0)
            return null;
        Node<String> ptr = ll.first;
        while (ptr != null && 0 < (--i)) {
            ptr = ptr.next;
        }
        return ptr;
    }
    
    private static void removeAfter(Node<String> node, CommonLinkedList<String> ll) {
        if (node == null || node.next == null)
            return;
        if (node.next == ll.last) {
            ll.last = node;
        }
        node.next = node.next.next;
    }
    
    public static void main(String[] args) {
        CommonLinkedList<String> ll = new CommonLinkedList<>();
        while (true) {
            StdOut.print("Enter op (a - add, r - rm after): ");
            switch (StdIn.readString()) {
            case "a": ll.add(StdIn.readString()); break;
            case "r": removeAfter(getNode(ll, StdIn.readInt()), ll); break;
            }
            ll.forEach(s -> StdOut.print(s + " "));
            StdOut.println();
        }
    }
}
