package part_1.chapter_3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import part_1.chapter_3.common.CommonStack;

/*
 * Task 1.3.7
 * 
 * Add a method peek() to Stack that returns the most recently inserted item on the stack
 * (w/o popping it).
 */
public class Task_07 {

    public static class PeekableStack<Item> extends CommonStack<Item> {
        public Item peek() { return first.item; }
    }
    
    public static void main(String[] args) {
        PeekableStack<String> ps = new PeekableStack<>();
        while (true) {
            StdOut.print("Enter operation (push, peek, print, pop): ");
            switch (StdIn.readString()) {
            case "push" : ps.push(StdIn.readString()); break;
            case "peek" : StdOut.println(ps.peek()); break;
            case "print":
                for (String s : ps) {
                    StdOut.print(s + " ");
                }
                StdOut.println();
                break;
            case "pop"  : StdOut.println(ps.pop()); break;
            }
        }
    }
}
