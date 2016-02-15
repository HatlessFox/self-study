package part_1.chapter_3;

import java.util.Arrays;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import utils.Tester;

/*
 * Task 1.3.10
 * 
 * Write a filter InfixToPostFix that converts an arithmetic expression from infix to postfix.
 * NB: expression is defined on page 128; filter -- on page 60; expression is correct.
 */
public class Task_10 {
    
    private static enum TokenType {
        LPAR, RPAR, NUM, OP;
        
        public static TokenType fromString(String value) {
            switch (value) {
            case "(": return LPAR;
            case ")": return RPAR;
            case "+": case "-": case "*": case "/": case "%": return OP;
            default: return NUM;
            }
        }
    }
    
    private static class InfixToPostfix {
        private Queue<String> consumer;
        private Stack<String> op_buffer;
        
        public InfixToPostfix(Queue<String> consumer) {
            this.consumer = consumer;
            op_buffer = new Stack<String>();
        }
        
        public void addToken(String t) {
            switch (TokenType.fromString(t)) {
            case LPAR: break;
            case RPAR: returnToken(op_buffer.pop()); break;
            case OP:   op_buffer.push(t);            break;
            case NUM:  returnToken(t);               break;
            }
        }
        
        private void returnToken(String t) {
            consumer.enqueue(t);
        }
    }
    
    private static String toPostfix(String data) {
        String[] tokens = data.split("\\s");
        Queue<String> dst = new Queue<>();
        
        InfixToPostfix converter = new InfixToPostfix(dst);
        Arrays.stream(tokens).forEach(t -> converter.addToken(t));
        
        StringBuilder sb = new StringBuilder();
        dst.forEach(t -> { sb.append(t); sb.append(" "); });
        
        return sb.toString().trim();
    }
    
    /* Tests */
    
    private static void __runTests() {
        Tester<String, String> tstr = new Tester<>(Task_10::toPostfix);
        
        tstr.runTest("( 2 + 3 )", "2 3 +");
        tstr.runTest("( ( 1 + 2 ) + ( ( 3 / 4 ) * ( 5 - 6 ) ) )",
                     "1 2 + 3 4 / 5 6 - * +");
    }
    
    public static void main(String[] args) {
        __runTests();
    }
}
