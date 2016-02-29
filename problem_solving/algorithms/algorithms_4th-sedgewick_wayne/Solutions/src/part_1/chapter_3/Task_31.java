package part_1.chapter_3;

import java.util.function.Function;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.3.30
 * 
 * Implement a nested class DoubleNode for building double-linked lists, where each node contains 
 * a reference to the item preceding it and the item following it in the list (null if there is 
 * no such item). Then implement static methods for the following tasks: insert at the beginning, 
 * insert at the end, remove from the beginning, remove from the end, insert before a given node, 
 * insert after a given node, and remove a given node.
 */
public class Task_31 {

    public static class DoubleLinkedList<T> {
        
        public DoubleNode<T> head, tail;
        
        public static class DoubleNode<T> {
            T payload;
            DoubleNode<T> next, prev;
        
            public DoubleNode(T data) { payload = data; }
        }
        
        public boolean isEmpty() { return head == null; }

        public void insertStart(T data) {
            insertBefore(head, data);
        }
        
        public void insertEnd(T data) {
            insertAfter(tail, data);
        }
        
        public T removeStart() {
            return removeNode(head);
        }
        public T removeEnd() {
            return removeNode(tail);
        }
        public void insertBefore(DoubleNode<T> node, T data) {
            DoubleNode<T> new_node = new DoubleNode<T>(data);
            insertBefore(new_node, node);
        }
        
        public void insertAfter(DoubleNode<T> node, T data) {
            DoubleNode<T> new_node = new DoubleNode<T>(data);
            insertAfter(new_node, node);
        }
        public T removeNode(DoubleNode<T> node) {
            T data = null;
            
            if (head == null)
                return data;
            if (head == tail) {
                data = head.payload;
                head = tail = null;
                return data;
            }
            
            if (node == head) {
                node.next.prev = node.prev;
                head = node.next;
            } else if (node == tail) {
                node.prev.next = node.next;
                tail = node.prev;
            } else { // node is inside list
                node.next.prev = node.prev;
                node.prev.next = node.next;
            }
            node.prev = node.next = null;
            return node.payload;
        }
        
        private void insertBefore(DoubleNode<T> new_node, DoubleNode<T> base_node) {
            if (isEmpty()) {
                head = tail = new_node;
                return;
            }
            
            new_node.next = base_node;
            if (base_node == head) {
                head = new_node;
            } else {
                new_node.prev = base_node.prev;
                new_node.prev.next = new_node;
            }
            base_node.prev = new_node;
        }
        
        private void insertAfter(DoubleNode<T> new_node, DoubleNode<T> base_node) {
            if (isEmpty()) {
                head = tail = new_node;
                return;
            }
            
            new_node.prev = base_node;
            if (base_node == tail) {
                tail = new_node;
            } else {
                new_node.next = base_node.next;
                new_node.prev.next = new_node;
            }
            base_node.next = new_node;
        }
        
        private String toStringBase(DoubleNode<T> ptr,
                Function<DoubleNode<T>, DoubleNode<T>> next) {
            StringBuilder sb = new StringBuilder();
            
            while (ptr != null) {
                sb.append(ptr.payload);
                sb.append(" ");
                ptr = next.apply(ptr);
            }
            
            return sb.toString().trim();
        }
        
        @Override
        public String toString() {
            return toStringBase(head, (n -> n.next));
        }
        
        public String toStringReverse() {
            return toStringBase(tail, (n -> n.prev));
        }
        
        public DoubleNode<T> nodeByIndex(int i) {
            if (i <= 0)
                return null;
            
            DoubleNode<T> ptr = head;
            while (0 < --i && ptr != null) {
                ptr = ptr.next;
            }
            return ptr;
        }
    }
    
    public static void main(String[] args) {
        DoubleLinkedList<String> dl = new DoubleLinkedList<>();
        while (true) {
            StdOut.print("Enter op (is - insert start, ie - insert end, " +
                    "ib - insert before <i, data>, ia - insert after <i, data>,\n"+
                    "rs - remove start, re - remove end, rm - remove <i>, rv - reverse) > ");
            switch (StdIn.readString()) {
            case "is": dl.insertStart(StdIn.readString()); break;
            case "ie": dl.insertEnd(StdIn.readString()); break;
            case "ib": dl.insertBefore(dl.nodeByIndex(StdIn.readInt()), StdIn.readString()); break;
            case "ia": dl.insertAfter(dl.nodeByIndex(StdIn.readInt()), StdIn.readString()); break;
            case "rs": dl.removeStart(); break;
            case "re": dl.removeEnd(); break;
            case "rm": dl.removeNode(dl.nodeByIndex(StdIn.readInt())); break;
            case "rv": StdOut.println(dl.toStringReverse()); continue;
            }
            StdOut.println(dl);
        }
    }
}
