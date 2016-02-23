package part_1.chapter_3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import part_1.chapter_3.common.CommonLinkedList;
import part_1.chapter_3.common.CommonLinkedList.Node;

/*
 * Task 1.3.26
 * 
 * Write a method remove() that takes a linked list and a string key as arguments and removes all of
 * the nodes in the list that have key as its item field.
 */
public class Task_26 {
    
    private static void filter(CommonLinkedList<String> ll, String key) {
        // clean up prefix
        while (ll.first != null && ll.first.item.equals(key)) {
            ll.first = ll.first.next;
        }
        
        if (ll.first == null || ll.first.next == null) {
            // size is in {0, 1}, first != key
            ll.last = ll.first;
            return;
        }
        
        // filter tail
        Node<String> ptr = ll.first;
        while (ptr.next != null) {
            if (ptr.next.item.equals(key)) {
                ptr.next = ptr.next.next;
            } else {
                ptr = ptr.next;
            }
        }
        ll.last = ptr;
    }
    
    public static void main(String[] args) {
        CommonLinkedList<String> ll = new CommonLinkedList<>();
        while (true) {
            StdOut.print("Enter op (a - add, f - filter): ");
            switch (StdIn.readString()) {
            case "a": ll.add(StdIn.readString()); break;
            case "f": filter(ll, StdIn.readString()); break;
            default: StdOut.println("[Error] Unknown operation"); continue;
            }
            ll.forEach(s -> StdOut.print(s + " "));
            StdOut.println();
        }
    }

}
