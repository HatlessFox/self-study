package chapter_1.section_1;

import edu.princeton.cs.algs4.StdOut;

public class Task_29 {

    /* Implementation */
    
    /* index of the first e that <= k */
    private static int leftBound(int k, int[] a) {
        int lo = 0, hi = a.length - 1, mid = 0;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (a[mid] < k) {
                lo = mid + 1;
            }
            else if (a[mid] == k && (mid == 0 || a[mid-1] != k)) {
                return mid;
            }
            else { /* k < a[mid] || a[mid-1] == k */
                hi = mid - 1;
            }
        }
        // Case: no key
        if (k < a[mid]) return mid;
        return (mid == a.length - 1) ? a.length : mid-1;
    }
    
    /* return index of element right before the first element that is greater that k */
    private static int rightBound(int k, int[] a) {
        int lo = 0, hi = a.length - 1, mid = 0;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (k < a[mid]) {
                hi = mid - 1;
            }
            else if (a[mid] == k && (mid == a.length-1 || a[mid+1] != k)) {
                return mid;
            }
            else { /* a[mid] < k || a[mid+1] == k */
                lo = mid + 1;
            }
        }
        if (a[mid] < k) return mid;
        return (a[mid] == 0) ? -1 : mid + 1;
    }
    
    private static int rank(int k, int[] a) {
        return leftBound(k, a);
    }
    
    private static int count(int k, int[] a) {
        int lb = leftBound(k, a);
        if (lb == -1 || lb == a.length || a[lb] != k)
            return 0;
        return rightBound(k, a) - leftBound(k, a) + 1;
    }
    
    /* Tests */
    
    /* O(n), brute force */
    private static int countSmaller(int k, int[] a) {
        int cnt = 0;
        for (int e : a) {
            if (k <= e) continue;
            cnt++;
        }
        return cnt;
    }
    
    /* O(n), brute force */
    private static int countSame(int k, int[] a) {
        int cnt = 0;
        for (int e : a) {
            if (e != k) continue; 
            cnt++;
        }
        return cnt;
    }
    
    private static int __task_i = 0;
    
    private static void __testRank(int k, int[] a) {
        StdOut.printf("%d. %s\n", ++__task_i, rank(k, a) == countSmaller(k, a) ? "OK" : "FAIL");
    }
    
    private static void __testCount(int k, int[] a) {
        StdOut.printf("%d. %s\n", ++__task_i, count(k, a) == countSame(k, a) ? "OK" : "FAIL");
    }
    
    private static void __runTests() {
        StdOut.println("== Rank tests ==");
        __testRank(-1, new int[]{1, 2, 3, 4});
        __testRank(15, new int[]{1, 2, 4});
        __testRank(8, new int[]{1, 2, 4, 5, 5, 7, 9});
        __testRank(5, new int[]{1, 2, 4, 5, 7, 9});
        __testRank(5, new int[]{1, 2, 4, 5, 5, 7, 9});
        __testRank(5, new int[]{5, 5, 7, 9, 10, 11, 12});
        __testRank(5, new int[]{0, 1, 2, 3, 4, 5, 5, 5});
        
        __task_i = 0;
        StdOut.println("== Count tests ==");
        __testCount(-1, new int[]{1, 2, 3, 4});
        __testCount(15, new int[]{1, 2, 4});
        __testCount(8, new int[]{1, 2, 4, 5, 5, 7, 9});
        __testCount(5, new int[]{1, 2, 4, 5, 7, 9});
        __testCount(5, new int[]{1, 2, 4, 5, 5, 7, 9});
        __testCount(5, new int[]{5, 5, 7, 9, 10, 11, 12});
        __testCount(5, new int[]{0, 1, 2, 3, 4, 5, 5, 5});
        
    }
    
    public static void main(String[] args) {
        __runTests();
    }
    
}
