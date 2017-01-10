package chapter_1.section_2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.2.19: Parsing
 * 
 * Develop the parse constructors for your Date and Transaction implementations of exercise 1.2.13 
 * that take a single String argument to specify the initialization values, using the formats given 
 * in the table below.
 * 
 * +-------------+-----------------------------------+------------------------+
 * |    type     |               format              |          example       |
 * +-------------+-----------------------------------+------------------------+
 * | Date        | integers separated by slashes     | 5/22/1939              |
 * | Transaction | customer, date, amount sep. by ws | Turing 5/22/1939 11.99 |
 * +-------------+-----------------------------------+------------------------+
 */
public class Task_19 {

    public static class Date {
        private int day, month, year;
        
        public Date(String date) {
            String[] tokens = date.split("/");
            if (tokens.length != 3) {
                throw new IllegalArgumentException(String.format("Date can't be parsed: %s", date));
            }
            try {
                this.month = Integer.parseInt(tokens[0]);
                this.day = Integer.parseInt(tokens[1]);
                this.year = Integer.parseInt(tokens[2]);
            } catch (Exception e) {
                throw new IllegalArgumentException(String.format("Date can't be parsed: %s", date));
            }
        }
        
        @Override
        public String toString() { return String.format("%d/%d/%d", month, day, year); }
    }

    public static class Transaction {
        private Date date;
        private String user;
        private double amount;
        
        public Transaction(String t) {
            String[] tokens = t.split("\\s");
            if (tokens.length != 3) {
                throw new IllegalArgumentException(
                        String.format("Transaction can't be parsed: %s", t));
            }
            try {
                user = tokens[0];
                date = new Date(tokens[1]);
                amount = Double.parseDouble(tokens[2]);
            } catch (Exception e) {
                throw new IllegalArgumentException(
                        String.format("Transaction can't be parsed: %s", t));
            }
        }
        
        @Override
        public String toString() { return String.format("%s %s %.2f", user, date, amount); }
    }

    public static void main(String[] args) {
        while (true) {
            StdOut.print("What you want to parse (d, t): ");
            String type = StdIn.readString();
            if (!type.equals("d") && !type.equals("t")) {
                StdOut.println("Unknown data format.");
                continue;
            }
            StdOut.print("Enter raw value: ");
            StringBuilder raw = new StringBuilder();
            for (int str_cnt = type.equals("d") ? 1 : 3; 0 < str_cnt; str_cnt--) {
                raw.append(StdIn.readString());
                raw.append(" ");
            }
            
            try {
                StdOut.printf("[Parsed] %s\n\n", type.equals("d") ?
                        new Date(raw.toString()) : new Transaction(raw.toString()));
            } catch (IllegalArgumentException e) {
                StdOut.printf("[Error] %s\n\n", e.getMessage());
            }
        }
    }
}
