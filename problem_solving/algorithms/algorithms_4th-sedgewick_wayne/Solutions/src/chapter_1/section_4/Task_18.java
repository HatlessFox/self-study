package chapter_1.section_4;

import java.util.function.BiPredicate;

import utils.LangUtils;
import utils.QA;

/*
 * Task 1.4.18 (Local minimum of an array).
 * 
 * Write a program that, given an array a[] of N distinct integers, finds a _local minimum_: 
 * an entry a[i] that is strictly less that its neighbors. Each internal entry (other than a[0] and
 * a[N-1]) has 2 neighbors. Your program should use ~2 lg N compares in the worst case. 
 */
public class Task_18 {

    private static boolean isDecrLeft(int data[], int i) {
        return i != 0 && data[i-1] < data[i]; 
    }

    private static boolean isLocalMin(int data[], int i) {
        if (i == 0) { return data[i] < data[i + 1]; }
        if (i == data.length - 1) { return data[i - 1] > data[i]; }
        return data[i - 1] > data[i] && data[i] < data[i + 1];
    }
    
    public static int findLocalMin(int data[]) {
        if (data.length == 0) return -1;
        if (data.length == 1) return 0;
        
        int lo = 0, hi = data.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (isLocalMin(data, mid)) { return mid; }
            else if (isDecrLeft(data, mid)) { hi = mid; }
            else { lo = mid; }
        }
        return hi;
    }

    /////////////
    /* Testing */

    public static boolean verifyLocalMin(int[] data, int ind) {
        if (ind < 0 || data.length <= ind) return data.length == 0 && ind < 0;
        if (data.length == 1) return true;
        if (ind == 0               && data[ind] < data[ind + 1] ||
            ind == data.length - 1 && data[ind] < data[ind - 1]) return true;
        return data[ind - 1] > data[ind] && data[ind] < data[ind + 1]; 
    }

    public static void testLocalMinScrch() {
        QA<int[], Integer> qa = new QA<>(Task_18::findLocalMin);
        BiPredicate<int[], Integer> validator = Task_18::verifyLocalMin;
        
        qa.runTest(LangUtils.ints2arr(), validator);
        qa.runTest(LangUtils.ints2arr(5), validator);
        qa.runTest(LangUtils.ints2arr(1, 2), validator);
        qa.runTest(LangUtils.ints2arr(1, 3, 5, 4, 2), validator);
        qa.runTest(LangUtils.ints2arr(8, 7, 6, 5, 9, 10, 11), validator);
        qa.runTest(LangUtils.ints2arr(1, 2, 3, 4, 5, 6), validator);
        qa.runTest(LangUtils.ints2arr(6, 5, 4, 3), validator);
        qa.runTest(LangUtils.ints2arr(1, 6, 8, 5, 3, 4, 9, 7), validator);
        qa.runTest(LangUtils.ints2arr(10, 9, 1, 3, 5, 4, 2, 8, 11), validator);
    }

    public static void main(String[] args) {
        testLocalMinScrch();
    }
}
