package chapter_1.section_1;

import edu.princeton.cs.algs4.StdOut;

public class Task_13 {

    private static int M = 5;
    private static int N = 7;
    
    private static void printMtx(int[][] mtx) {
        for (int[] row : mtx) {
            for (int e : row) {
                StdOut.printf("%d ", e);
            }
            StdOut.println();
        }
    }
    
    private static void printTransposition(int[][] mtx) {
        for (int i = 0; i < mtx[0].length; i++) {
            for (int j = 0; j < mtx.length; j++) {
                StdOut.printf("%d ", mtx[j][i]);
            }
            StdOut.println();
        }
    }
    
    public static void main(String[] args) {
        int[][] mtx = new int[M][N];
        for (int[] row : mtx) {
            for (int i = 0; i < row.length; i++) {
                row[i] = (int)(Math.random() * 10);
            }
        }
        
        StdOut.println("[Original]");
        printMtx(mtx);
        StdOut.println("[Transposed]");
        printTransposition(mtx);
    }
}
