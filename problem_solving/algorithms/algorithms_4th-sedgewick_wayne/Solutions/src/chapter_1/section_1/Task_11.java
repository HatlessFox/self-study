package chapter_1.section_1;

import edu.princeton.cs.algs4.StdOut;

public class Task_11 {

    private static int ROWS = 11;
    private static int COLS = 11;
    
    public static void printBooleanMtx(boolean[][] mtx) {
        int row_ind_width = Integer.toString(mtx.length).length();
        int col_ind_width = Integer.toString(mtx[0].length).length();
        String column_header_format = String.format("%%%dd ", col_ind_width);
        
        // print column headers
        StdOut.printf(String.format("%%%ds", row_ind_width), "");
        for (int i = 0; i < mtx[0].length; i++) { StdOut.printf(column_header_format, i); };
        StdOut.println();
        
        for (int row_i = 0; row_i < mtx.length; row_i++) {
            StdOut.printf(String.format("%%%dd", row_ind_width), row_i);
            for (boolean e : mtx[row_i]) {
                StdOut.printf(String.format("%%%ds ", col_ind_width), e ? "*" : " ");
            }
            StdOut.println();
        }
    }
    
    public static void main(String[] args) {
        boolean[][] mtx = new boolean[ROWS][COLS];
        for (int i = 0; i < mtx.length; i++) {
            for (int j = 0; j < mtx[i].length; j++) {
                mtx[i][j] = Math.random() < 0.5;
            }
        }
        printBooleanMtx(mtx);
    }
}
