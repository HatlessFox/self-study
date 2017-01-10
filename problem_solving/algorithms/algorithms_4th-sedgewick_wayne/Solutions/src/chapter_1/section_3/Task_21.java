package chapter_1.section_3;

import chapter_1.section_3.common.CommonLinkedList;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.3.21
 * 
 * Write a method find() that takes a linked list and a string key as arguments and returns true 
 * if some node in the list has key as its item field, false otherwise.
 */
public class Task_21 {

    public static boolean find(CommonLinkedList<String> ll, String k) {
        for (String e : ll) {
            if (e.equals(k)) { return true; }
        }
        return false;
    }
    
    public static void main(String[] args) {
        CommonLinkedList<String> ll = new CommonLinkedList<>();
        while (true) {
            StdOut.print("Enter op (a - add; f - find): ");
            switch (StdIn.readString()) {
            case "a": ll.add(StdIn.readString()); break;
            case "f": StdOut.printf("%s\n", find(ll, StdIn.readString()) ? "OK" : "FAIL"); break;
            }
        }
    }
}
