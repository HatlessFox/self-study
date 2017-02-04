package chapter_3.section_4;

import static chapter_3.section_4.Task_02.SeparateChainingHashST;

public class Task_09 {

    public static class SChEagerDelete<Key, Value> extends SeparateChainingHashST<Key, Value> {

        public SChEagerDelete(int table_size) {
            super(table_size);
        }

        @Override
        public void delete(Key k) {
            int chain_i = hashCode(k);
            chains[chain_i] = delete(k, chains[chain_i]);
        }
        protected Node delete(Key k, Node node) {
            if (node == null) { return null; }
            if (k.equals(node.k)) { return node.next; }
            node.next = delete(k, node.next);
            return node;
        }
    }

    public static final int N = 10;
    public static void main(String[] args) {
        SChEagerDelete<Integer, Integer> st = new SChEagerDelete<>(4);
        for (int i = 0; i < N; ++i) { st.put(i, i); }
        for (int i = N / 2; i < N; ++i) { st.delete(i); }
        st.dump();
    }
}
