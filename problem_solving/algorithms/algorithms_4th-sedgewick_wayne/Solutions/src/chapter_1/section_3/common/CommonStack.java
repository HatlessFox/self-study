package chapter_1.section_3.common;

import java.util.Iterator;

/*
 * Basic stack implementation designed to be completely extendible to save typing (page 149). 
 */
public class CommonStack<Item> implements Iterable<Item>{
    public Node first;
    public int sz = 0;
    
    public class Node {
        public Item item;
        public Node next;
    }
    
    public boolean isEmpty() { return first == null; }
    public int size() { return sz; }
    
    public void push(Item item) {
        Node new_node = new Node();
        new_node.item = item;
        
        // link the new node
        new_node.next = first;
        first = new_node;
        sz++;
    }
    
    public Item pop() {
        Item item = first.item;
        first = first.next;
        sz--;
        return item;
    }
    
    @Override
    public Iterator<Item> iterator() {
        return new StackIterator();
    }
    
    public class StackIterator implements Iterator<Item> {
        private Node current = first;
        
        @Override
        public boolean hasNext() {
            return current != null;
        }
        
        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
