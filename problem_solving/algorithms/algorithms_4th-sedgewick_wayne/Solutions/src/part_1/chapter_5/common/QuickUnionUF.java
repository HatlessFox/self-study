package part_1.chapter_5.common;

import edu.princeton.cs.algs4.StdOut;

public class QuickUnionUF implements UF {

    public QuickUnionUF(int n) {
        count = n;
        id = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; ++i) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    private void link(int child_id, int parent_id) {
        id[child_id] = parent_id;
        sz[parent_id] += sz[child_id];
    }
    
    @Override
    public void union(int p, int q) {
        int p_id = find(p);
        int q_id = find(q);
        if (p_id == q_id) { return; }
        
        if (sz[p_id] < sz[q_id]) { link(p_id, q_id); }
        else                     { link(q_id, p_id); }

        --count;
    }

    @Override
    public int find(int p) {
        while (p != id[p]) { p = id[p]; }
        return p;
    }

    @Override public int count() { return count; }

    @Override public void dump_debug_info() {
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
    }
    
    private int count;
    private int[] id, sz;

}
