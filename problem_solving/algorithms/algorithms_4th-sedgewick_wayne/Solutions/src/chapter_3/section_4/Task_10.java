package chapter_3.section_4;

import java.util.LinkedList;
import java.util.Queue;

import chapter_3.common.ST;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 3.4.10
 * 
 * Insert the keys EASYQUTION in that order into an initially empty table of size M = 16 using
 * linear probing. Use the hash function 11*k % M to transform the kth letter of the alphabet into
 * a table index. Redo this exercise for M = 10.
 */
public class Task_10 {

    public static class LinearProbingHashST<Key, Value> implements ST<Key, Value> {
        
        protected Key[] keys;
        protected Value[] vals;
        protected int elem_nm;
        protected boolean is_resizable = false;
        
        public LinearProbingHashST(int table_sz) {
            this(table_sz, true);
        }
        
        @SuppressWarnings("unchecked")
        public LinearProbingHashST(int table_sz, boolean is_rszbl) {
            keys = (Key[]) new Object[table_sz];
            vals = (Value[]) new Object[table_sz];
            is_resizable = is_rszbl;
        }
        
        protected void copy(LinearProbingHashST<Key, Value> lphst) {
            keys = lphst.keys;
            vals = lphst.vals;
            elem_nm = lphst.elem_nm;
        }
        
        protected int hashCode(Key k) { return 11 * k.hashCode() % keys.length; }
        protected void resize(int new_table_sz) {
            if (!is_resizable) { return; }
            LinearProbingHashST<Key, Value> lphst = new LinearProbingHashST<>(new_table_sz);
            for (Key k : keys()) {
                lphst.put(k, get(k));
            }
            copy(lphst);
        }
        
        @Override
        public Value get(Key k) {
            for (int i = hashCode(k); keys[i] != null; i = (i + 1) % keys.length) {
                if (k.equals(keys[i])) {
                    return vals[i];
                }
            }
            return null;
        }

        @Override
        public void put(Key k, Value v) {
            if (keys.length / 2 <= elem_nm) {
                resize(keys.length * 2);
            }
            int i = hashCode(k);
            for (; keys[i] != null; i = (i + 1) % keys.length) {
                if (k.equals(keys[i])) { 
                    vals[i] = v;
                    return;
                }
            }
            keys[i] = k;
            vals[i] = v;
            ++elem_nm;
        }

        @Override
        public void delete(Key k) {
            int i = hashCode(k);
            for (; keys[i] != null; i = (i + 1) % keys.length) {
                if (k.equals(keys[i])) {
                    break;
                }
            }
            if (keys[i] == null) { return; }
            
            keys[i] = null;
            vals[i] = null;
            i = (i + 1) % keys.length;
            for (; keys[i] != null; i = (i + 1) % keys.length) {
                Key old_key = keys[i];
                Value old_val = vals[i];
                keys[i] = null;
                vals[i] = null;
                --elem_nm;
                
                put(old_key, old_val);
            }
            
            --elem_nm;
            if (0 < elem_nm && elem_nm < keys.length / 2) {
                resize(keys.length / 2);
            }
        }
        
        @Override
        public int size() { return elem_nm; }

        @Override
        public Iterable<Key> keys() {
            Queue<Key> itrbl_keys = new LinkedList<>();
            for (int i = 0; i < keys.length; ++i) {
                if (keys[i] == null) { continue; }
                itrbl_keys.add(keys[i]);
            }
            return itrbl_keys;
        }
        
        public void dump() {
            StdOut.printf("Table size = %d\n", keys.length);
            for (int i = 0; i < keys.length; ++i) {
                StdOut.printf("%s ", keys[i] == null ? "-" : keys[i]);
            }
            StdOut.println();
        }
    }
    
    public static class CharLPHashST extends LinearProbingHashST<Character, Character> {
        public CharLPHashST(int table_sz) {
            super(table_sz, false);
        }

        @Override
        protected int hashCode(Character k) {
            int table_sz = ((Object[])keys).length;
            return 11 * (k - 'A' + 1) % table_sz;
        }
    }
    
    public static final char[] CHRS = {'E', 'A', 'S', 'Y', 'Q', 'U', 'T', 'I', 'O', 'N'};
    public static final int[] TBL_SIZES = {10, 16};
    public static void main(String[] args) {
        for (int tbl_sz : TBL_SIZES) {
            CharLPHashST lphst = new CharLPHashST(tbl_sz);
            for (char ch : CHRS) {
                lphst.put(ch, ch);
            }
            lphst.dump();
        }
    }
}
