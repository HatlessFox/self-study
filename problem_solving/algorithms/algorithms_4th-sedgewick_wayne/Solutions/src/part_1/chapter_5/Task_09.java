package part_1.chapter_5;

import part_1.chapter_5.common.QuickUnionUF;

/*
 * Task 1.5.9
 * 
 * Draw the tree corresponding to the id[] array depicted at right. Can this be the result of
 * running weighted quick-union? Explain why this is impossible or give a sequence of operations
 * that resuts in this array.
 * 
 * i     | 0 1 2 3 4 5 6 7 8 9
 * id[i] | 1 1 3 1 5 6 1 3 4 5
 */
public class Task_09 {

    public static void main(String[] args) {
        QuickUnionUF uf = new QuickUnionUF(10);
        /*
        uf.union(0, 1);
        uf.union(3, 1);
        uf.union(2, 3);
        uf.union(7, 3);
        uf.union(1, 6);
        
        uf.union(5, 6);
        uf.union(9, 5);
        uf.union(4, 5);
        uf.union(8, 4);
        */
        uf.union(4, 3);
        uf.union(3, 8);
        uf.union(6, 5);
        uf.union(9, 4);
        uf.union(2, 1);
        uf.union(8, 9);
        uf.union(5, 0);
        uf.union(7, 2);
        uf.union(6, 1);
        uf.union(1, 0);
        uf.union(6, 7);
        
        uf.dump_debug_info();
    }
}
