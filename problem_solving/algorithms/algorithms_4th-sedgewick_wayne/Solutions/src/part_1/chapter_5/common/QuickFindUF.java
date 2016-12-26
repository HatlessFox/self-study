package part_1.chapter_5.common;

public class QuickFindUF implements UF {
    public QuickFindUF(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; ++i) {
            id[i] = i;
        }
    }
    
    @Override
    public void union(int p, int q) {
        int p_id = find(p);
        int q_id = find(q);
        if (p_id == q_id) { return; }
        
        for (int i = 0; i < id.length; ++i) {
            if (id[i] == p_id) {
                id[i] = q_id;
            }
        }
        --count;
    }

    @Override public int find(int p) { return id[p]; }
    @Override public int count() { return count; }

    private int[] id;
    private int count;
}
