package chapter_1.section_4;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import utils.LangUtils;

/*
 * Task 1.4.12
 * 
 * Write a program that, given two sorted arrays of N int values, prints all elements that appear in
 * both arrays, in sorted order. The running time of your program should be proportional to N in the
 * worst case.
 */
public class Task_12 {

    private static int[] linearMerge(int[] a, int[] b) {
        int[] merged = new int[a.length + b.length];
        int a_i = 0, b_i = 0, merged_i = 0;
        while (true) {
            if (a_i == a.length) {
                System.arraycopy(b, b_i, merged, merged_i, b.length - b_i);
                break;
            }
            if (b_i == b.length) {
                System.arraycopy(a, a_i, merged, merged_i, a.length - a_i);
                break;
            }
            
            merged[merged_i++] = a[a_i] < b[b_i] ? a[a_i++] : b[b_i++];
        }
        return merged;
    }
    
    /* Tests */
    
    private static int __test_id = 0;
    
    private static void __runTest(int[] a, int[] b, int[] expected) {
        StdOut.printf("%d. %s\n", ++__test_id,
                      Arrays.equals(linearMerge(a, b), expected) ? "OK" : "Failed");
    }
    
    private static void __runTests() {
        __runTest(LangUtils.ints2arr(1, 3, 5), LangUtils.ints2arr(2, 4, 6),
                  LangUtils.ints2arr(1, 2, 3, 4, 5, 6));
        __runTest(LangUtils.ints2arr(3, 3), LangUtils.ints2arr(2, 4, 6),
                LangUtils.ints2arr(2, 3, 3, 4, 6));
        __runTest(LangUtils.ints2arr(1, 3, 5), LangUtils.ints2arr(22, 44, 66),
                LangUtils.ints2arr(1, 3, 5, 22, 44, 66));
    }
    
    public static void main(String[] args) {
        __runTests();
    }
}
