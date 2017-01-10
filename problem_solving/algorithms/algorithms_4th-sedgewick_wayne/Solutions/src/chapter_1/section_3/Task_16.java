package chapter_1.section_3;

import java.util.Arrays;

import chapter_1.section_2.Task_19.Date;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.3.16
 * 
 * Using readAllInts() on p. 126 as a model, write a static method readAllDates() for Date that 
 * reads dates from standard input in the format specified in the table on p. 119 and returns
 * an array containing them.
 */
public class Task_16 {
    
    public static Date[] readAllDates(In in) {
        Queue<Date> buffer = new Queue<>();
        while (!in.isEmpty()) {
            buffer.enqueue(new Date(in.readString()));
        }
        
        Date[] dates = new Date[buffer.size()];
        for (int i = 0; i < dates.length; i++) {
            dates[i] = buffer.dequeue();
        }
        return dates;
    }
    
    public static void main(String[] args) {
        Arrays.stream(readAllDates(new In())).forEach(d -> StdOut.println(d + "; "));
    }
}
