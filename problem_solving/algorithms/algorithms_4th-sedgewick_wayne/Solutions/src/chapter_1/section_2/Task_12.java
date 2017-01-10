package chapter_1.section_2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.2.12
 * 
 * Add a method dayOfTheWeek() to SmartDate that returns a String value giving the appropriate day 
 * of the week for the date. You may assume that the date is in the 21st century.
 */
public class Task_12 {

    private static class SmartDate {
        private int month, day, year;
        private static final int DAYS_OFFSET = 4;
        
        private static final int START_YEAR = 2000;
        private static final int DAYS_IN_YEAR = 365;
        private static final String[] DAYS_OF_WEEK = new String[] {
                "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
        };
        private static final int[] DAYS_IN_MONTH = new int[] {
                -1, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        };
        
        public SmartDate(int m, int d, int y) {
            if (y < START_YEAR) { throw new IllegalArgumentException(); }
            month = m;
            day = d;
            year = y;
        }
        
        private boolean isLeap(int y) {
            return ((y % 4 == 0) && (y % 100 != 0)) || (y % 400 == 0);
        }
        
        private int daysPassedTilYear(int year) {
            int total = 0;
            for (int y = START_YEAR; y < year; y++) {
                total += DAYS_IN_YEAR + (isLeap(y) ? 1 : 0);
            }
            return total;
        }
        
        private int daysPassedTilMonth(int month) {
            int total = 0;
            for (int m = 1; m < month; m++) {
                total += DAYS_IN_MONTH[m] + (m == 2 && isLeap(year) ? 1 : 0);
            }
            return total;
        }
        
        
        private int daysPassed() {
            return DAYS_OFFSET + day + daysPassedTilYear(year) + daysPassedTilMonth(month);
        }
        
        public String dayOfWeek() {
            return DAYS_OF_WEEK[daysPassed() % 7];
        }
    }
    
    public static void main(String[] args) {
        StdOut.print("Enter the date (m d y): ");
        SmartDate sd = new SmartDate(StdIn.readInt(), StdIn.readInt(), StdIn.readInt());
        StdOut.printf("Day of week: %s\n", sd.dayOfWeek());
    }
}
