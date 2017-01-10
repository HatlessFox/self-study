package chapter_1.section_3;

import java.lang.reflect.Field;

import edu.princeton.cs.algs4.ResizingArrayStack;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.3.8
 * 
 * Give the content and size of the array for ResizingArrayStackOfStrings with the input:
 * it was - the best - of times - - - it was - the - -
 */
public class Task_08 {

    private static final String[] TOKENS = 
            "it was - the best - of times - - - it was - the - -".split("\\s");
    
    public static void main(String[] args) {
        ResizingArrayStack<String> ras = new ResizingArrayStack<>();
        for (String token : TOKENS) {
            if (token.equals("-")) { ras.pop(); }
            else { ras.push(token); }
        }
        
        StdOut.print("Content: ");
        for (String e : ras) {
            StdOut.print(e + " ");
        }
        StdOut.println();
        try {
            Field arr_field = ras.getClass().getDeclaredField("a");
            arr_field.setAccessible(true);
            StdOut.printf("Array Size: %d\n", ((Object [])arr_field.get(ras)).length);
        } catch (Exception e) {
            StdOut.printf("[Error] Unable to extract array length: %s\n", e);
        }
    }
}
