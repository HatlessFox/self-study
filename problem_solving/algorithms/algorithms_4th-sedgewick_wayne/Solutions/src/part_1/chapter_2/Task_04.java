package part_1.chapter_2;

import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.2.4
 * 
 * What does the following code fragment print?
 */
public class Task_04 {

    public static void main(String[] args) {
        String string1 = "hello";
        String string2 = string1;
        string1 = "world";
        StdOut.println(string1);
        StdOut.println(string2);
    }
}
