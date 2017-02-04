package chapter_3.section_1;

import chapter_3.common.OST;
import chapter_3.common.Utils;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 3.1.4
 * 
 * Develop Time and Event ADTs that allow processing of data as in the example illustrated
 * on page 367.
 */
public class Task_04 {

    public static class Time implements Comparable<Time> {
        private int h, m, s;
        public Time(int hrs, int mns, int scs) {
            h = hrs;
            m = mns;
            s = scs;
        }
        
        @Override
        public int compareTo(Time t) {
            int h_diff = h - t.h;
            if (h_diff != 0) { return h_diff; }
            int m_diff = m - t.m;
            if (m_diff != 0) { return m_diff; }
            return s - t.s;
        }
        
        @Override
        public String toString() {
            return String.format("%02d:%02d:%02d", h, m, s);
        }
    }
    
    public static class Event implements Comparable<Event> {
        private Time t;
        
        public Event(int h, int m, int s) {
            t = new Time(h, m, s);
        }
        @Override
        public int compareTo(Event that) { return t.compareTo(that.t); }
        
        @Override
        public String toString() { return t.toString(); }
    }
    
    //-- Tests
    
    private static void init(OST<Event, String> ost) {
        ost.put(new Event(9, 0, 13), "Houston");
        ost.put(new Event(9, 3, 13), "Chicago");
        ost.put(new Event(9, 14, 25), "Phoenix");
        ost.put(new Event(9, 21, 5), "Chicago");
        ost.put(new Event(9, 25, 52), "Chicago");
        ost.put(new Event(9, 37, 44), "Phoenix");
        
        ost.put(new Event(9, 0, 3), "Phoenix");
        ost.put(new Event(9, 1, 10), "Houston");
        ost.put(new Event(9, 10, 25), "Seattle");
        ost.put(new Event(9, 19, 46), "Chicago");
        ost.put(new Event(9, 22, 54), "Seattle");
        ost.put(new Event(9, 36, 14), "Seattle");
        
        ost.put(new Event(9, 35, 21), "Chicago");
        ost.put(new Event(9, 22, 43), "Seattle");
        ost.put(new Event(9, 19, 32), "Chicago");
        ost.put(new Event(9, 10, 11), "Seattle");
        ost.put(new Event(9, 00, 59), "Chicago");
        ost.put(new Event(9, 00, 00), "Chicago");
    }
    
    public static void main(String[] args) {
        OST<Event, String> ost = new Task_03.OrderedSequentialSearchST<>();
        init(ost);
        StdOut.println("== DUMP ==");
        Utils.dumpST(ost, "\n");
        
        StdOut.printf("min(): %s\n", ost.min());
        StdOut.printf("get(09:00:13): %s\n", ost.get(new Event(9, 0, 13)));
        StdOut.printf("floor(09:05:00): %s\n", ost.floor(new Event(9, 5, 0)));
        StdOut.printf("select(7): %s\n", ost.select(7));
        StdOut.printf("ceiling(09:30:00): %s\n", ost.ceiling(new Event(9, 35, 0)));
        StdOut.printf("max(): %s\n", ost.max());
        StdOut.printf("size(09:15:00, 09:25:00): %s\n", ost.size(new Event(9, 15, 0),
                                                                 new Event(9, 25, 0)));
        StdOut.printf("rank(09:10:25): %s\n", ost.rank(new Event(9, 10, 25)));
        StdOut.println("== DUMP Inverval ==");
        for (Event k : ost.keys(new Event(9, 15, 0), new Event(9, 25, 0))) {
            StdOut.printf("%s %s\n", k, ost.get(k));
         }
    }
}
