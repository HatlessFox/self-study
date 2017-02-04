package chapter_3.section_4;

import chapter_3.section_4.Task_02.SeparateChainingHashST;

/*
 * Task 3.4.18
 * 
 * Add a constructor to SeparateChainingHashST that gives the client the ability
 * to specify the average number of probes to be tolerated for searches. Use array resizing to keep
 * the average list size less than the specified value, and use the technique described on page 478
 * to ensure that the modulus for hash() is prime.
 */
public class Task_18 {

    public static class ResizingSChST<Key, Value> extends SeparateChainingHashST<Key, Value> {
        protected double avg_lst_sz;
        protected int log_tbl_sz;
        
        private static int log2(int tbl_sz) {
            return (int)(Math.log(tbl_sz) / Math.log(2));
        }
        
        public ResizingSChST(int table_size, double avg_lst_size) {
            super(table_size);
            log_tbl_sz = log2(table_size);
            avg_lst_sz = avg_lst_size;
        }
        
        public void copy(ResizingSChST<Key, Value> rsch_st) {
            chains = rsch_st.chains;
            avg_lst_sz = rsch_st.avg_lst_sz;
            log_tbl_sz = log2(rsch_st.chains.length);
        }
        
        protected static final int[] PRIMES = {31, 61, 127, 251, 509, 1021};
        
        @Override
        protected int hashCode(Key k) {
            int hash = k.hashCode() & 0x7FFFFFFF;
            if (log_tbl_sz < PRIMES.length) { hash %= PRIMES[log_tbl_sz]; }
            return hash % chains.length;
        }
        
        @Override
        public void put(Key k, Value v) {
            if (chains.length * avg_lst_sz <= size()) {
                resize(chains.length * 2);
            }
            super.put(k, v);
        }
        
        protected void resize(int new_chains_nm) {
            ResizingSChST<Key, Value> rsch_st = new ResizingSChST<>(new_chains_nm, avg_lst_sz);
            for (Key key : keys()) {
                rsch_st.put(key, get(key));
            }
            copy(rsch_st);
        }
    }
    
    public static void main(String[] args) {
        ResizingSChST<Integer, Integer> rsch_st = new ResizingSChST<>(5, 5);
        for (int i = 0; i < 200; i++) {
            rsch_st.put(i, i);
        }
        rsch_st.dump();
    }
}
