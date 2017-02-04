package chapter_3.common;

/* basic symbol-table API (p. 363) */
public interface ST<Key, Value> {
    public Value get(Key k);
    public void put(Key k, Value v);
    public default void delete(Key k) { put(k, null); }
    public default boolean contains(Key k) { return get(k) != null; }
    public default boolean isEmpty() { return size() == 0; }
    public int size();
    public Iterable<Key> keys();
}
