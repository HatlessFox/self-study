package chapter_1.section_3;

import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.3.4
 * 
 * Write a stack client Parentheses that reads in a text stream from standard input and uses a stack
 * to determine whether its parentheses are properly balanced. For example, your program should 
 * print true for [()]{}{[()()]()} and false for [(]).
 */
public class Task_04 {

    /* Implementation */
    
    private static final Map<Character, Character> PAREN_CORR = new HashMap<>();
    static {
        PAREN_CORR.put('(', ')');
        PAREN_CORR.put('{', '}');
        PAREN_CORR.put('[', ']');
    }
    
    private static boolean checkParentheses(String data) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < data.length(); i++) {
            Character token = data.charAt(i);
            switch (token) {
            case '(': case '{': case '[':
                stack.push(token);
                break;
            case ')': case '}': case ']':
                if (!token.equals(PAREN_CORR.get(stack.pop()))) {
                    return false;
                }
                break;
            }
        }
        return true;
    }
    
    /* Testing */
    
    private static final boolean __CLI_MODE = true;
    
    private static void runTest(int testId, String data) {
        StdOut.printf("%d. %b\n", testId, checkParentheses(data));
    }
    
    private static void __runTests() {
        int test_id = 0;
        runTest(++test_id, "[()]{}{[()()]()}");
        runTest(++test_id, "([)]");
    }
    
    private static void __cli() {
        while (true) {
            StdOut.print("Enter string: ");
            StdOut.printf("Parentheses are %sok\n\n",
                    checkParentheses(StdIn.readString()) ? "" : "not ");
        }
    }
    
    public static void main(String[] args) {
        if (__CLI_MODE) __cli(); else __runTests();
    }
}
