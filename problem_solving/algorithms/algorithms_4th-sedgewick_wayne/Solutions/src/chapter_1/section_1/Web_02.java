package chapter_1.section_1;

import java.nio.file.Paths;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdOut;

/*
 * Web Exercise 2: Wget
 * 
 * Write a program that reads in data from the URL specified on the command line and saves it
 * in a file with the same name.
 */
public class Web_02 {

    public static void main(String[] args) {
        if (args.length < 1) {
            StdOut.println("Args: <url>");
            System.exit(-1);
        }
        In in = new In(args[0]);
        Out out = new Out(Paths.get(args[0]).getFileName().toString());
        out.print(in.readAll());
    }
    
}
