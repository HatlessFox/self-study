package chapter_2.section_1;

import edu.princeton.cs.algs4.StdOut;

/*
 * Task 2.1.11
 * 
 * Implement a version of shellsort that keeps the increment sequence in an array, rather than
 * computing it.
 * 
 */
public class Task_11 {

    public static class Shellsort {
        
        private static final int H_IDX[] = {1093, 364, 121, 40, 13, 4, 1}; 
        
        public <Key extends Comparable<Key>> void sort(Key[] a) {
            int n = a.length;
            for (int h : H_IDX) {
                if (n / 3 < h) {
                    continue;
                }
                cmp_nm = 0;
                // insert each element into appropriate h-array
                for (int i = 0; i < n; ++i) {
                    for (int j = i; h <= j; j -= h) {
                        if (less(a[j - h], a[j]))
                            break;
                        exch(a, j - h, j);
                    }
                }
                StdOut.printf("%d %d %.2f\n", n, h, 1.0 * cmp_nm / n);
            }
        }
        
        private <Key extends Comparable<Key>> boolean less(Key a, Key b) {
            cmp_nm++;
            return a.compareTo(b) < 0;
        }
        
        private <Key> void exch(Key[] a, int i, int j) {
            Key tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
        
        private int cmp_nm;
    }
    
    public static void main(String[] args) {
        final String TEST_STR = "SHELLSORTEXAMPLE";
        Character data[] = new Character[TEST_STR.length()];
        for (int i = 0; i < TEST_STR.length(); ++i) {
            data[i] = TEST_STR.charAt(i);
        }
        new Shellsort().sort(data);
        
        for (Character c : data) {
            StdOut.printf("%c", c);
        }
        StdOut.println();
    }
}
