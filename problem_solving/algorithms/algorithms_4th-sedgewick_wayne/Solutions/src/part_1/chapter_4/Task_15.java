package part_1.chapter_4;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import utils.LangUtils;
import utils.QA;

/*
 * Task 1.4.15: Faster 3-sum.
 * 
 * As a warmup, develop an implementation. TwoSumFaster that uses a linear algorithm to count
 * the pairs that sum to zero after the array is sorted (instead of the binary-search-based
 * linearithmic algorithm). Then apply a similar idea to develop a quadratic algorithm for
 * the 3-sum problem.
 */
public class Task_15 {
 
    public static class TwoSumFaster {
        public static int count(int[] data) {
            Arrays.sort(data);

            int lo = 0, hi = data.length - 1;
            int cnt = 0;
            while (lo < hi && data[lo] * data[hi] < 0) {
                if (data[lo] == -data[hi]) {
                    cnt++;
                    // NB: assume that data values are unique
                    lo++;
                    hi--;
                } else if (-data[lo] < data[hi]) {
                    hi--;
                } else {
                    lo++;
                }
            }
            return cnt;
        }
    }

    public static int countThreeSum(int[] data) {
        // NB: elements of data are assumed to be unique
        Arrays.sort(data);

        // TODO: ????
        return -1;
    }

    /* Tests */
    public static int countTwoSumBf(int[] data) {
        int cnt = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j < data.length; j++) {
                if (data[i] + data[j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
    
    public static void testFasterTwoSum() {
        StdOut.println("== Two Sum Faster Tests ==");
        QA<int[], Integer> qa = new QA<>(TwoSumFaster::count);
        qa.runTest(LangUtils.ints2arr(-1, 5, 1, -3, 2, 4, -2), 2);
    }

    public static int countThreeSumBf(int[] data) {
        int cnt = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j < data.length; j++) {
                for (int k = j + 1; k < data.length; k++) {
                    if (data[i] + data[j] + data[k] == 0) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }
    public static void testThreeSum() {
        StdOut.println("== Three Sum Faster Tests ==");
        QA<int[], Integer> qa = new QA<>(Task_15::countThreeSum);
        qa.runTest(LangUtils.ints2arr(-1, 5, 1, -3, 2, 4, -2), Task_15::countThreeSumBf);
    }

    public static void main(String[] args) {
        testFasterTwoSum();
        testThreeSum();
    }

}
