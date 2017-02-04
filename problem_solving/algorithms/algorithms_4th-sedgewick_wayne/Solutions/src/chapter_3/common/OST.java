package chapter_3.common;

/* symbol table that uses ordered values as keys (p. 366) */
public interface OST<Key extends Comparable<Key>, Value> extends ST<Key, Value>{
    public Key min();
    public Key max();
    public Key floor(Key k);
    public Key ceiling(Key k);
    public int rank(Key k);
    public Key select(int k);
    public Iterable<Key> keys(Key lo, Key hi);
    
    public default void deleteMin() { delete(min()); }
    public default void deleteMax() { delete(max()); }
    public default int size(Key lo, Key hi) {
        if (hi.compareTo(lo) < 0) { return 0; }
        return rank(hi) - rank(lo) + (contains(hi) ? 1 : 0);
    }
    public default Iterable<Key> keys() { return keys(min(), max()); }
}
