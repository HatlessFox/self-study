package part_1.chapter_2;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.2.16: Rational numbers
 * 
 * Implement an immutable data type Rational for rational numbers that supports addition,
 * subtraction, multiplication, and division.
 * You don't have to worry about testing for overflow, but use as instance variables two long values
 * that represent the numerator and denominator to limit the possibility of overflow. Use Euclid's 
 * algorithm to ensure that the numerator and denominator never have any common factors. Include a 
 * test client that exercises all of your methods.
 */
public class Task_16 {

    /* Implementation */
    
    public static class Rational {
        private long n, d;
        private static final Rational MINUS_ONE = new Rational(-1, 1);
        
        private long gcd(long a, long b) {
            while (b != 0) {
                long tmp = b;
                b = a % b;
                a = tmp;
            }
            return a;
        }
        
        public Rational(long numerator, long denominator) {
            long gcd = gcd(Math.abs(numerator), Math.abs(denominator));
            n = numerator / gcd;
            d = denominator / gcd;
            if (d < 0) {
                d = -d;
                n = -n;
            }
        }
        
        public Rational plus(Rational b) { return new Rational(n*b.d + b.n*d, d * b.d); }
        public Rational times(Rational b) { return new Rational(n * b.n, d * b.d); }
        public Rational minus(Rational b) { return plus(b.times(MINUS_ONE)); }
        public Rational dividedBy(Rational b) { return times(new Rational(b.d, b.n)); }
        
        @Override
        public boolean equals(Object that) {
            if (this == that) return true;
            if (that == null) return false;
            if (getClass() != that.getClass()) return false;
            Rational that_rational = (Rational) that;
            return n == that_rational.n && d == that_rational.d;
        }
        
        @Override
        public String toString() {
            return d == 1 ? Long.toString(n) : String.format("%d/%d", n, d);
        }
    }
    
    /* Tests */
    
    private static Rational readRational(String prompt) {
        if (!prompt.isEmpty()) { StdOut.println(prompt); }
        StdOut.print("Enter numerator and denominator: ");
        return new Rational(StdIn.readInt(), StdIn.readInt());
    }
    
    private static void __cli() {
        Map<String, BinaryOperator<Rational>> ops = new HashMap<>();
        ops.put("+", (a, b) -> a.plus(b));
        ops.put("-", (a, b) -> a.minus(b));
        ops.put("*", (a, b) -> a.times(b));
        ops.put("/", (a, b) -> a.dividedBy(b));
        
        while (true) {
            Rational a = readRational("== Enter a ==");
            Rational b = readRational("== Enter b ==");
            StdOut.print("Pick operation:\n"
                       + "\t+) a+b\n\t-) a-b\n\t*) a*b\n\t/) a/b\n\t=)a == b\nOp:");
            String op = StdIn.readString();
            switch (op) {
            case "+": case "-": case "*": case "/":
                StdOut.println(ops.get(op).apply(a, b)); break;
            case "=": StdOut.println(a.equals(b)); break;
            default: StdOut.println("Unknown operation"); break;
            }
        }
    }
    
    public static void main(String[] args) {
        __cli();
    }
}
