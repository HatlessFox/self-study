package chapter_1.section_5;

import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.5.2
 * 
 * Show the contents of the id[] array and the number of times the array is accessed for each input
 * pair when you use _quick-union_ for the sequence
 */
public class Task_02 {

    public static class UnionFindQuickUnion {
        public UnionFindQuickUnion(int n) {
            arr = new int[n];
            for (int i = 0; i < n; ++i) {
                arr[i] = i;
            }
            count = n;
        }
        
        public void union(int p, int q) {
            int p_component = find(p);
            int q_component = find(q);
            if (p_component == q_component) { return; }
            
            arr[p_component] = q_component;
            ++arr_access_nm;
            
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
        
        private int[] arr;
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
        UnionFindQuickUnion ufqu = new UnionFindQuickUnion(10);
        ufqu.union(9, 0);
        ufqu.union(3, 4);
        ufqu.union(5, 8);
        ufqu.union(7, 2);
        ufqu.union(2, 1);
        ufqu.union(5, 7);
        ufqu.union(0, 3);
        ufqu.union(4, 2);
        
        ufqu.dump_arr();
        StdOut.printf("Count: %d\n", ufqu.count());
        StdOut.printf("Array access number: %d\n", ufqu.arr_access_nm);
    }
}
