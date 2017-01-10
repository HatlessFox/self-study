package chapter_2.section_5;

import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 2.5.4
 * 
 * Implement a method String[] dedup(String[] a) that returns the objects in a[] in sorted order,
 * with duplicates removed.
 */
public class Task_04 {

    public static String[] readStrings() {
        ArrayList<String> words = new ArrayList<>();
        while (!StdIn.isEmpty()) {
            words.add(StdIn.readString());
            if (words.get(words.size() - 1).equals("<exit>")) {
                break;
            }
        }
        String[] words_arr = new String[words.size()];
        return words.toArray(words_arr);
    }
    
    public static String[] dedup(String[] data) {
        Arrays.sort(data);
        int ins_i = 1;
        for (int i = 1; i < data.length; ++i) {
            if (data[i-1].equals(data[i])) { continue; }
            data[ins_i++] = data[i];
        }
        String result[] = new String[ins_i];
        System.arraycopy(data, 0, result, 0, ins_i);
        
        return result;
    }
    
    public static void main(String[] args) {
        Arrays.stream(dedup(readStrings())).forEach(s -> { StdOut.println(s); });
    }
}
