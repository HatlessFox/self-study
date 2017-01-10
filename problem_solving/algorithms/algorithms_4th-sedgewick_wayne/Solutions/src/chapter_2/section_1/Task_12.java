package chapter_2.section_1;

import java.util.Random;

import chapter_2.section_1.Task_11.Shellsort;

/*
 * Task 2.1.12
 * 
 * Instrument shellsort to print the number of compares divided by the array size for each
 * increment. Write a test client that tests the hypothesis that this number is a small constant,
 * by sorting arrays of random Double values, using array sizes that are increasing powers of 10,
 * starting at 100.
 */
public class Task_12 {

    private static Double[] gen_rnd_doubles(int sz) {
        Double data[] = new Double[sz];
        Random rnd = new Random();
        
        for (int i = 0; i < sz; ++i) {
            data[i] = rnd.nextDouble();
        }
        return data;
    }
    
    public static void main(String[] args) {
        int sz = 100;
        
        while (true) {
            Shellsort sh_sort = new Shellsort();
            sh_sort.sort(gen_rnd_doubles(sz));
            sz *= 10;
        }
    }
}
