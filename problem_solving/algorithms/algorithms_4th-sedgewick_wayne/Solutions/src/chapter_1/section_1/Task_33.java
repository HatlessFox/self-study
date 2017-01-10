package chapter_1.section_1;

import java.io.IOException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.1.13: Matrix library
 * 
 * Write a library Matrix that implements the following API: dot, mult, traspose
 */
public class Task_33 {

    public static class Matrix {
        public static double dot(double[] x, double[] y) {
            if (x.length != y.length) {
                throw new IllegalArgumentException("Vector lengthes are not the same");
            }
            
            double dot_prod = 0;
            for (int i = 0; i < x.length; i++) {
                dot_prod += x[i] * y[i];
            }
            return dot_prod;
        }
        
        public static double[][] mult(double[][] a, double[][] b) {
            if (a[0].length != b.length) {
                throw new IllegalArgumentException("Row and column numbers are not the same");
            }
            
            double[][] prod = new double[a.length][b[0].length];
            for (int i = 0; i < a.length; i++) {     // row in a as coefficients
                for (int j = 0; j < b.length; j++) { // row in b as a basis
                    for (int k = 0; k < b[0].length; k++) {
                        prod[i][k] += a[i][j] * b[j][k]; // add b[j] row multiplied by coefficient
                    }
                }
            }
            return prod;
        }
        
        public static double[][] transpose(double[][] a) {
            int n = a.length, m = a[0].length;
            double[][] trans = new double[m][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    trans[j][i] = a[i][j];
                }
            }
            return trans;
        }
        
        public static double[] mult(double[][] a, double[] x) {
            // reuse matrix-matrix multiplication by x wrapping
            return transpose(mult(a, transpose(new double[][]{x})))[0];
        }
        
        public static double[] mult(double[] y, double[][] b) {
            // reuse matrix-matrix multiplication by y wrapping
            return mult(new double[][]{y}, b)[0];
        }
    }
    
    /* Command Line Interface */
    
    private static double[] __readVector(int n) {
        StdOut.printf("{->} Enter vector data (%d): ", n);
        double[] v = new double[n];
        for (int i = 0; i < n; i++) {
            v[i] = StdIn.readDouble();
        }
        return v;
    }
    
    private static double[] __readVector() {
        StdOut.print("{->} Enter vector length: ");
        return __readVector(StdIn.readInt());
    }
    
    private static double[][] __readMatrix() {
        StdOut.print("{->} Enter number of rows and cols: ");
        int n = StdIn.readInt(), m = StdIn.readInt();
        double[][] mtx = new double[n][m];
        
        for (int i = 0; i < n; i++) {
            StdOut.printf("{->} Row %d (%d elems): ", i, m);
            for (int j = 0; j < m; j++) {
                mtx[i][j] = StdIn.readDouble();
            }
        }
        return mtx;
    }
    
    private static void __printVector(double[] v) {
        StdOut.print("[ ");
        for (double e : v) { StdOut.print(e + " "); }
        StdOut.println("]");
    }
    
    private static void __printMatrix(double[][] mtx) {
        StdOut.println("[");
        for (double[] row : mtx) { __printVector(row); }
        StdOut.println("]");
    }
    
    private static void __cliDot() {
        StdOut.print("{->} Enter vector length: ");
        int n = StdIn.readInt();
        StdOut.println("\n{->} Enter vector x");
        double[] v1 = __readVector(n);
        StdOut.println("\n{->} Enter vector y");
        double[] v2 = __readVector(n);
        StdOut.printf("{<-} x*y: %f\n", Matrix.dot(v1, v2));
    }
    
    private static void __cliMtxMtxMult() {
        StdOut.println("\n{->} Read matrix A");
        double[][] mtx_a = __readMatrix();
        StdOut.println("\n{->} Read matrix B");
        double[][] mtx_b = __readMatrix();
        StdOut.println("{<-} A*B:");
        __printMatrix(Matrix.mult(mtx_a, mtx_b));
    }
    
    private static void __cliMtxVecMult() {
        StdOut.println("\n{->} Read matrix A");
        double[][] mtx_a = __readMatrix();
        StdOut.println("\n{->} Read vector x");
        double[] x = __readVector();
        StdOut.println("{<-} A*x:");
        __printVector(Matrix.mult(mtx_a, x));
    }
    
    private static void __cliVecMtxMult() {
        StdOut.println("\n{->} Read vector y");
        double[] y = __readVector();
        StdOut.println("\n{->} Read matrix B");
        double[][] mtx_b = __readMatrix();
        StdOut.println("{<-} y*B:");
        __printVector(Matrix.mult(y, mtx_b));
    }
    
    private static void __cliTranspose() {
        StdOut.println("\n{->} Read matrix A");
        double[][] mtx_a = __readMatrix();
        StdOut.println("{<-} A^T:");
        __printMatrix(Matrix.transpose(mtx_a));
    }
    
    private static void __waitForCarriageReturn() {
        try {
            System.in.read();
        } catch (IOException e) {
            // ignore
        }
    }
    
    private static void __cli() {
        while (true) {
            StdOut.println("== Available Operations ==");
            StdOut.println("1) dot product");
            StdOut.println("2) matrix-matrix product");
            StdOut.println("3) matrix-vector product");
            StdOut.println("4) vector-matrix product");
            StdOut.println("5) matrix transpose");
            StdOut.println("6) exit");
            StdOut.print("\n{->} Pick operation: ");
            
            switch (StdIn.readInt()) {
                case 1:
                    __cliDot();
                    break;
                case 2:
                    __cliMtxMtxMult();
                    break;
                case 3:
                    __cliMtxVecMult();
                    break;
                case 4:
                    __cliVecMtxMult();
                    break;
                case 5:
                    __cliTranspose();
                    break;
                case 6:
                    StdOut.println("Bye");
                    return;
                default:
                    StdOut.println("Error: Unknown command");
            }
            __waitForCarriageReturn();
        }
    }
    
    
    public static void main(String[] args) {
        __cli();
    }
}
