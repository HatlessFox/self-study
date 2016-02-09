package part_1.chapter_2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.2.13
 * 
 * Using our implementation of Date as a model (page 91), develop an implementation of Transaction.
 */
public class Task_13 {

    private static class Date {
        private final int month, day, year;
        
        public Date(int m, int d, int y) {
            month = m;
            day = d;
            year = y;
        }
        
        public int month() { return month; }
        public int day() { return day; }
        public int year() { return year; }
        @Override
        public String toString() { return month() + "/" + day() + "/" + year(); }
    }
    
    private static class Transaction {
        private final String user;
        private final Date date;
        private final double amount;
        
        public Transaction(String who, Date when, double amount) {
            this.user = who;
            this.date = when;
            this.amount = amount;
        }
        
        public String who() { return user; }
        public Date when() { return date; }
        public double amount() { return amount; }
        @Override
        public String toString() {
            return String.format("%s: %s -- %.2f", when(), who(), amount());
        }
        
    }
    
    public static void main(String[] args) {
        while (true) {
            StdOut.print("Enter user: ");
            String user = StdIn.readString();
            StdOut.print("Enter date: ");
            String[] raw_date = StdIn.readString().split("/");
            Date date = new Date(Integer.parseInt(raw_date[0]),
                                 Integer.parseInt(raw_date[1]),
                                 Integer.parseInt(raw_date[2]));
            StdOut.print("Enter amount: ");
            double amount = StdIn.readDouble();
            
            StdOut.println("<Transaction>");
            StdOut.println(new Transaction(user, date, amount));
            StdOut.println();
        }
    }
}
