package chapter_3.section_4;

import chapter_3.section_4.Task_10.LinearProbingHashST;

/*
 * Task 3.4.11
 * 
 * Give the contents of a linear-probing hash table that results when you insert the keys EASYQUTION
 * in that order into an initially empty table of initial size M = 4 that is expanded with doubling
 * whenever half full. Use the hash function 11*k % M to transform the kth letter of the alphabet
 * into a table index.
 */
public class Task_11 {

    public static class CharLPHashST extends LinearProbingHashST<Character, Character> {
        public CharLPHashST(int table_sz) {
            super(table_sz, true);
        }

        @Override
        protected int hashCode(Character k) {
            int table_sz = ((Object[])keys).length;
            return 11 * (k - 'A' + 1) % table_sz;
        }
        
        @Override
        protected void resize(int new_table_sz) {
            dump();
            super.resize(new_table_sz);
        }
    }
    
    public static int INIT_TABLE_SZ = 4;
    public static final char[] CHRS = {'E', 'A', 'S', 'Y', 'Q', 'U', 'T', 'I', 'O', 'N'};
    public static void main(String[] args) {
        CharLPHashST ch_lp = new CharLPHashST(INIT_TABLE_SZ);
        for (char ch : CHRS) {
            ch_lp.put(ch, ch);
        }
        ch_lp.dump();
    }
}
