package part_1.chapter_5;

import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.5.1
 * 
 * Show the contents of the id[] array and the number of times the array is accessed for each input
 * pair when you use _quick-find_ for the sequence
 */
public class Task_01 {

    public static class UnionFindQuickFind {
        public UnionFindQuickFind(int n) {
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
            
            for (int i = 0; i < arr.length; ++i) {
                if (arr[i] == p_component) {
                    ++arr_access_nm;
                    arr[i] = q_component;
                }
                ++arr_access_nm;
            }
            count--;
        }
        
        public int find(int p) {
            ++arr_access_nm;
            return arr[p];
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
        UnionFindQuickFind ufqf = new UnionFindQuickFind(10);
        ufqf.union(9, 0);
        ufqf.union(3, 4);
        ufqf.union(5, 8);
        ufqf.union(7, 2);
        ufqf.union(2, 1);
        ufqf.union(5, 7);
        ufqf.union(0, 3);
        ufqf.union(4, 2);
        
        ufqf.dump_arr();
        StdOut.printf("Count: %d\n", ufqf.count());
        StdOut.printf("Array access number: %d\n", ufqf.arr_access_nm);
    }
}
