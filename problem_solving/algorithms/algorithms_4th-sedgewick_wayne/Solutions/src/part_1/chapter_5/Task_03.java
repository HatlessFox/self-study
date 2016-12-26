package part_1.chapter_5;

import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.5.3
 * 
 * Show the contents of the id[] array and the number of times the array is accessed for each input
 * pair when you use _weighted quick-union_ for the sequence
 */
public class Task_03 {

    public static class UnionFindWeightedQuickUnion {
        public UnionFindWeightedQuickUnion(int n) {
            arr = new int[n];
            sz = new int[n];
            for (int i = 0; i < n; ++i) {
                arr[i] = i;
                sz[i] = 1;
            }
            count = n;
        }
        
        public void union(int p, int q) {
            int p_component = find(p);
            int q_component = find(q);
            if (p_component == q_component) { return; }
            
            if (sz[p_component] < sz[q_component]) {
                arr[p_component] = q_component;
                sz[q_component] += sz[p_component];
            } else {
                arr[q_component] = p_component;
                sz[p_component] += sz[q_component];
            }
            arr_access_nm += 5;
            
            count--;
        }
        
        public int find(int p) {
            while (p != arr[p]) {
                p = arr[p];
                arr_access_nm += 2;
            }
            ++arr_access_nm;
            return p;
        }
        
        public boolean connected(int p, int q) { return find(p) == find(q); }
        public int count() { return count; }
        
        private int[] arr, sz;
        private int count;
        
        // Debug
        public int arr_access_nm;
        public void dump_arr() {
            for (int i : arr) {
                StdOut.printf("%d ", i);
            }
            StdOut.println();
        }
    }
    
    public static void main(String[] args) {
        UnionFindWeightedQuickUnion ufwqu = new UnionFindWeightedQuickUnion(10);
        ufwqu.union(9, 0);
        ufwqu.union(3, 4);
        ufwqu.union(5, 8);
        ufwqu.union(7, 2);
        ufwqu.union(2, 1);
        ufwqu.union(5, 7);
        ufwqu.union(0, 3);
        ufwqu.union(4, 2);
        
        ufwqu.dump_arr();
        StdOut.printf("Count: %d\n", ufwqu.count());
        StdOut.printf("Array access number: %d\n", ufwqu.arr_access_nm);
    }

}
