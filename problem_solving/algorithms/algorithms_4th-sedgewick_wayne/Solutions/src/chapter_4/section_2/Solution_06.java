package chapter_4.section_2;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 4.2.6
 * 
 * Develop a test client for Digraph.
 */
public class Solution_06 {

  public static void main(String[] args) {
    StdOut.print("Enter number of vertices: ");
    Digraph g = new Digraph(StdIn.readInt());
    while (true) {
      String cmd = StdIn.readString();
      switch (cmd) {
      case "exit": return;
      case "print": StdOut.print(g); break;
      case "add": g.addEdge(StdIn.readInt(), StdIn.readInt()); break;
      }
    }
  }

}
