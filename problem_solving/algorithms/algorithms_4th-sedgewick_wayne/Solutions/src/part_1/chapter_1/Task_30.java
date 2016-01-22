package part_1.chapter_1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Task_30 {

    private static boolean[][] genPrimMtx(int n) {
        boolean[][] prim_mtx = new boolean[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                prim_mtx[i][j] = Task_24.gcd(i, j) == 1;
            }
        }
        return prim_mtx;
    }
    
    public static void main(String[] args) {
        StdOut.print("Enter N: ");
        Task_11.printBooleanMtx(genPrimMtx(StdIn.readInt()));
    }
    
}
