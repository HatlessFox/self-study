package chapter_1.section_3;

import chapter_1.section_3.common.CommonLinkedList;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.3.32: Steque
 * 
 * A stack-ended queue or steque is a data type that supports push, pop and enqueue.
 * Articulate an API for this ADT. Develop a linked-list-based implementation.
 */
public class Task_32 {

    public static class Steque<T> extends CommonLinkedList<T>{
        public void push(T data) {
            Node<T> new_node = new Node<T>(data);
            if (isEmpty()) {
                first = last = new_node;
            } else {
                new_node.next = first;
                first = new_node;
            }
        }
        
        public T pop() {
            if (isEmpty())
                return null;
            
            T data = first.item;
            if (first == last) {
                first = last = null;
            } else {
                first = first.next;
            }
            return data;
        }
        
        public void enqueue(T data) {
            add(data);
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            forEach(e -> sb.append(e + " "));
            return sb.toString().trim();
        }
    }
    
    public static void main(String[] args) {
        Steque<String> stq = new Steque<>();
        while (true) {
            StdOut.print("Enter op (ph - push, pp - pop, en - enqueue): ");
            switch (StdIn.readString()) {
            case "ph": stq.push(StdIn.readString()); break;
            case "pp": StdOut.println(stq.pop()); break;
            case "en": stq.enqueue(StdIn.readString()); break;
            }
            StdOut.println(stq);
        }
    }
}
