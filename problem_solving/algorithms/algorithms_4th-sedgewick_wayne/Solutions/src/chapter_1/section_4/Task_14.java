package chapter_1.section_4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import utils.LangUtils;
import utils.QA;

/*
 * Task 1.4.14: 4-sum.
 * 
 * Develop an algorithm for the 4-sum problem
 */
public class Task_14 {

    /* Implementation */
    
    private static class OrderedPair implements Comparable<OrderedPair> {
        public OrderedPair(int i, int j, int sum) {
            if (j <= i) {
                throw new IllegalArgumentException();
            }
            this.i = i;
            this.j = j;
            this.sum = sum;
        }
        
        public OrderedPair(int sum) {
            i = j = -1;
            this.sum = sum;
        }
        
        public int i, j, sum;

        public static boolean areDisjoint(OrderedPair p1, OrderedPair p2) {
            return p1.j < p2.i || p2.j < p1.i;
        }
        
        @Override
        public int compareTo(OrderedPair that) { return this.sum - that.sum; }
    }
    
    private static List<OrderedPair> generatePairs(int[] data) {
        List<OrderedPair> pairs = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j < data.length; j++) {
                pairs.add(new OrderedPair(i, j, data[i] + data[j]));
            }
        }
        return pairs;
    }
    
    private static <T extends Comparable<? super T>> int computeBotBound(
            T key, List<? extends T> pairs, int st, int end /* list view */) {
        int lo = st, hi = end;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int mid_cmp = key.compareTo(pairs.get(mid));
            if (0 < mid_cmp) {
                lo = mid + 1;
            } else {
                if (mid_cmp == 0 &&
                    (mid == st || pairs.get(mid - 1).compareTo(key) < 0)) {
                    return mid;
                }
                hi = mid - 1;
            }
        }
        return -1;
    }
    
    private static <T extends Comparable<? super T>> int computeTopBound(
            T key, List<? extends T> pairs, int st, int end /* list view */) {
        int lo = st, hi = end;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int mid_cmp = key.compareTo(pairs.get(mid));
            if (mid_cmp < 0) {
                hi = mid - 1;
            } else {
                if (mid_cmp == 0 &&
                    (mid == end || key.compareTo(pairs.get(mid + 1)) < 0)) {
                    return mid;
                }
                lo = mid + 1;
            }
        }
        return -1;
    }
    
    private static int countFourSum(int[] data) {
        List<OrderedPair> pairs = generatePairs(data);
        Collections.sort(pairs);

        OrderedPair key = new OrderedPair(0);
        int cnt = 0;
        for (OrderedPair pair : pairs) {
            key.sum = -pair.sum;
            int sum_bot = computeBotBound(key, pairs, 0, pairs.size() - 1);
            if (sum_bot == -1) {
                continue;
            }
            int sum_top = computeTopBound(key, pairs, sum_bot, pairs.size() - 1);
            if (sum_top == -1) {
                throw new Error("[BUG] Unexpected missed top bound.");
            }
            for (int i = sum_bot; i <= sum_top; i++) {
                if (OrderedPair.areDisjoint(pair, pairs.get(i))) {
                    cnt++;
                }
            }
        }
        return cnt / 2;
    }
    
    /* Testing */
    
    private static int countFourSumBf(int[] data) {
        int cnt = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j < data.length; j++) {
                for (int k = j + 1; k < data.length; k++) {
                    for (int l = k + 1; l < data.length; l++) {
                        if (data[i] + data[j] + data[k] + data[l] == 0) {
                            cnt++;
                        }
                    }
                }
            }
        }
        return cnt;
    }
    
    private static int[] genRandInts(int size, int width) {
        Random rnd = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = width / 2 - rnd.nextInt(width);
        }
        return arr;
    }
    
    public static void main(String[] args) {
        QA<int[], Integer> qa = new QA<>(Task_14::countFourSum);
        qa.runTest(LangUtils.ints2arr(1, 2, -3, 0), 1);
        qa.runTest(LangUtils.ints2arr(1, -2, -3, 4), 1);
        qa.runTest(LangUtils.ints2arr(3, 1, -3, 0), 0);
        
        for (int i = 0; i < 10; i++) {
            int[] arr_100 = genRandInts(100, 17);
            if (countFourSum(arr_100) == 0) {
                i--;
            }
            qa.runTest(arr_100, countFourSumBf(arr_100));
            
        }
    }
}
