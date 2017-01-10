package chapter_2.section_4;

import java.util.LinkedList;
import java.util.ListIterator;

import edu.princeton.cs.algs4.StdOut;
import utils.LangUtils;

/*
 * Task 2.4.3
 * 
 * Provide priority-queue implementations that support _instert_ and _remove the maximum_, one for
 * each of the following underlying data structures: unordered array, ordered array, unordered list,
 * and ordered linked list. Give a table of the worst-case bounds for each operation for each of
 * your four implementations.
 */
public class Task_03 {

    public static interface PriorityQueue {
        public void insert(int v);
        public int rmMax();
        public boolean isEmpty();
    }
    
    public static class UnorderedArrayPQ implements PriorityQueue {
        private int sz, cap;
        private int[] data;
        
        public UnorderedArrayPQ(int init_cap) {
            cap = init_cap;
            data = new int[cap]; 
        }
        
        @Override
        public void insert(int v) {
            if (sz == cap) {
                int new_cap = cap * 2;
                int[] new_data = new int[new_cap];
                System.arraycopy(data, 0, new_data, 0, sz);
                cap = new_cap;
                data = new_data;
            }
            
            data[sz++] = v;
        }

        @Override
        public boolean isEmpty() {
            return sz == 0;
        }
        
        @Override
        public int rmMax() {
            if (isEmpty()) { return Integer.MAX_VALUE; }
            
            // find mx
            int mx_i = 0;
            for (int i = 1; i < sz; ++i) {
                if (data[i] <= data[mx_i]) { continue; }
                mx_i = i;
            }
            
            // mv items
            int mx_val = data[mx_i];
            System.arraycopy(data, mx_i + 1, data, mx_i, sz - mx_i - 1);
            --sz;
            return mx_val;
        }
    }
    
    public static class OrderedArrayPQ implements PriorityQueue {
        private int sz, cap;
        private int[] data;
        
        public OrderedArrayPQ(int init_cap) {
            cap = init_cap;
            data = new int[cap]; 
        }
        
        @Override
        public void insert(int v) {
            if (sz == cap) {
                int new_cap = cap * 2;
                int[] new_data = new int[new_cap];
                System.arraycopy(data, 0, new_data, 0, sz);
                cap = new_cap;
                data = new_data;
            }
            
            data[sz++] = v;
            for (int i = sz - 1; 0 < i; --i) {
                if (data[i-1] < data[i]) { break; }
                int tmp = data[i];
                data[i] = data[i-1];
                data[i-1] = tmp;
            }
        }

        @Override
        public int rmMax() {
            if (isEmpty()) { return Integer.MAX_VALUE; }
            return data[--sz]; // NB: also assign to null in case of objects
        }
        
        @Override
        public boolean isEmpty() { return sz == 0; }
    }
    
    public static class UnorderedListPQ implements PriorityQueue {
        private LinkedList<Integer> lst = new LinkedList<>();
        
        @Override
        public void insert(int v) { lst.add(v); }

        @Override
        public int rmMax() {
            if (isEmpty()) { return Integer.MAX_VALUE; }
            
            // find mx
            int mx_val = lst.getFirst();
            for (Integer i : lst) {
                if (mx_val < i) { mx_val = i; }
            }
            
            // rm max
            lst.removeFirstOccurrence(mx_val);
            return mx_val;
        }

        @Override
        public boolean isEmpty() { return lst.isEmpty(); }
    }
    
    public static class OrderedListPQ implements PriorityQueue {
        private LinkedList<Integer> lst = new LinkedList<>();
        
        @Override
        public void insert(int v) {
            ListIterator<Integer> it = lst.listIterator();
            while (it.hasNext()) {
                if (v < it.next()) {
                    it.previous();
                    it.add(v);
                    return;
                }
            }
            lst.addLast(v);
        }

        @Override
        public int rmMax() {
            if (isEmpty()) { return Integer.MAX_VALUE; }
            return lst.removeLast();
        }

        @Override
        public boolean isEmpty() { return lst.isEmpty(); }
    }

    // Tests
    
    public static void fillPq(PriorityQueue pq) {
        for (int e : LangUtils.ints2arr(2, 7, 0, 4, 3, 5, 1, 9, 8, 6)) {
            pq.insert(e);
        }
    }
    
    public static void printPq(PriorityQueue pq) {
        StdOut.printf("=== %s ===\n", pq.getClass().getSimpleName());
        while (!pq.isEmpty()) {
            StdOut.printf("%d ", pq.rmMax());
        }
        StdOut.println();
    }
    
    public static void main(String[] args) {
        PriorityQueue pqs[] = { new UnorderedArrayPQ(5), new OrderedArrayPQ(5),
                                new UnorderedListPQ(), new OrderedListPQ()};
        for (PriorityQueue pq : pqs) {
            fillPq(pq);
            printPq(pq);
        }
    }
}
