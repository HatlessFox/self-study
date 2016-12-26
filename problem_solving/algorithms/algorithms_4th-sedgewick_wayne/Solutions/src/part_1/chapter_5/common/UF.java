package part_1.chapter_5.common;

public interface UF {
    void union(int p, int q);
    int find(int p);
    int count();
    
    default boolean connected(int p, int q) { return find(p) == find(q); }
    
    default void dump_debug_info() {}
    default int array_access_nm() { return -1; }
}
