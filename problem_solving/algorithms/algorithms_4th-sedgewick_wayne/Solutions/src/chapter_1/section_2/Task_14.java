package chapter_1.section_2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.2.14
 * 
 * Using our implementation of equals() in Date (page 103), develop an implementation of equals() 
 * for Transaction.
 */
public class Task_14 {

    private static class Date {
        private final int month, day, year;
        public Date(int m, int d, int y) {
            month = m;
            day = d;
            year = y;
        }
        
        @Override
        public boolean equals(Object that) {
            if (this == that) return true;
            if (that == null) return false;
            if (this.getClass() != that.getClass()) return false;
            Date that_d = (Date) that;
            return month == that_d.month &&
                   day == that_d.day &&
                   year == that_d.year;
        }
        @Override
        public String toString() { return String.format("%d/%d/%d", month, day, year); }
    }
    private static class Transaction {
        private double amount;
        private String user;
        private Date date;
        
        public Transaction(double amount, Date date, String user) {
            this.amount = amount;
            this.user = user;
            this.date = date;
        }

        @Override
        public boolean equals(Object that) {
            if (this == that) return true;
            if (that == null) return false;
            if (this.getClass() != that.getClass()) return false;
            Transaction that_t = (Transaction) that;
            return amount == that_t.amount &&
                   user.equals(that_t.user) &&
                   date.equals(that_t.date);
        }
    }
    
    private static Transaction readTransaction(String prompt) {
        StdOut.println(prompt);
        
        StdOut.print("Enter user name: ");
        String uname = StdIn.readString();
        
        StdOut.print("Enter amount: ");
        double amount = StdIn.readDouble();
        
        StdOut.print("Enter date: ");
        String[] raw_date = StdIn.readString().split("/");
        Date date = new Date(Integer.parseInt(raw_date[0]),
                             Integer.parseInt(raw_date[1]),
                             Integer.parseInt(raw_date[2]));
        return new Transaction(amount, date, uname);
    }
    
    public static void main(String[] args) {
        Transaction base_t = readTransaction("== Enter the base transaction ==");
        while (true) {
            Transaction tmp_t = readTransaction("== Enter transaction for comparison ==");
            StdOut.printf("<Comparison result> %b\n\n", base_t.equals(tmp_t));
        }
    }
}
