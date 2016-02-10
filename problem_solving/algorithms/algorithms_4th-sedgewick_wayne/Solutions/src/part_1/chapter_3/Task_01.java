package part_1.chapter_3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.3.1
 * 
 * Add a method isFull() to FixedCapacityStackOfStrings.
 */
public class Task_01 {

    private static class FixedCapacityStackOfStrings {
        private String[] a;
        private int sz;
        
        public FixedCapacityStackOfStrings(int cap) {
            a = new String[cap];
        }
        
        public boolean isFull() { return size() == a.length; }
        public int size() { return sz; }
        
        public void push(String item) { a[sz++] = item; }
        public String pop() { return a[--sz]; }
    }
    
    public static void main(String[] args) {
        StdOut.print("Enter stack size: ");
        FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(StdIn.readInt());
        while (true) {
            StdOut.print("\n(a) push\n(p) pop\n(f) fullness check\nEnter op: ");
            switch (StdIn.readString()) {
            case "a":
                StdOut.print("Enter String to be pushed: ");
                stack.push(StdIn.readString());
                break;
            case "p":
                StdOut.printf("Popped string: %s\n", stack.pop());
                break;
            case "f":
                StdOut.printf("Is full: %b\n", stack.isFull());
                break;
            default:
                StdOut.println("Unknown command");
            }
        }
    }
}
