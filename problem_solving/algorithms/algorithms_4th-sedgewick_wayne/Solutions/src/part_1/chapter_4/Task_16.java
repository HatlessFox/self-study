package part_1.chapter_4;

import java.util.Arrays;

import utils.LangUtils;
import utils.QA;

/*
 * Task 1.4.16: Closest pair (in one dimension).
 * 
 * Write a program that, given an array a[] of N double values, finds a _closest pair_: two values
 * whose difference is no greater than the difference of any other pair (in absolute value).
 * The running time of your program should be linearithmic in the worst case.
 */
public class Task_16 {

    ////////////////////
    /* Implementation */

    public static class DoubleInterval implements Comparable<DoubleInterval>{
        public double fst, snd;

        public DoubleInterval(double a, double b) {
            fst = a;
            snd = b;
        }

        public double distance1d() { return Math.abs(fst - snd); }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (!(obj instanceof DoubleInterval)) return false;
            DoubleInterval that = (DoubleInterval) obj;
            return compareTo(that) == 0;
        }

        @Override
        public int compareTo(DoubleInterval that) {
            return Double.compare(distance1d(), that.distance1d());
        }
        
        @Override
        public String toString() {
            return String.format("{%.5f, %.5f}", fst, snd);
        }
    }

    public static DoubleInterval closestPair(double[] data) {
        DoubleInterval result = null;
        Arrays.sort(data);
        
        for (int i = 1; i < data.length; i++) {
            DoubleInterval testee = new DoubleInterval(data[i-1], data[i]);
            if (result == null || testee.compareTo(result) < 0) {
                result = testee;
            }
        }
        return result;
    }

    /////////////
    /* Testing */

    public static DoubleInterval closestPairBf(double[] data) {
        DoubleInterval result = null;
        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j < data.length; j++) {
                DoubleInterval testee = new DoubleInterval(data[i], data[j]);
                if (result == null || testee.compareTo(result) < 0) {
                    result = testee;
                }
            }
        }
        return result;
    }

    public static void testClosestPair() {
        QA<double[], DoubleInterval> qa = new QA<>(Task_16::closestPair);
        
        qa.runTest(LangUtils.dbls2arr(0, 1.2, .1, .3, .8), new DoubleInterval(0, 0.1));
        
        for (int i = 0; i < 10; i++) {
            double[] arr_100 = LangUtils.rndDoubles(100);
            double[] arr_100_copy = Arrays.copyOf(arr_100, arr_100.length);
            qa.runTest(arr_100, Task_16.closestPairBf(arr_100_copy));
        }
    }

    public static void main(String[] args) {
        testClosestPair();
    }
}
