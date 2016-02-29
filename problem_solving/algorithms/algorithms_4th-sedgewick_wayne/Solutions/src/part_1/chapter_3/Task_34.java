package part_1.chapter_3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.3.34: Random Bag
 * 
 * A random bag stores a collection of items and supports the following API <skipped, p. 167>.
 * Write a class RandomBag that implements this API. Note that this API is the same as for Bag,
 * except for the adjective _random_, which indicates that the iteration should provide the items
 * is random order (all N! permutations equally likely, for each iterator).
 * _Hint_: Put the items in an array and randomize their order in the iterator's constructor.
 */
public class Task_34 {

    /* Implementation */
    
    public static class RandomBag<T> implements Iterable<T>{
        @SuppressWarnings("unchecked")
        private T[] holder = (T[])new Object[1];
        private int eff_sz;
        
        public RandomBag() {}
        
        private void resize(int new_sz) {
            int new_holder_sz = 0;
            if (holder.length < new_sz) {
                new_holder_sz = Math.max(new_sz, 2*holder.length);
            } else if (new_sz < holder.length / 4) {
                new_holder_sz = Math.min(new_sz, holder.length / 2);
            } else {
                return;
            }
            
            if (new_holder_sz == 0)
                return;
            
            @SuppressWarnings("unchecked")
            T[] new_holder = (T[]) new Object[new_holder_sz];
            System.arraycopy(holder, 0, new_holder, 0, eff_sz);
            holder = new_holder;
        }
        
        public boolean isEmpty() { return size() == 0; }
        
        public int size() { return eff_sz; }
        
        public void add(T e) {
            resize(eff_sz + 1);
            holder[eff_sz++] = e;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            forEach(e -> sb.append(e + " "));
            return sb.toString();
        }
        
        @Override
        public Iterator<T> iterator() { return new RandomBagIterator(); }
        
        public class RandomBagIterator implements Iterator<T> {
            private int i = 0;
            private T[] shuffled = holder.clone();
            
            private void swap(int i, int j) {
                if (i == j)
                    return;
                T tmp = shuffled[i];
                shuffled[i] = shuffled[j];
                shuffled[j] = tmp;
            }
            
            public RandomBagIterator() {
                Random rnd = new Random();
                for (int i = eff_sz - 1; 0 < i ; i--) {
                    swap(i, rnd.nextInt(i + 1));
                }
            }
            
            @Override
            public boolean hasNext() { return i < size(); }

            @Override
            public T next() { return shuffled[i++]; }
            
        }
    }
    
    /* Test */
    
    private static final boolean RUN_TEST = true;
    private static final int TRIALS = 1_000_000;
    
    private static int factorial(int n) {
        int prod = 1;
        for (int i = 1; i <= n; i++) {
            prod *= i;
        }
        return prod;
    }
    
    
    private static void __testRandomness(int elems) {
        RandomBag<Integer> rb = new RandomBag<>();
        for (int i = 0; i < elems; i++) {
            rb.add(i);
        }
        
        Map<String, Integer> stat = new HashMap<>();
        for (int i = 0; i < TRIALS; i++) {
            stat.compute(rb.toString(), (k, v) -> (v == null) ? 1 : v + 1);
        }
        
        stat.forEach((perm, cnt) -> StdOut.printf("%s: %.4f%%\n", perm, 1.0*cnt / TRIALS));
        StdOut.printf("Expected: %.4f%%\n", 1.0 / factorial(elems));
    }
    
    private static void __cli() {
        RandomBag<String> rand_bag = new RandomBag<>();
        while (true) {
            StdOut.print("Enter element: ");
            rand_bag.add(StdIn.readString());
            StdOut.println(rand_bag.toString());
        }
    }
    
    public static void main(String[] args) {
        if (RUN_TEST) __testRandomness(4); else __cli(); 
    }
}
