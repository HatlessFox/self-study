package part_1.chapter_1;

import edu.princeton.cs.algs4.StdOut;

public class Task_27 {

    public static double binomial(int n, int k, double p) {
        double[][] cache = new double[n + 2][k + 2]; // extra row and column -- guards
        cache[1][1] = 1.0;
        
        for (int i = 1; i <= n+1; i++) {
            int start_col = (i == 1) ? 2 : 1; // the first column of the first row in already set 
            for (int j = start_col; j <= k+1; j++) {
                cache[i][j] = (1-p)*cache[i-1][j] + p*cache[i-1][j-1];
            }
        }
        
        return cache[n+1][k+1];
    }
    
    /* Testing */
    
    private static boolean __RUN_TESTS = true;
    private static int __test_i = 0;
    
    private static double binomial_conservative(int n, int k, double p) {
        if ((n == 0) && (k == 0)) return 1.0;
        if ((n < 0) || (k < 0)) return 0.0;
        return (1-p)*binomial_conservative(n-1, k, p) + p*binomial_conservative(n-1, k-1, p);
    }
    
    private static void __runTest(int n, int k, double p) {
        StdOut.printf("%d. %s\n",
                ++__test_i, binomial(n, k, p) == binomial_conservative(n, k, p) ? "OK" : "FAIL");
    }
    
    private static void __runTests() {
        __runTest(0, 0, 0.1);
        __runTest(0, 5, 0.1);
        __runTest(5, 0, 0.1);
        __runTest(5, 5, 0.3);
        __runTest(8, 4, 0.5);
        __runTest(4, 8, 3);
    }
    
    private static void __cli() {
        
    }
    
    public static void main(String[] args) {
        if (__RUN_TESTS) __runTests(); else __cli();
    }
    
}
