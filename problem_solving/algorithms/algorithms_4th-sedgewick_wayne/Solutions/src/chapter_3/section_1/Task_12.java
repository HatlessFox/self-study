package chapter_3.section_1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import edu.princeton.cs.algs4.StdOut;

/*
 * Task 3.1.12
 * 
 * Modify BinarySearchST to maintain one array of Item objects that contain keys and values, rather
 * than tow parallel arrays. Add a constructor that takes an array of Item values as argument and
 * uses mergesort to sort the arrays.
 * 
 */
public class Task_12 {

    public static class BinarySearchST<Item extends Comparable<Item>> {
        protected Item[] items;
        protected int sz;

        @SuppressWarnings("unchecked")
        public BinarySearchST(int cap) {
            items = (Item[]) new Comparable[cap];
            sz = 0;
        }
        
        public BinarySearchST(Item[] init_elems) {
            items = Arrays.copyOf(init_elems, init_elems.length);
            Arrays.sort(items);
            sz = items.length;
        }
        
        private void resize(int cap) {
            int curr_cap = items.length;
            if (cap <= curr_cap) { return; }
            int new_cap = Math.max(2*curr_cap, cap);
            items = Arrays.copyOf(items, new_cap);
        }
        
        protected boolean containsByRank(int rank, Item item) {
            return rank < sz && items[rank].compareTo(item) == 0;
        }
        
        // Ops
        
        public void put(Item item) {
            int rank = rank(item);
            if (containsByRank(rank, item)) {
                items[rank] = item;
                return;
            }
            resize(sz + 1);
            System.arraycopy(items, rank, items, rank + 1, sz - rank);
            items[rank] = item;
            sz += 1;
        }

        public Item get(Item item) {
            int rank = rank(item);
            return containsByRank(rank, item) ? items[rank] : null;
        }
        
        public boolean contains(Item item) { return get(item) != null; }
        
        // Stats 

        public int rank(Item item) {
            int lo = 0, hi = sz - 1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                int cmp_res = items[mid].compareTo(item);
                if (cmp_res < 0) { lo = mid + 1; }
                else if (0 < cmp_res) { hi = mid - 1; }
                else { return mid; }
            }
            return lo;
        }

        public boolean isEmpty() { return size() == 0; }
        public int size() { return sz; }
        
        public int size(Item lo, Item hi) {
            if (hi.compareTo(lo) < 0) { return 0; }
            return rank(hi) - rank(lo) + (contains(hi) ? 1 : 0);
        }

        public Item min() { return items[0]; }
        public Item max() { return items[sz - 1]; }

        public Item ceiling(Item item) {
            return select(rank(item));
        }

        public Item select(int k) {
            return 0 <= k && k < sz ? items[k] : null;
        }
        
        // Keys
        
        public Iterable<Item> keys(Item lo, Item hi) {
            Queue<Item> itrbl = new LinkedList<>();
            int lo_rank = rank(lo), hi_rank = rank(hi);
            
            for (int i = lo_rank; i < hi_rank; ++i) {
                itrbl.add(items[i]);
            }
            if (containsByRank(hi_rank, hi)) {
                itrbl.add(items[hi_rank]);
            }
            
            return itrbl;
        }

        public Iterable<Item> keys() {
            return keys(min(), max());
        }
    }
    
    // Tests
    
    public static <Item extends Comparable<Item>> void dumpBinST(BinarySearchST<Item> bst) {
        bst.keys().forEach(k -> { StdOut.println(k); });
    }
    
    public static class FlightInfo implements Comparable<FlightInfo>{
        private String dst;
        private Task_04.Time time;
        
        public FlightInfo(String dst, int h, int m, int s) {
            this.dst = dst;
            time = new Task_04.Time(h, m, s);
        }

        @Override
        public int compareTo(FlightInfo that) { return time.compareTo(that.time); }
        
        @Override
        public String toString() { return String.format("%s %s", time, dst); }
    }
    
    public static void initStWithFlights(BinarySearchST<FlightInfo> bst) {
        bst.put(new FlightInfo("Houston", 9, 0, 13));
        bst.put(new FlightInfo("Chicago", 9, 3, 13));
        bst.put(new FlightInfo("Phoenix", 9, 14, 25));
        bst.put(new FlightInfo("Chicago", 9, 21, 5));
        bst.put(new FlightInfo("Chicago", 9, 25, 52));
        bst.put(new FlightInfo("Phoenix", 9, 37, 44));
        
        bst.put(new FlightInfo("Phoenix", 9, 0, 3));
        bst.put(new FlightInfo("Houston", 9, 1, 10));
        bst.put(new FlightInfo("Seattle", 9, 10, 25));
        bst.put(new FlightInfo("Chicago", 9, 19, 46));
        bst.put(new FlightInfo("Seattle", 9, 22, 54));
        bst.put(new FlightInfo("Seattle", 9, 36, 14));
        
        bst.put(new FlightInfo("Chicago", 9, 35, 21));
        bst.put(new FlightInfo("Seattle", 9, 22, 43));
        bst.put(new FlightInfo("Chicago", 9, 19, 32));
        bst.put(new FlightInfo("Seattle", 9, 10, 11));
        bst.put(new FlightInfo("Chicago", 9, 00, 59));
        bst.put(new FlightInfo("Chicago", 9, 00, 00));
    }
    
    public static void main(String[] args) {
        BinarySearchST<FlightInfo> bst = new BinarySearchST<>(3);
        initStWithFlights(bst);
        StdOut.println("== DUMP ==");
        dumpBinST(bst);
        
        StdOut.printf("min(): %s\n", bst.min());
        StdOut.printf("get(09:00:13): %s\n", bst.get(new FlightInfo("", 9, 0, 13)));
        StdOut.printf("select(7): %s\n", bst.select(7));
        StdOut.printf("ceiling(09:30:00): %s\n", bst.ceiling(new FlightInfo("", 9, 35, 0)));
        StdOut.printf("max(): %s\n", bst.max());
        StdOut.printf("size(09:15:00, 09:25:00): %s\n", bst.size(new FlightInfo("", 9, 15, 0),
                                                                 new FlightInfo("", 9, 25, 0)));
        StdOut.printf("rank(09:10:25): %s\n", bst.rank(new FlightInfo("", 9, 10, 25)));
        StdOut.println("== DUMP Inverval ==");
        for (FlightInfo k : bst.keys(new FlightInfo("", 9, 15, 0), new FlightInfo("", 9, 25, 0))) {
            StdOut.printf("%s %s\n", k, bst.get(k));
         }
    }
}
