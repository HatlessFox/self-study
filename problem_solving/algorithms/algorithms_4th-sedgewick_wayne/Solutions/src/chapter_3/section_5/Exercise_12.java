package chapter_3.section_5;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 3.5.12
 * 
 * Modify LookupCSV to associate with each key all values that appear in a key-value pair
 * with that key in the input (not just the most recent, as in the associative-array abstraction).
 */
public class Exercise_12 {

    public static void main(String[] args) {
        StdOut.print("Enter csv file name: ");
        In in = new In(StdIn.readString());
        
        StdOut.print("Enter key/value field indices: ");
        int key_field_i = StdIn.readInt(), val_field_i = StdIn.readInt();
        
        ST<String, Bag<String>> st = new ST<>();
        while (in.hasNextLine()) {
            String[] tokens = in.readLine().split(",");
            String key = tokens[key_field_i], val = tokens[val_field_i];
            if (!st.contains(key)) {
                st.put(key, new Bag<>());
            }
            st.get(key).add(val);
        }
        
        StdOut.println("CSV is loaded");
        while (!StdIn.isEmpty()) {
            String query = StdIn.readString();
            if (!st.contains(query)) { continue; }
            for (String val : st.get(query)) {
                StdOut.print(val + "; ");
            }
            StdOut.println();
        }
    }
}
