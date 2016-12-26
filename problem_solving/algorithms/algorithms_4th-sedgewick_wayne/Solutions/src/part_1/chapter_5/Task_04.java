package part_1.chapter_5;

import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.5.4
 * 
 * Show the contents of the sz[] and id[] arrays and the number of array accesses for each input
 * pair corresponding to the weighted quick-union examples in the text (both the reference input and
 * the worst-case input).
 */
public class Task_04 {

    public static class QFWeightedQuickUnion {
        public QFWeightedQuickUnion(int n) {
            id = new int[n];
            sz = new int[n];
            
            for (int i = 0; i < n; ++i) {
                id[i] = i;
                sz[i] = 1;
            }
            count = n;
        }
        
        public int find(int p) {
            while (p != id[p]) {
                p = id[p];
                arr_access += 2;
            }
            ++arr_access;
            return p;
        }
        
        public void union(int p, int q) {
            int p_id = find(p);
            int q_id = find(q);
            if (p_id == q_id) { return; }
            
            if (sz[p_id] < sz[q_id]) {
                id[p_id] = q_id;
                sz[q_id] += sz[p_id];
            } else {
                id[q_id] = p_id;
                sz[p_id] += sz[q_id];
            }
            
            arr_access += 5;
            count--;
        }
        
        public int count() { return count; }
        
        private int[] id, sz;
        private int count;
        private int arr_access;
        
        // Debug
        private void dump_internals() {
            StdOut.print("    ");
            for (int i = 0; i < id.length; ++i) {
                StdOut.printf("%d ", i);
            }
            StdOut.printf("\nID: ");
            for (int i = 0; i < id.length; ++i) {
                StdOut.printf("%d ", id[i]);
            }
            StdOut.printf("\nSZ: ");
            for (int i = 0; i < sz.length; ++i) {
                StdOut.printf("%d ", sz[i]);
            }
            StdOut.printf("\nArray access: %d; #components: %d\n", arr_access, count);
        }
    }
    
    // Tests
    
    private static void run_on_ref_input() {
        QFWeightedQuickUnion qfwqu = new QFWeightedQuickUnion(10);
        qfwqu.union(4, 3);
        qfwqu.union(3, 8);
        qfwqu.union(6, 5);
        qfwqu.union(9, 4);
        qfwqu.union(2, 1);
        qfwqu.union(8, 9);
        qfwqu.union(5, 0);
        qfwqu.union(7, 2);
        qfwqu.union(6, 1);
        qfwqu.union(1, 0);
        qfwqu.union(6, 7);
        
        StdOut.println("== Reference Input ==");
        qfwqu.dump_internals();
    }
    
    private static void run_on_worst_input() {
        QFWeightedQuickUnion qfwqu = new QFWeightedQuickUnion(8);
        qfwqu.union(0, 1);
        qfwqu.union(2, 3);
        qfwqu.union(4, 5);
        qfwqu.union(6, 7);
        qfwqu.union(0, 2);
        qfwqu.union(4, 6);
        qfwqu.union(0, 4);
        
        StdOut.println("== Worst-case Input ==");
        qfwqu.dump_internals();
    }
    
    public static void main(String[] args) {
        run_on_ref_input();
        run_on_worst_input();
    }
}
