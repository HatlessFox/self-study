package chapter_2.section_5;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 2.5.10
 * 
 * Create a data type Version that represents a software version number, such as 115.1.1, 115.10.1,
 * 115.10.2. Implement the Comparable interface so that 115.1.1 is less than 115.10.1, and so forth.
 */
public class Task_10 {
    
    public static class Version implements Comparable<Version>{
        private int min, maj, main;
        
        @Override public int compareTo(Version that) {
            if (main != that.main) { return main - that.main; }
            if (maj != that.maj) { return maj - that.maj; }
            return min - that.min;
        }
        @Override public String toString() { return String.format("%d.%d.%d", main, maj, min); }
        
        public static Version fromString(String v) {
            String[] vs = v.split("\\.");
            Version r = new Version();
            r.main = Integer.parseInt(vs[0]);
            r.maj = Integer.parseInt(vs[1]);
            r.min = Integer.parseInt(vs[2]);
            return r;
        }
    }
    
    public static void main(String[] args) {
        String[] raw_data = StdIn.readAllStrings();
        
        Version vs[] = new Version[raw_data.length];
        for (int i = 0; i < raw_data.length; ++i) {
            vs[i] = Version.fromString(raw_data[i]);
        }
        Arrays.sort(vs);
        
        for (Version v :  vs) { StdOut.println(v); }
    }
}
