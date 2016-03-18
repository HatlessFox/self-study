package part_1.chapter_3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.3.44: Text editor buffer.
 * 
 * Develop a data type for a buffer in a text editor that implements the following API: <page 170>.
 * _Hint_: USe two stacks.
 */
public class Task_44 {

    public static class Buffer {
        private Stack<Character> lft = new Stack<>(), rht = new Stack<>();
        
        public Buffer() {}
        
        public void insert(char c) {
            lft.push(c);
        }
        public char get() { return lft.peek(); }
        public char delete() { return lft.pop(); }
        public int size() { return lft.size() + rht.size(); }
        
        private void moveElems(Stack<Character> dst, Stack<Character> src, int cnt) {
            while (0 < cnt-- && !src.isEmpty()) {
                dst.push(src.pop());
            }
        }
        
        public void left(int k) {
            moveElems(rht, lft, k);
        }
        
        public void right(int k) {
            moveElems(lft, rht, k);
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            lft.forEach(e -> sb.append(e).append(" "));
            sb.reverse().append(" | ");
            rht.forEach(e -> sb.append(e).append(" "));
            sb.append("[").append(size()).append("] ");
            return sb.toString().trim();
        }
    }
    
    public static void main(String[] args) {
        Buffer buf = new Buffer();
        while (true) {
            StdOut.print("Enter op (l <k>, r <k> - move left/right; i <c> - insert <c>; " +
                         "g/d - get/delete char): ");
            switch (StdIn.readString()) {
            case "l": buf.left(StdIn.readInt()); break;
            case "r": buf.right(StdIn.readInt()); break;
            case "g": StdOut.println(buf.get()); break;
            case "d": StdOut.println(buf.delete()); break;
            case "i": buf.insert(StdIn.readString().charAt(0)); break;
            }
            StdOut.println(buf);
            StdOut.println();
        }
    }
}
