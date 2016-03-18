package part_1.chapter_3;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.3.45: Stack generability.
 * 
 * Suppose that we have a sequence of intermixed _push_ and _pop_ operations as with our test stack 
 * client, where the integers 0, 1, ..., N-1 in that order (_push_ directives) are intermixed with 
 * N minus signs (_pop_ directives).
 *   - Devise an algorithm that determines whether the intermixed sequence causes the stack to
 *     underflow. (You may use only an amount of space independent of N -- you cannot store
 *     the integers in a data structure.
 *   - Devise a liner-time algorithm that determines whether a given permutation can be generated as 
 *     output by our test client (depending on where the _pop_ directives occur).
 */
public class Task_45 {

    public static boolean checkUnderflow(String[] data) {
        int stack_sz = 0;
        for (String e : data) {
            stack_sz += e.equals("-") ? -1 : 1;
            if (stack_sz < 0) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isValid(Integer[] data) {
        int max_in_stack = data.length;
        int prev = -1;
        for (int e : data) {
            if (prev < e && e < max_in_stack) {
                // (?!): e has just been added && e has been added *before* max_in_stack
                return false;
            }
            max_in_stack = Math.max(e, max_in_stack);
            prev = e;
        }
        return true;
    }
    
    public static void main(String[] args) {
        while (true) {
            StdOut.print("Undeflow check/Validation {(u|v) data}: ");
            String op = StdIn.readString();
            String[] data = StdIn.readLine().trim().split(" ");
            switch (op) {
            case "u":
                StdOut.println(checkUnderflow(data));
                break;
            case "v":
                Integer[] input = 
                    Arrays.stream(data).map(Integer::parseInt).toArray(Integer[]::new);
                StdOut.println(isValid(input));
                break;
            }
        }
    }
}
