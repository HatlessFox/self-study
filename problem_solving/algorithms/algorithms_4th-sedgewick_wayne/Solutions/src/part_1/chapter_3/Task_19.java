package part_1.chapter_3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import part_1.chapter_3.common.CommonLinkedList;

/*
 * Task 1.3.19
 * 
 * Give a code fragment that removes the last node in a linked list whose first node is first.
 */
public class Task_19 {

    public static class LinkedListWithRemove extends CommonLinkedList<String> {
        public void removeLast() {
            Node<String> prelast = first;
            if (prelast == null) {
                // size is 0
                return;
            } else if (prelast.next == null) {
                // size is 1
                first = last = null;
            } else {
                // size is 1<
                while (prelast.next.next != null) {
                    prelast = prelast.next;
                }
                prelast.next = null;
                last = prelast;
            }
        }
    }
    
    public static void main(String[] args) {
        LinkedListWithRemove ll = new LinkedListWithRemove();
        while (true) {
            StdOut.print("Enter op (a - add, r - rm last): ");
            switch (StdIn.readString()) {
            case "a": ll.add(StdIn.readString()); break;
            case "r": ll.removeLast(); break;
            }
            ll.forEach(s -> { StdOut.print(s + " ");});
            StdOut.println();
       
        }
    }
}
