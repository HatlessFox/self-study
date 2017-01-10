package chapter_1.section_3;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/* 
 * Task 1.3.37: Josephus problem.
 * 
 * In the Josephus problem from antiquity, N people ale in dire straits and agree to the following 
 * strategy to reduce the population. They arrange themselves in a circle (at positions numbered 
 * from 0 to N-1) and proceed around the circle, eliminating every Mth person until only one person 
 * is left. Legend has it that Josephus figured out where to sit to avoid being eliminated. Write a 
 * Queue client Josephus that takes M and N from the command line and prints out the order in which 
 * people are eliminated (and thus would show Josephus where to sit in the circle).
 * 
 * Example:
 * > java Josephus 2 7
 * 1 3 5 0 4 2 6
 */
public class Task_37 {

    public static void main(String[] args) {
        if (args.length < 2) {
            StdOut.println("Args: <M> <N>");
            System.exit(-1);
        }
        
        int m = Integer.parseInt(args[0]), n = Integer.parseInt(args[1]);
        Queue<Integer> people = new Queue<>();
        for (int i = 0; i < n; i++) { people.enqueue(i); }
        
        while (0 < people.size()) {
            int i = m - 1;
            while (0 < i--) {
                people.enqueue(people.dequeue());
            }
            StdOut.printf("%d ", people.dequeue());
        }
        StdOut.println();
    }
}
