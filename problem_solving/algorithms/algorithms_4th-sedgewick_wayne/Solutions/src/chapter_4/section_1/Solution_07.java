package chapter_4.section_1;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 4.1.7
 * 
 * Develop a test client for Graph that reads a graph from the input stream named as command-line
 * argument and then prints it, relying on toString(). 
 */
public class Solution_07 {
  public static void main(String[] args) {
    if (args.length == 0) {
      StdOut.println("Args: <input file>");
      System.exit(-1);
    }

    StdOut.println(new Graph(new In(args[0])));
  }
}
