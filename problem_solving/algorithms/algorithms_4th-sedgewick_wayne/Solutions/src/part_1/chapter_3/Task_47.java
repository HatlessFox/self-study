package part_1.chapter_3;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import part_1.chapter_3.Task_32.Steque;

/*
 * Task 1.3.47: Catenable queues, stacks, or steques.
 * 
 * Add an extra operation catenation that (destructively) concatenates two queues, stacks,
 * or steques (see Exercise 1.3.33 <p. 167>).
 */
public class Task_47 {

    public static <T> Consumer<T> getQueueConsumer(Queue<T> q) { return q::enqueue; }
    public static <T> Queue<T> concatQueues(Queue<T> q1, Queue<T> q2) {
        Queue<T> mix = new Queue<>();
        q1.forEach(mix::enqueue);
        q2.forEach(mix::enqueue);
        return mix;
    }
    
    public static <T> Consumer<T> getStackConsumer(Stack<T> s) { return s::push; }
    public static <T> Stack<T> concatStacks(Stack<T> s1, Stack<T> s2) {
        Stack<T> tmp = new Stack<>(), mix = new Stack<>();
        s1.forEach(tmp::push);
        s2.forEach(tmp::push);
        tmp.forEach(mix::push);
        return mix;
    }
    
    public static <T> Consumer<T> getStequeConsumer(Steque<T> s) { return s::enqueue; }
    public static <T> Steque<T> concatSteques(Steque<T> s1, Steque<T> s2) {
        Steque<T> mix = new Steque<>();
        s1.forEach(mix::enqueue);
        s2.forEach(mix::enqueue);
        return mix;
    }
    
    public static void populateContainer(Consumer<String> cons) {
        while (true) {
            String e = StdIn.readString();
            if (e.equals("done")) {
                break;
            }
            cons.accept(e);
        }
    }
    
    public static <T extends Iterable<String>>
        void runConcatCLI(BinaryOperator<T> mixer,
                          Supplier<T> cont_supp,
                          Function<T, Consumer<String>> consumer_supp) {
        T c1 = cont_supp.get(), c2 = cont_supp.get();
        StdOut.println("-> C1 enter data:");
        populateContainer(consumer_supp.apply(c1));
        
        StdOut.println("-> C2 enter data:");
        populateContainer((Consumer<String>)consumer_supp.apply(c1));
        
        
        StdOut.println("<- Concat results:");
        mixer.apply(c1, c2).forEach(e -> StdOut.print(e + " "));
        StdOut.println();
    }
    
    public static void main(String[] args) {
        while (true) {
            StdOut.print("Enter container type (sk - stack, qe - queue, se - steque): ");
            switch (StdIn.readString()) {
            case "sk":
                runConcatCLI(Task_47::concatStacks, Stack<String>::new,
                             Task_47::getStackConsumer);
                break;
            case "qe":
                runConcatCLI(Task_47::concatQueues, Queue<String>::new,
                             Task_47::getQueueConsumer);
                break;
            case "se":
                runConcatCLI(Task_47::concatSteques, Steque<String>::new,
                             Task_47::getStequeConsumer);
                break;
            default: continue;
            }
        }
    }
}
