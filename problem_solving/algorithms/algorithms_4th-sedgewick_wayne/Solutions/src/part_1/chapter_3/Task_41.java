package part_1.chapter_3;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import utils.CliUtils;

/*
 * Task 1.3.41: Copy a queue.
 * 
 * Create a new constructor so that
 *   Queue<T> r = new Queue<>(q);
 * makes r a reference to a new and independent copy of the queue q. You should be able to enqueue 
 * and dequeue from either q or r without influencing the other.
 * _Hint_: Delete all of the elements from q and add these elements to both q and r.
 */
public class Task_41 {
    
    public static <T> Queue<T> copyQueue(Queue<T> q) {
        Queue<T> copy = new Queue<T>();
        q.forEach(e -> copy.enqueue(e));
        return copy;
    }
    
    public static void main(String[] args) {
        Queue<String> q = new Queue<>(), copy = copyQueue(q);
        while (true) {
            StdOut.print("Op (aq/c <e> -- add to q/c; dc/q -- dequeue q/c; c -- update copy): ");
            String op = StdIn.readString();
            switch (op.charAt(0)) {
            case 'a': {
                Queue<String> target = op.charAt(1) == 'q' ? q : copy;
                target.enqueue(StdIn.readString());
                break;
            }
            case 'd': {
                Queue<String> target = op.charAt(1) == 'q' ? q : copy;
                StdOut.printf("Dequeued: %s\n", target.dequeue());
                break;
            }
            case 'c':
                copy = copyQueue(q);
                break;
            }
            StdOut.printf("Orig: %s\nCopy: %s\n",
                    CliUtils.iterableToString(q), CliUtils.iterableToString(copy));
        }
    }
}
