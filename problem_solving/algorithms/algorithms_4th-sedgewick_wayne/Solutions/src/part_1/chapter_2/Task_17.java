package part_1.chapter_2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.2.17: Robust implementation of rational numbers
 * 
 * Use assertions to develop an implementation of Rational that is immune to overflow.
 * 
 * NB: byte and warning printing is used for to simplify tests
 */
public class Task_17 {

    /* Implementation */
    
    private static void asserctCheck(boolean cond, String msg) {
        if (!cond) return;
        StdOut.printf("[Warning] %s\n", msg);
    }
    
    private static boolean checkAddOverflow(byte a, byte b) {
        if (a * b < 0) return false;
        if (a < 0) {
            a = (byte)-a;
            b = (byte)-b;
        }
        
        boolean is_overflow = (a + b) < a || (a + b) < b;
        asserctCheck(is_overflow, String.format("%d + %d = %d", a + b, a, b));
        return is_overflow;
    }
    
    private static boolean checkMulOverflow(byte a, byte b) {
        if (a < 0) {
            a = (byte)-a;
        }
        if (b < 0) {
            b = (byte)-b;
        }
        boolean is_overflow = (byte)(a * b) < a || (byte)(a * b) < b;
        asserctCheck(is_overflow, String.format("%d * %d = %d", a, b, (byte)(a * b)));
        return is_overflow;
    }
    
    private static class Rational {
        private byte n, d;
        private static final Rational MINUS_ONE = new Rational((byte)-1, (byte)1);
        private static final Rational NaN = new Rational((byte)1, (byte)0);
        
        private int gcd(int a, int b) {
            while (b != 0) {
                int tmp = b;
                b = a % b;
                a = tmp;
            }
            return a;
        }
        
        public Rational(byte numerator, byte denominator) {
            int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
            n = (byte)(numerator / gcd);
            d = (byte)(denominator / gcd);
            if (d < 0) {
                n = (byte)-n;
                d = (byte)-d;
            }
        }
        public Rational plus(Rational that) {
            if (checkMulOverflow(d, that.d) || checkMulOverflow(n, that.d) ||
                checkMulOverflow(d, that.n) || 
                checkAddOverflow((byte)(n*that.d), (byte)(d*that.n))) {
                return NaN;
            }
            return new Rational((byte)(n * that.d + d * that.n), (byte)(d * that.d));
        }
        public Rational minus(Rational that) { return plus(MINUS_ONE.times(that)); }
        public Rational times(Rational that) {
            if (checkMulOverflow(n, that.n) || checkMulOverflow(d, that.d)) {
                return NaN;
            }
            return new Rational((byte)(n * that.n), (byte)(d * that.d));
        }
        public Rational dividedBy(Rational that) { return times(new Rational(that.d, that.n)); }
        public boolean equals(Object that) {
            if (that == this) return true;
            if (that == null) return false;
            if (getClass() != that.getClass()) return false;
            Rational b = (Rational) that;
            return n == b.n && d == b.d;
        }
        public String toString() {
            switch (d) {
                case 0:  return "NaN";
                case 1:  return String.format("%d", n);
                default: return String.format("%d/%d", n, d);
            }
        }
    }
    
    /* Test scaffolding */
    
    private static Rational readRational(String prompt) {
        if (!prompt.isEmpty()) { StdOut.println(prompt); }
        StdOut.print("Enter numerator and denominator: ");
        return new Rational((byte)StdIn.readInt(), (byte)StdIn.readInt());
    }
    
    private static void __cli() {
        while (true) {
            Rational a = readRational("== Enter a =="), b = readRational("== Enter b ==");
            StdOut.print("Enter operation (+, -, *, /, =, e): ");
            String op = StdIn.readString();
            switch (op) {
            case "+": StdOut.printf("a%sb -- %s\n", op, a.plus(b)); break;
            case "-": StdOut.printf("a%sb -- %s\n", op, a.minus(b)); break;
            case "/": StdOut.printf("a%sb -- %s\n", op, a.dividedBy(b)); break;
            case "*": StdOut.printf("a%sb -- %s\n", op, a.times(b)); break;
            case "=": StdOut.printf("a%sb -- %s\n", op, a.equals(b)); break;
            case "e": StdOut.printf("Bye"); return;
            }
        }
    }
    
    public static void main(String[] args) {
        __cli();
    }
}
