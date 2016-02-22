package part_1.chapter_3;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.3.14
 * 
 * Develop a class ResizingArrayQueueOfStrings that implements the queue abstraction with a fixed-
 * size array, and then extend your implementation to use array resizing to remove the size
 * restriction.
 */
public class Task_14 {

    public interface Queue<Item> extends Iterable<Item> {
        public boolean isEmpty();
        public int size();
        
        public Item peek();
        public void enqueue(Item item);
        public Item dequeue();
    }
    
    public static class ResizingArrayQueueOfStrings implements Queue<String>{
        private String[] data;
        private int start_i;
        private int sz;
        
        private String getElement(int i) { return data[(start_i + i) % data.length]; }
        private void setElement(int i, String new_e) { data[(start_i + i) % data.length] = new_e; }
        
        private void moveElements(String[] dst) {
            int suff_sz = data.length - start_i;
            System.arraycopy(data, start_i, dst, 0, suff_sz);
            System.arraycopy(data, 0, dst, suff_sz, sz - suff_sz);
            
            start_i = 0;
            data = dst;
        }
        
        private void resizeStorage(int new_sz) {
            if (data.length < new_sz) { // grow
                moveElements(new String[Math.max(data.length*2, new_sz)]);
            } else if (new_sz < data.length / 4) { //shrink
                moveElements(new String[data.length / 2]);
            }
        }
        
        public ResizingArrayQueueOfStrings(int init_storage_sz) {
            data = new String[init_storage_sz];
        }
        
        @Override public boolean isEmpty() { return sz == 0; }
        @Override public int size() { return sz; }
        public int storageSize() { return data.length; }

        @Override
        public String peek() {
            if (isEmpty()) return null;
            return getElement(0);
        }

        @Override
        public void enqueue(String item) {
            resizeStorage(sz + 1);
            setElement(sz++, item);
        }

        @Override
        public String dequeue() {
            if (isEmpty()) return null;
            
            String item = getElement(0);
            setElement(0, null); // prevent loitering
            
            start_i++; // internals smell: move "array" right
            sz--;
            resizeStorage(sz);
            
            return item;
        }
        
        @Override
        public Iterator<String> iterator() { return new ResizingArrayQueueOfStringsIterator(); }
        
        public class ResizingArrayQueueOfStringsIterator implements Iterator<String> {
            private int i = 0;
            
            @Override
            public boolean hasNext() { return i != ResizingArrayQueueOfStrings.this.size(); }

            @Override
            public String next() { return ResizingArrayQueueOfStrings.this.getElement(i++); }
        }
    }
    
    public static void main(String[] args) {
        // CLI
        StdOut.print("Enter initial storage size: ");
        ResizingArrayQueueOfStrings raq = new ResizingArrayQueueOfStrings(StdIn.readInt());
        
        while (true) {
            StdOut.print("Enter operation "
                    + "[s - size, e <str> - enqueue, d - dequeue, v - view, p - peek,"
                    + " i - storage size]: ");
            switch (StdIn.readString()) {
            case "s": StdOut.printf("Size is %d\n", raq.size()); break;
            case "e": raq.enqueue(StdIn.readString()); break;
            case "p": StdOut.println(raq.peek()); break;
            case "d": StdOut.println(raq.dequeue()); break;
            case "i": StdOut.println(raq.storageSize()); break;
            case "v":
                for (String e : raq) {
                    StdOut.print(e + " ");
                }
                StdOut.println();
                break;
            }
        }
    }
}
