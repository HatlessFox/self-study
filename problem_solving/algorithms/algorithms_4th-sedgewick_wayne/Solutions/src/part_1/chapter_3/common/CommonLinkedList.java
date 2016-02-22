package part_1.chapter_3.common;

import java.util.Iterator;

public class CommonLinkedList<T> implements Iterable<T>{
    protected static class Node<T> {
        public T item;
        public Node<T> next;
        
        public Node(T data) {
            item = data;
        }
    }
    
    public Node<T> first;
    public Node<T> last;
    
    public boolean isEmpty() { return first == null; }
    
    public void add(T data) {
        Node<T> new_node = new Node<>(data);
        if (last != null) {
            last.next = new_node;
        }
        last = new_node;
        
        if (first == null) {
            first = last;
        }
    }

    @Override
    public Iterator<T> iterator() { return new CommonLinkedListIterator(); }
    
    public class CommonLinkedListIterator implements Iterator<T> {
        Node<T> ptr = first;
        @Override
        public boolean hasNext() { return ptr != null; }

        @Override
        public T next() {
           T item = ptr.item;
           ptr = ptr.next;
            return item;
        }
        
    }
}
