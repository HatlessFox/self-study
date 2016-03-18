package part_1.chapter_3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import utils.CliUtils;

/*
 * Task 1.3.42: Copy a stack.
 * 
 * Create a new constructor for the linked-list implementation of Stack so that
 *   Stack<T> t = new Stack<>(s);
 * makes a reference to a new and independent copy of the stack s.
 */
public class Task_42 {
    
    public static <T> Stack<T> copyStack(Stack<T> s) {
        Stack<T> reverse = new Stack<T>(), copy = new Stack<>();
        s.forEach(e -> reverse.push(e));
        reverse.forEach(e -> copy.push(e));
        return copy;
    }
    
    public static void main(String[] args) {
        Stack<String> s = new Stack<>(), copy = copyStack(s);
        while (true) {
            StdOut.print("Op (u[s|c] <e> -- push to s/c; o[s|q] -- pop s/c; c -- update copy): ");
            String op = StdIn.readString();
            switch (op.charAt(0)) {
            case 'u': {
                Stack<String> target = op.charAt(1) == 's' ? s : copy;
                target.push(StdIn.readString());
                break;
            }
            case 'o': {
                Stack<String> target = op.charAt(1) == 's' ? s : copy;
                StdOut.printf("Dequeued: %s\n", target.pop());
                break;
            }
            case 'c':
                copy = copyStack(s);
                break;
            }
            StdOut.printf("Orig: %s\nCopy: %s\n",
                    CliUtils.iterableToString(s), CliUtils.iterableToString(copy));
        }
    }
}
