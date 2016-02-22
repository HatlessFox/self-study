package part_1.chapter_3;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.3.15
 * 
 * Write a Queue client that takes a command-line argument k and prints the kth from the last string
 * found on standard input (assuming that standard input has k or more strings)
 */
public class Task_15 {

    public static void main(String[] args) {
        if (args.length < 1) {
            StdOut.println("Args: <num of last lines>");
            System.exit(-1);
        }
        
        int lines_cnt = Integer.parseInt(args[0]);
        Queue<String> buffer = new Queue<>();
        while (!StdIn.isEmpty()) {
            buffer.enqueue(StdIn.readLine());
            while (lines_cnt < buffer.size()) {
                buffer.dequeue();
            }
        }
        
        buffer.forEach(line -> StdOut.println(line));
    }
    
}
