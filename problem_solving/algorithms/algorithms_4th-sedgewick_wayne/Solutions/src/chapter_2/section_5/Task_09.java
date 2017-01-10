package chapter_2.section_5;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 2.5.9
 * 
 * Develop a data type that allows you to write a client that can sort a file such as the one shown
 * at right (p. 354).
 */
public class Task_09 {

    public static class Record implements Comparable<Record>{
        private String date;
        private int amount;
        
        @Override public int compareTo(Record that) { return that.amount - amount; }
        @Override public String toString() { return String.format("%s %d", date, amount); }
        
        public static Record fromString(String date, String amount) {
            Record r = new Record();
            r.date = date;
            r.amount = Integer.parseInt(amount);
            return r;
        }
    }
    
    public static void main(String[] args) {
        String[] raw_data = StdIn.readAllStrings();
        
        Record rs[] = new Record[raw_data.length / 2];
        for (int i = 1; i < raw_data.length; i += 2) {
            rs[i / 2] = Record.fromString(raw_data[i - 1], raw_data[i]);
        }
        Arrays.sort(rs);
        
        for (Record r :  rs) { StdOut.println(r); }
    }
}
