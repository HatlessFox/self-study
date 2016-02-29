package part_1.chapter_3;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import part_1.chapter_3.Task_31.DoubleLinkedList;
import part_1.chapter_3.Task_31.DoubleLinkedList.DoubleNode;
import utils.CliUtils;

/*
 * Task 1.3.33: Deque
 * 
 * A double-ended queue or deque (pronounced "deck") is like a stack or a queue but supports adding 
 * and removing items at both ends. A deque stores a collection of items and supports the following 
 * API: <skipped, page 167>.
 * Write a class Deque that uses a double-linked list to implement this API and a class
 * ResizingArrayDeque that uses a resizing array.
 */
public class Task_33 {

    public static interface Deque<T> extends Iterable<T> {
        default public boolean isEmpty() { return size() == 0; }
        int size();
        void pushLeft(T item);
        void pushRight(T item);
        T popLeft();
        T popRight();
    }
    
    public static class DoubleLinkedListDeque<T> implements Deque<T> {
        private DoubleLinkedList<T> holder = new DoubleLinkedList<>();
        private int size = 0;
        
        @Override
        public int size() { return size; }

        @Override
        public void pushLeft(T item) {
            holder.insertStart(item);
            size++;
        }

        @Override
        public void pushRight(T item) {
            holder.insertEnd(item);
            size++;
        }

        @Override
        public T popLeft() {
            T data = holder.removeStart();
            if (data != null) {
                size--;
            }
            return data;
        }

        @Override
        public T popRight() {
            T data = holder.removeEnd();
            if (data != null) {
                size--;
            }
            return data;
        }
        

        @Override
        public Iterator<T> iterator() { return new DequeIterator(); }

        private class DequeIterator implements Iterator<T> {
            private DoubleNode<T> ptr = holder.head;
            @Override
            public boolean hasNext() { return ptr != null; }

            @Override
            public T next() {
                T data = ptr.payload;
                ptr = ptr.next;
                return data;
            }
        }
    }
    
    public static class ResizingArrayDeque<T> implements Deque<T> {

        private static class ExtendableArray<T> implements Iterable<T>{
            @SuppressWarnings("unchecked")
            private T[] holder = (T[])new Object[1];
            private int fst, sz;
            
            private void resize(int new_size) {
                if (holder.length / 2 <= new_size && new_size <= holder.length)
                    return;

                int holder_size = (int)(holder.length * (holder.length < new_size ? 2.0 : 0.25));
                holder_size = Math.max(new_size, holder_size);
                
                @SuppressWarnings("unchecked")
                T[] new_holder = (T[])new Object[holder_size];
                
                int suff_len = Math.min(sz, holder.length - fst);
                System.arraycopy(holder, fst, new_holder, 0, suff_len);
                System.arraycopy(holder, 0, new_holder, suff_len, sz - suff_len);
                
                holder = new_holder;
                fst = 0;
            }
            
            private int getEffectiveIndex(int i) {
                return (fst + i + holder.length) % holder.length;
            }
            
            public void prepend(T item) {
                resize(sz + 1);
                
                holder[getEffectiveIndex(-1)] = item;
                fst = getEffectiveIndex(-1);
                sz++;
            }
            
            public void append(T item) {
                resize(sz + 1);
                
                holder[getEffectiveIndex(sz)] = item;
                sz++;
            }
            
            public T popHead() {
                if (sz == 0)
                    return null;
                
                int eff_i = getEffectiveIndex(0);
                T data = holder[eff_i];
                
                fst = getEffectiveIndex(1);
                sz--;
                resize(sz);
                
                return data;
            }
            
            public T popTail() {
                if (sz == 0)
                    return null;
                
                int eff_i = getEffectiveIndex(sz-1);
                T data = holder[eff_i];
                
                sz--;
                resize(sz);
                
                return data;
            }
            
            @Override
            public Iterator<T> iterator() { return new ExtendableArrayIterator(); }
            
            private class ExtendableArrayIterator implements Iterator<T> {
                int i = 0;
                
                @Override
                public boolean hasNext() { return i < sz; }

                @Override
                public T next() {
                    return holder[getEffectiveIndex(i++)];
                }
                
            }
        }
        
        private ExtendableArray<T> data = new ExtendableArray<>();
        
        @Override
        public Iterator<T> iterator() { return data.iterator(); }

        @Override
        public int size() { return data.sz; }
        @Override
        public void pushLeft(T item) { data.prepend(item); }
        @Override
        public void pushRight(T item) { data.append(item); }
        @Override
        public T popLeft() { return data.popHead(); }
        @Override
        public T popRight() { return data.popTail(); }
    }
    
    public static void main(String[] args) {
        Deque<String> dll_deque = new DoubleLinkedListDeque<>();
        Deque<String> re_deque = new ResizingArrayDeque<>();
        while (true) {
            StdOut.print("Enter op (phl - push left; phr - push right; ppl - pop left; " +
                         "ppr - pop right): ");
            switch (StdIn.readString()) {
            case "phl": {
                String e = StdIn.readString();
                dll_deque.pushLeft(e);
                re_deque.pushLeft(e);
                break;
            }
            case "phr": {
                String e = StdIn.readString();
                dll_deque.pushRight(e);
                re_deque.pushRight(e);
                break;
            }
            case "ppl":
                StdOut.printf("DLL: %s; RE: %s\n", dll_deque.popLeft(), re_deque.popLeft());
                break;
            case "ppr":
                StdOut.printf("DLL: %s; RE: %s\n", dll_deque.popRight(), re_deque.popRight());
                break;
            }
            StdOut.printf("DLL Deck: %s\n", CliUtils.iterableToString(dll_deque));
            StdOut.printf("RE  Deck: %s\n", CliUtils.iterableToString(re_deque));
        }
    }
}
