package chapter_3.section_5;

import java.util.HashMap;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 3.5.16
 * 
 * Add a method sum() to SparseVector that takes a SparseVector as argument and
 * returns a SparseVector that is the term-by-term sum of this vector and the argument vector.
 * _Note_: You need delete() (and special attention to precision) to handle the case
 *         where an entry becomes 0.
 */
public class Exercise_16 {

    public static class SparseVector {
        private HashMap<Integer, Double> payload = new HashMap<>();
        
        public int size() { return payload.size(); }
        public void set(int i, double v) { payload.put(i, v); }
        public double get(int i) { return payload.containsKey(i) ? payload.get(i) : 0.0; }
        public void add(SparseVector v) {
            for (Integer ind : v.payload.keySet()) {
                double add_res = v.get(ind) + get(ind);
                if (Double.compare(add_res, 0.0) == 0) {
                    payload.remove(ind);
                } else {
                    payload.put(ind, add_res);
                }
            }
        }
    }
    
    private static SparseVector readVector() {
        StdOut.println("> Enter vector");
        SparseVector sv = new SparseVector();
        while (true) {
            String cmd = StdIn.readLine();
            if (cmd.equals("DONE")) { break; }
            String tokens[] = cmd.split(" ");
            sv.set(Integer.parseInt(tokens[0]), Double.parseDouble(tokens[1]));
        }
        return sv;
    }
    
    public static void main(String[] args) {
        SparseVector sv = readVector();
        sv.add(readVector());
        
        StdOut.printf("Size: %d\n", sv.size());
        for (int i = 0; i < 7; ++i) {
            StdOut.print(sv.get(i) + " ");
        }
        StdOut.println();
    }
}
