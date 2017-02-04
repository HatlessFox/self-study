package chapter_3.section_1;

import chapter_3.common.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 3.1.1
 * 
 *  Write a client that creates a symbol table mapping letter grades to numerical scores, as in
 *  the table below, then reads from standard input a list of letters grades and computes and prints
 *  the GPA (the average of the numbers corresponding to the grades).
 */
public class Task_01 {
    
    public static void initGradesToNum(ST<String, Double> g2s) {
        g2s.put("A+", 4.33);
        g2s.put("A",  4.00);
        g2s.put("A-", 3.67);
        g2s.put("B+", 3.33);
        g2s.put("B",  3.00);
        g2s.put("B-", 2.67);
        g2s.put("C+", 2.33);
        g2s.put("C",  2.00);
        g2s.put("C-", 1.67);
        g2s.put("D",  1.00);
        g2s.put("F",  0.00);
    }
    
    public static void main(String[] args) {
        ST<String, Double> grades_to_score = new Task_02.ArrayST<>(2);
        initGradesToNum(grades_to_score);
        
        int grades_nm = 0;
        double total_score = 0;
        while (!StdIn.isEmpty()) {
            String grade = StdIn.readString();
            if (grade.equals("<exit>")) { break; }
            if (!grades_to_score.contains(grade)) { continue; }
            total_score += grades_to_score.get(grade);
            grades_nm++;
        }
        
        StdOut.printf("%s\n", grades_nm == 0 ? "N/A" : total_score / grades_nm);
    }

}
