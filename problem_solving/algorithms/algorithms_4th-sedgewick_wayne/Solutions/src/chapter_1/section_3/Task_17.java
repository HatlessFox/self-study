package chapter_1.section_3;

import java.util.Arrays;

import chapter_1.section_2.Task_19.Transaction;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;;

/*
 * Task 17
 * 
 * Do exercise 1.3.16 for Transaction
 */
public class Task_17 {

    public static Transaction[] readAllTransactions(In in) {
        Queue<Transaction> buffer = new Queue<>();
        while (!in.isEmpty()) {
            String client = in.readString(),
                   raw_date = in.readString(),
                   raw_amount = in.readString();
            buffer.enqueue(new Transaction(client + " " + raw_date + " " + raw_amount));
        }
        
        Transaction[] transactions = new Transaction[buffer.size()];
        for (int i = 0; i < transactions.length; i++) {
            transactions[i] = buffer.dequeue();
        }
        return transactions;
    }
    
    public static void main(String[] args) {
        Arrays.stream(readAllTransactions(new In())).forEach(t -> StdOut.println(t + "; "));
    }
}
