package part_1.chapter_4;

import java.util.Arrays;

import utils.QA;

/*
 * Task 1.4.8
 * 
 * Write a program to determine the number pairs of values in an input file that are equal. If your
 * first try is quadratic, think again and use Arrays.sort() to develop a linearithmic solution.
 */
public class Task_08 {
    
    private static int numPairs(int n) {
        return n * (n - 1) / 2;
    }
    
    public static int countPairs(int[] data) {
        if (data.length == 0)
            return 0;
        
        Arrays.sort(data);
        
        int same_pairs_cnt = 0;
        int same_in_a_row = 1, prev_e = data[0];
        for (int i = 1; i < data.length; i++) {
            int curr_e = data[i];
            if (prev_e == curr_e) {
                same_in_a_row++;
            } else {
                same_pairs_cnt += numPairs(same_in_a_row);
                same_in_a_row = 1;
            }
            prev_e = curr_e;
        }
        same_pairs_cnt += numPairs(same_in_a_row);
        return same_pairs_cnt;
    }
    
    /* Tests */
    
    private static int[] toArr(int... args) {
        return args;
    }
    
    private static void __runTests() {
        QA<int[], Integer> qa = new QA<>(Task_08::countPairs);
        
        qa.runTest(toArr(), 0);
        qa.runTest(toArr(1, 2, 3), 0);
        qa.runTest(toArr(1, 2, 1, 2), 2);
        qa.runTest(toArr(1, 2, 2, 4, 5, 6, 4, 2, 3), 4);
        qa.runTest(toArr(7, 7, 7, 7), 6);
    }
    
    public static void main(String[] args) {
        __runTests();
    }
}
