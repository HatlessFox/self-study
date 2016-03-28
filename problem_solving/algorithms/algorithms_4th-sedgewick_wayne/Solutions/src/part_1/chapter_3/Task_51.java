package part_1.chapter_3;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import utils.QA;

/*
 * Task 1.3.51: Expression evaluation with precedence order.
 * 
 * Extend Evaluate (p. 129) to handle arithmetic expressions that are not fully parenthesized, using
 * the standard precedence order for the operators +, -, *, and /.
 */
public class Task_51 {
    
    private static final String LEFT_PAR = "(", RIGHT_PAR = ")";
    
    public static enum BinaryOp {
        ADD(1, (l, r) -> l + r),
        SUB(1, (l, r) -> l - r),
        MUL(2, (l, r) -> l * r),
        DIV(2, (l, r) -> l / r);
        
        public int priority;
        public BinaryOperator<Integer> op;
        
        BinaryOp(int pr, BinaryOperator<Integer> op) {
            priority = pr;
            this.op = op;
        }
        
        public int eval(int lft, int rht) { return op.apply(lft, rht); }
        
        public static boolean isBinaryOp(String op) {
            return BinaryOp.fromString(op) != null;
        }
        
        public static BinaryOp fromString(String op) {
            switch (op) {
            case "+": return ADD;
            case "-": return SUB;
            case "*": return MUL;
            case "/": return DIV;
            }
            return null;
        }
    }
    
    private static void evalOp(BinaryOp op, Stack<Integer> args) {
        assert 2 <= args.size();
        
        int right_arg = args.pop(), left_arg = args.pop();
        args.push(op.eval(left_arg, right_arg));
    }
    
    private static int eval(String[] tokens) {
       Stack<String> ops = new Stack<>();
       Stack<Integer> args = new Stack<>();
       for (String t : tokens) {
           if (t.equals(LEFT_PAR)) {
               ops.push(t);
           } else if (t.equals(RIGHT_PAR)) {
               while (!ops.peek().equals(LEFT_PAR)) {
                   evalOp(BinaryOp.fromString(ops.pop()), args);
               }
               ops.pop();
           } else if (BinaryOp.isBinaryOp(t)) {
               BinaryOp curr_op = BinaryOp.fromString(t);
               while (0 < ops.size()) {
                   String op = ops.peek();
                   BinaryOp prev_op = BinaryOp.fromString(op);
                   if (op.equals(LEFT_PAR) || prev_op.priority < curr_op.priority) {
                       break;
                   }
                   
                   evalOp(prev_op, args);
                   ops.pop();
               }
               ops.push(t);
           } else { // it is op argument
               args.push(Integer.parseInt(t));
           }
       }
       
       while (0 < ops.size()) {
           evalOp(BinaryOp.fromString(ops.pop()), args);
       }
       
       assert args.size() == 1;
       return args.pop();
    }
    
    public static Integer evalExpression(String e) {
        return eval(e.split("\\s"));
    }
    
    /* Tests */
    
    private static void __runTests() {
        QA<String, Integer> qa = new QA<>(Task_51::evalExpression);
        qa.runTest("2 + 3", 5);
        qa.runTest("1 + 2 + 3", 6);
        qa.runTest("1 * 2 + 3", 5);
        qa.runTest("1 + 2 * 3", 7);
        qa.runTest("( 1 + 2 ) * 3", 9);
        qa.runTest("6 + ( 4 / 2 + ( 2 + 3 ) ) * 5", 41);
    }
    
    public static void main(String[] args) {
        __runTests();
    }
}
