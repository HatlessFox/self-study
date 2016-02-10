package part_1.chapter_3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/* 
 * Task 1.3.2
 * 
 * Give the output printed by java Stack for the input
 * it was - the best - of times - - - it was - the - -
 */
public class Task_02 {

    public static void main(String[] args) {
        String[] tokens = new String[] {
                "it", "was", "-", "the", "best", "-", "of", "times", "-", "-", "-", "it", "was",
                "-", "the", "-", "-"};
        Stack<String> stack = new Stack<>();
        for (String token : tokens) {
            if (token.equals("-")) {
                StdOut.print(stack.pop() + " ");
            } else {
                stack.push(token);
            }
        }
    }
}
