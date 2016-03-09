package part_1.chapter_3;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.3.39: Circular Buffer.
 * 
 * A ring buffer, or circular queue, is a FIFO data structure of a fixed size N. It is useful for
 * transferring data between asynchronous processes or for storing log files. When the buffer is 
 * empty, the consumer waits until data is deposited; when the buffer is full, the producer waits to
 * deposit data. Develop an API for a RingBuffer and an implementation that uses an array
 * representation (with circular wrap-around).
 */
public class Task_39 {

    public static class RingBuffer<T> implements Iterable<T>{
        private T[] holder;
        private int fst, sz;
        
        private T getElement(int i) { return holder[(fst + i) % holder.length]; }
        private void setElement(int i, T e) { holder[(fst + i) % holder.length] = e; }
        
        @SuppressWarnings("unchecked")
        public RingBuffer(int n) {
            holder = ((T[]) new Object[n]);
            fst = sz = 0;
        }
        
        public boolean isEmpty() { return sz == 0; }
        public boolean isFull() { return sz == holder.length; }
        
        public void enqueue(T data) {
            if (isFull())
                return;
            setElement(sz++, data);
        }
        
        public T dequeue() {
            if (isEmpty())
                return null;
            T data = getElement(0);
            setElement(0, null);
            fst = 1 + (fst == holder.length ? 0 : fst);
            sz--;
            return data;
        }

        @Override
        public Iterator<T> iterator() {
            return new RingBufferIterator();
        }
        
        private class RingBufferIterator implements Iterator<T> {
            private int i = 0;
            @Override
            public boolean hasNext() { return i != sz; }
            @Override
            public T next() { return getElement(i++); }
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("E:%s; F:%s. Content: ",
                    isEmpty() ? "+" : "-", isFull() ? "+" : "-"));
            forEach(e -> sb.append(e).append(" "));
            return sb.toString().trim();
        }
    }
    
    public static void main(String[] args) {
        StdOut.print("Enter ring buffer size: ");
        RingBuffer<String> rb = new RingBuffer<>(StdIn.readInt());
        
        while (true) {
            StdOut.print("Enter operation (e <e> - enqueue <e>, d - dequeue): ");
            switch (StdIn.readString()) {
            case "e": rb.enqueue(StdIn.readString()); break;
            case "d": StdOut.println(rb.dequeue()); break;
            }
            StdOut.println(rb);
        }
    }
}
