package part_1.chapter_3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import part_1.chapter_3.common.CommonLinkedList;

/*
 * Task 1.3.20
 * 
 * Write a method delete() that takes an int argument k and deletes the kth element in
 * a linked list, if it exists.
 * 
 */
public class Task_20 {

    public static class LinkedListWithRandomDelete extends CommonLinkedList<String>{
        public void delete(int k) {
            Node<String> previctim = first;
            if (previctim == null) {
                // size is 0
                return;
            } else if (k == 1) {
                first = first.next;
                if (first == null) { last = null; }
            } else {
                int i = 1;
                while (i++ + 1 != k && previctim != null) {
                    previctim = previctim.next;
                }
                if (previctim == null || previctim.next == null) {
                    // size < k
                    return;
                }
                
                if (previctim.next == last) {
                    last = previctim;
                }
                previctim.next = previctim.next.next;
            }
        }
    }
    
    public static void main(String[] args) {
        LinkedListWithRandomDelete ll = new LinkedListWithRandomDelete();
        while (true) {
            StdOut.print("Enter op (a - add, r - delete kth): ");
            switch (StdIn.readString()) {
            case "a": ll.add(StdIn.readString()); break;
            case "r": ll.delete(StdIn.readInt()); break;
            }
            ll.forEach(s -> { StdOut.print(s + " ");});
            StdOut.println();
        }
    }
}
