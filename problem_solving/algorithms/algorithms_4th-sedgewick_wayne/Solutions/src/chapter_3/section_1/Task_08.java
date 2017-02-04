package chapter_3.section_1;

import chapter_3.common.FrequencyCounter;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 3.1.8
 * 
 * What is the most frequently used word of ten letters or more in Tale of Two Cities?
 */
public class Task_08 {
    
    public static void main(String[] args) {
        FrequencyCounter fc = new FrequencyCounter(new Task_02.ArrayST<>(10), 5);
        while (!StdIn.isEmpty()) {
            fc.addWord(StdIn.readString());
        }
        StdOut.println(fc.mostFrequentWord());
    }

}
