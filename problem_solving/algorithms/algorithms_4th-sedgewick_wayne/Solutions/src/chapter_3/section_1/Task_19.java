package chapter_3.section_1;

import java.util.LinkedList;
import java.util.Queue;

import chapter_3.common.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 3.1.16
 * 
 * Modify FrequencyCounter to print all of the values having the highest frequency of occurrence,
 * not just one of them. _Hint_: Use a queue.
 */
public class Task_19 {

    private static final String DFLT_MOST_FREQ_W = "";
    
    public static void main(String[] args) {
        int cutoff = 0;
        if (1 <= args.length) {
            cutoff = Integer.parseInt(args[0]);
        }

        ST<String, Integer> word_stat = new Task_02.ArrayST<>(100);
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < cutoff) { continue; }

            Integer word_occup = word_stat.get(word);
            word_stat.put(word, 1 + (word_occup == null ? 0 : word_occup));
        }
        
        
        word_stat.put(DFLT_MOST_FREQ_W, 0);
        Queue<String> most_frequent_words = new LinkedList<>();
        most_frequent_words.add(DFLT_MOST_FREQ_W);
        for (String w : word_stat.keys()) {
            int occ_diff = word_stat.get(w) - word_stat.get(most_frequent_words.peek()); 
            if (occ_diff < 0) { continue; }
            if (0 < occ_diff) { most_frequent_words.clear(); }
            most_frequent_words.add(w);
        }
        
        StdOut.println("Most frequent words:");
        for (String w : most_frequent_words) { StdOut.println(w); }
    }

}
