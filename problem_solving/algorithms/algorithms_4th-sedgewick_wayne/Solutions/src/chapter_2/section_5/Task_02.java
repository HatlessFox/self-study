package chapter_2.section_5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 2.5.2
 * 
 * Write a program that reads a list of words from standard input and prints all two-word compound
 * words in the list. For example, if after, thought, and afterthought are in the list, then
 * afterthought is a compound word.
 */
public class Task_02 {

    public static ArrayList<String> readWords() {
        ArrayList<String> words = new ArrayList<>();
        while (!StdIn.isEmpty()) {
            words.add(StdIn.readString());
            if (words.get(words.size() - 1).equals("<exit>")) {
                break;
            }
        }
        return words;
    }
    
    public static boolean isEqualAt(String str, ArrayList<String> data, int i) {
        if (i < 0 || data.size() <= i) return false;
        return data.get(i).equals(str);
    }

    public static Set<String> findTwoCompoundWords(ArrayList<String> words) {
        Set<String> two_comp_words = new HashSet<>();
        Collections.sort(words);

        for (int prefix_i = 0, comp_testee_i = 0; prefix_i < words.size(); ++prefix_i) {
            String prefix = words.get(prefix_i);
            comp_testee_i = prefix_i;
            while (++comp_testee_i < words.size()) {
                String comp_testee = words.get(comp_testee_i);
                if (!comp_testee.startsWith(prefix)) { break; }
                String suffix = comp_testee.substring(prefix.length());
                int suffix_i = Collections.binarySearch(words, suffix);
                
                if (suffix_i < 0) { continue; }
                if (prefix_i == suffix_i &&
                    !isEqualAt(prefix, words, suffix_i - 1) &&
                    !isEqualAt(prefix, words, suffix_i + 1)) { continue; }
                two_comp_words.add(comp_testee);
            }
        }
        return two_comp_words;
    }
    
    public static void main(String[] args) {
        findTwoCompoundWords(readWords()).forEach((w) -> { StdOut.println(w); });
    }
}
