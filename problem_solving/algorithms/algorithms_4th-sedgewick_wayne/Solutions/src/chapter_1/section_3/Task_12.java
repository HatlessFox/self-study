package chapter_1.section_3;

import java.util.Arrays;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.3.12
 * 
 * Write an iterable Stack client that has a static method copy() that takes a stack of strings as 
 * argument and returns a copy of the stack. Note: this ability is a prime example of the value of 
 * having an iterator, because it allows development of such functionality without changing the 
 * basic API.
 */
public class Task_12 {
    
    public static Stack<String> copy(Stack<String> copee) {
        // use program stack implicitly
        if (copee.isEmpty()) {
            return new Stack<String>();
        } else {
            String top_copee_e = copee.pop();
            Stack<String> copy = copy(copee);
            copy.push(top_copee_e);
            copee.push(top_copee_e);
            return copy;
        }
    }
    
    /* Tests */
    
    private static <T> String printStackToString(Stack<T> stack) {
        StringBuilder sb = new StringBuilder();
        stack.forEach(e -> sb.append(e + " "));
        return sb.toString().trim();
    }
    
    private static Stack<String> parseStringToStack(String stack_elts) {
        String[] tokens = stack_elts.split("\\s");
        //reverse tokens order
        for (int i = 0; i < tokens.length / 2; i++) {
            String tmp = tokens[i];
            tokens[i] = tokens[tokens.length - 1 - i];
            tokens[tokens.length - 1] = tmp;
        }
        Stack<String> dst = new Stack<>();
        Arrays.stream(tokens, 0, tokens.length).forEach(e -> dst.push(e));
        return dst;
        
    }
    
    private static void runTest(int test_id, String data) {
        Stack<String> copee = parseStringToStack(data);
        Stack<String> copy = copy(copee);
        boolean is_ok = printStackToString(copee).equals(data) &&
                        printStackToString(copy).equals(data);
        StdOut.printf("%d. %s\n", test_id, is_ok ? "OK" : "FAILED");
    }
    
    private static void __runTests() {
        int test_id = 0;
        runTest(++test_id, "");
        runTest(++test_id, "a");
        runTest(++test_id, "a b c");
    }
    
    public static void main(String[] args) {
        __runTests();
    }
}
