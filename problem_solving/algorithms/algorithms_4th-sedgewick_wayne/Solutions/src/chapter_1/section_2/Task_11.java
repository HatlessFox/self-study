package chapter_1.section_2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.2.12
 * 
 * Develop an implementation SmartDate of our Date API that raises an exception
 * if the date is not legal.
 */
public class Task_11 {

    private static class Date {
        private boolean isLeap(int y) {
            return ((y % 4 == 0) && (y % 100 != 0)) || (y% 400 == 0);
        }
        private static final int[] MONTH_DAY = new int[]{
                -1, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        };
        
        private int daysInMonth(int m, int y) {
            return MONTH_DAY[m] + ((m == 2 && isLeap(y)) ? 1 : 0);
        }
        
        private boolean isDateValid(int d, int m, int y) {
            if (y < 0) return false;
            if (m < 1 || 12 < m) return false;
            if (d < 1 || daysInMonth(m, y) < d) return false;
            return true;
        }
        
        
        public Date(int m, int d, int y) {
            if (!isDateValid(d, m, y)) {
                throw new IllegalArgumentException();
            }
        }
    }
    
    public static void main(String[] args) {
        StdOut.print("Enter date (m d y): ");
        try {
            new Date(StdIn.readInt(), StdIn.readInt(), StdIn.readInt());
            StdOut.println("Date is valid");
        } catch (Exception e) {
            StdOut.println("Date is not valid");
        }
    }
}
