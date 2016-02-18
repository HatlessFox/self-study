package part_1.chapter_3;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import utils.QA;

/* Task 1.3.11
 * 
 * Write a program EvaluatePostfix that takes a postfix expression from standard output,
 * evaluates it, and prints the value. (Piping the output of your program from the previous exercise
 * to this program gives equivalent to Evaluate)
 */
public class Task_11 {
    
    public static class EvaluatePostfix {
        
        private static final Map<String, BinaryOperator<Integer>> OPS;
        static {
            Map<String, BinaryOperator<Integer>> ops = new HashMap<>();
            ops.put("+", (a, b) -> a + b);
            ops.put("-", (a, b) -> a - b);
            ops.put("*", (a, b) -> a * b);
            ops.put("/", (a, b) -> a / b);
            ops.put("%", (a, b) -> a % b);
            OPS = Collections.unmodifiableMap(ops);
        }
        
        private static int evaluate(String[] tokens) {
            Stack<Integer> arg_stack = new Stack<>();
            for (String token : tokens) {
                if (OPS.containsKey(token)) {
                    int arg2 = arg_stack.pop(), arg1 = arg_stack.pop();
                    arg_stack.push(OPS.get(token).apply(arg1, arg2));
                } else {
                    arg_stack.push(Integer.parseInt(token));
                }
            }
            return arg_stack.pop();
        }
        
        public static int evaluate(String expression) {
            return evaluate(expression.split("\\s"));
        }
    }
    
    /* Tests */
    
    private static final boolean __RUN_TESTS = false;
    
    private static void __runTests() {
        QA<String, Integer> qa = new QA<String, Integer>(EvaluatePostfix::evaluate);
        qa.runTest("2 3 +", 5);
        qa.runTest("24 3 4 * /", 2);
    }

    private static void __cli() {
        while (!StdIn.isEmpty()) {
            StdOut.println(EvaluatePostfix.evaluate(StdIn.readLine()));
        }
    }
    
    public static void main(String[] args) {
        if (__RUN_TESTS) __runTests(); else __cli(); 
    }
}
