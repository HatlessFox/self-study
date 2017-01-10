package chapter_1.section_3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.3.9
 * 
 * Write a program that takes from standard input an expression without left parentheses and prints 
 * the equivalent infix expression with the parentheses inserted. For example, given the input:
 *   1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) ) ==> ( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )
 *   
 * NB: According to p. 128 expression is defined as: number OR ( expression operator expression )
 */
public class Task_09 {
    
    /* Implementation */
    
    private static enum ElemType {
        RPARAN, NUMBER, OP;
        
        public static ElemType fromString(String value) {
            switch (value) {
            case ")": return RPARAN;
            case "+": case "*": case "/": case "-": case "%": return OP;
            default: return NUMBER;
            }
        }
    }
    
    private static int readExpression(String[] tokens, int i, Stack<String> dst) {
        switch (ElemType.fromString(tokens[i])) {
        case RPARAN:
            dst.push(tokens[i]);
            i = readExpression(tokens, i - 1, dst);
            dst.push(tokens[i]); // operator
            i = readExpression(tokens, i - 1, dst);
            dst.push("("); // fix left parenthesis
            return i;
        case OP:
            throw new RuntimeException("Op token is not expected");
        case NUMBER:
            dst.push(tokens[i]);
            return i - 1;
        }
        throw new RuntimeException("Unreachable end of readExpression");
    }
    
    private static String addLeftParentheses(String raw_data) {
        Stack<String> holder = new Stack<>();
        String[] tokens = raw_data.split("\\s");
        readExpression(tokens, tokens.length - 1, holder);
        
        StringBuilder sb = new StringBuilder();
        holder.forEach(s -> { sb.append(s); sb.append(" "); });
        return sb.toString().trim();
    }
    
    /* Tests */
    
    private static void runTest(int test_id, String input, String expected_output) {
        String output = addLeftParentheses(input);
        if (expected_output.equals(output)) {
            StdOut.printf("%d. Valid \n", test_id);
        } else {
            StdOut.printf("%d. Invalid. Output: %s\n",
                    test_id, output.isEmpty() ? "<empty>" : output);
        }
    }
    
    private static void __runTests() {
        runTest(0, "1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )",
                   "( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )");
    }
    
    public static void main(String[] args) {
        __runTests();
    }
}
