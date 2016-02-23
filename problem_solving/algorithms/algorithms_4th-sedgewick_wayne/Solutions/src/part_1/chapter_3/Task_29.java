package part_1.chapter_3;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.3.29
 * 
 * Write a Queue implementation that uses a circular linked list, which is the same as a linked list
 * except that no links are null and the value of lask.next if first whenever the list is not empty.
 * Keep only one Node instance variable (last).
 */
public class Task_29 {

    private static class CircularListQueue<T> implements Iterable<T>{
        private Node<T> last;
        
        private static class Node<T> {
            T payload;
            Node<T> next;
            
            public Node(T data) {
                payload = data;
            }
        }
        
        public boolean isEmpty() { return last == null; }
        
        public void enqueue(T data) {
            Node<T> holder = new Node<>(data);
            if (isEmpty()) {
                last = holder;
                last.next = last;
            } else {
                holder.next = last.next;
                last.next = holder;
            }
            
            last = holder;
        }
        
        public T dequeue() {
            if (isEmpty())
                return null;
            
            T item = last.next.payload;
            if (last == last.next) {
                // size is 1
                last = null;
            } else {
                last.next = last.next.next;
            }
            return item;
        }

        @Override
        public Iterator<T> iterator() { return new CircularListIterator(); }
        
        private class CircularListIterator implements Iterator<T>{
            private final Node<T> FIRST = last == null ? null : last.next;
            private Node<T> ptr = FIRST;
            private boolean fst_was_visited = false;
            @Override
            public boolean hasNext() {
                return !(ptr == null || (ptr == FIRST && fst_was_visited));
            }

            @Override
            public T next() {
                fst_was_visited |= ptr == FIRST;
                T data = ptr.payload;
                ptr = ptr.next;
                return data;
            }
            
        }
    }

    public static void main(String[] args) {
        CircularListQueue<String> clq = new CircularListQueue<>();
        while (true) {
            StdOut.print("Enter op (e - enqueue, d - dequeue): ");
            switch (StdIn.readString()) {
            case "e": clq.enqueue(StdIn.readString()); break;
            case "d": StdOut.println(clq.dequeue()); break;
            default: StdOut.println("[Error] Unknown operation");
            }
            
            clq.forEach(s -> StdOut.print(s + " "));
            StdOut.println();
        }
    }
}
