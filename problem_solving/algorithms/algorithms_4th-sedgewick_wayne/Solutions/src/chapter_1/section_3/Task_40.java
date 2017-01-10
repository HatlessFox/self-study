package chapter_1.section_3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.3.40: Move-to-front.
 * 
 * Read in a sequence of characters from standard input and maintain the characters in a linked list
 * with no duplicates. When you read in a previously unseen character, insert it at the front of the
 * list. When you read in a duplicate character, delete it from the list and reinsert it at the 
 * beginning. Name your program MoveToFront: it implements the well-known move-to-front strategy,
 * which is useful for caching, data compression, and many other applications where items that have
 * been recently accessed are more likely to be reaccessed.
 */
public class Task_40 {

    public static class MoveToFrontLinkedList<T>{
        private class Node {
            private T payload;
            private Node next;
            public Node(T data) { payload = data; }
        }
        
        private Node first;
        
        public boolean isEmpty() { return first == null; }
        
        public void add(T data) {
            Node new_node = new Node(data);
            new_node.next = first;
            first = new_node;
            
            removeByData(data);
        }
        
        private void removeByData(T data) {
            Node ptr = first;
            while (ptr.next != null) {
                if (data.equals(ptr.next.payload)) {
                    ptr.next = ptr.next.next;
                    return;
                }
                ptr = ptr.next;
            }
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node ptr = first;
            while (ptr != null) {
                sb.append(ptr.payload).append(" ");
                ptr = ptr.next;
            }
            return sb.toString().trim();
        }
    }
    
    public static void main(String[] args) {
        MoveToFrontLinkedList<String> container = new MoveToFrontLinkedList<>();
        while (true) {
            StdOut.print("Enter element to be added: ");
            container.add(StdIn.readString());
            StdOut.println(container);
        }
    }
}
