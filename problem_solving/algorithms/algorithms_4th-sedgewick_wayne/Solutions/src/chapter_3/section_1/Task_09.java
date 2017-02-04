package chapter_3.section_1;

import chapter_3.common.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/* 
 * Task 3.1.9
 * 
 * Add code to FrequencyCounter to keep track of the last call to put(). Print the last word
 * inserted and the number of words that were processed in the input stream prior to this insertion.
 * Run your program for tale.txt with length cutoffs 1, 8, 10.
 */
public class Task_09 {
    // cat ~/Downloads/tale.txt | java -cp ../external/algs4.jar:. chapter_3.section_1.Task_09
    public static void main(String[] args) {
        int cutoff = 0;
        if (1 <= args.length) {
            cutoff = Integer.parseInt(args[0]);
        }

        ST<String, Integer> word_stat = new Task_02.ArrayST<>(100);
        int rejected_words_nm = 0;
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < cutoff) {
                rejected_words_nm++;
                continue;
            }
            StdOut.printf("%s %d\n", word, rejected_words_nm);
            
            rejected_words_nm = 0;
            Integer word_occup = word_stat.get(word);
            word_stat.put(word, 1 + (word_occup == null ? 0 : word_occup));
        }
        
        String most_frequent_word = "";
        word_stat.put(most_frequent_word, 0);
        for (String w : word_stat.keys()) {
            if (word_stat.get(w) <= word_stat.get(most_frequent_word)) { continue; }
            most_frequent_word = w;
        }
        StdOut.printf("Most frequent word: %s\n", most_frequent_word);
    }

}
