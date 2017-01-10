package chapter_2.section_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 2.5.8
 * 
 * Write a program Frequency that reads strings from standard input and prints the number of times
 * each string occurs, in descending order of frequency.
 */
public class Task_08 {

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
    
    public static class StringRecord implements Comparable<StringRecord> {
        int nm;
        String str;
        
        public StringRecord(String s) {
            str = s;
            nm = 1;
        }
        
        @Override
        public int compareTo(StringRecord that) {
            return that.nm - nm; // descending order
        }
        
        @Override
        public String toString() {
            return String.format("%d %s", nm, str);
        }
    }
    
    public static StringRecord[] toFreqs(String[] data) {
        if (data.length == 0) { return new StringRecord[0]; }
        List<StringRecord> sr_holder = new ArrayList<>();
        
        StringRecord curr_sr = new StringRecord(data[0]);
        for (int i = 1; i < data.length; ++i) {
            if (data[i].equals(curr_sr.str)) {
                curr_sr.nm++;
            } else {
                sr_holder.add(curr_sr);
                curr_sr = new StringRecord(data[i]);
            }
        }
        sr_holder.add(curr_sr);
        
        StringRecord[] srs = new StringRecord[sr_holder.size()];
        return sr_holder.toArray(srs);
    }
    
    public static void main(String[] args) {
        String[] words = readStrings();
        Arrays.sort(words);
        
        StringRecord[] stat = toFreqs(words);
        Arrays.sort(stat);
        Arrays.stream(stat).forEach(sr -> { StdOut.println(sr); });
    }
}
