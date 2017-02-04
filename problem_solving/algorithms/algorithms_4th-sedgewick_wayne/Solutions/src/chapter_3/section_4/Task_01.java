package chapter_3.section_4;

import static chapter_3.section_4.Task_02.SeparateChainingHashST;

import utils.LangUtils;

/*
 * Task 3.4.1
 * 
 * Insert the keys EASYQUTION in that order into an initially empty table of M = 5 lists, using
 * separate chaining. Use the hash function 11*k % M to transform the kth letter of the alphabet
 * into a table index.
 */
public class Task_01 {

    public static class CharSeparateTable extends SeparateChainingHashST<Character, Integer> {

        public CharSeparateTable(int table_size) {
            super(table_size);
        }
        
        @Override
        protected int hashCode(Character k) {
            int eff_k = k - 'A' + 1;
            return 11 * eff_k % chains.length;
        }
    }
    
    public static final int M = 5;
    public static void main(String[] args) {
        CharSeparateTable cst = new CharSeparateTable(M);
        for (char ch : LangUtils.chrs2arr('E', 'A', 'S', 'Y', 'Q', 'U', 'T', 'I', 'O', 'N')) {
            cst.put(ch, 0);
        }
        cst.dump();
    }
}
