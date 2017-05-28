package chapter_4.section_1;

import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 4.1.22
 * 
 * Run SymbolGraph with movies.txt to find the Kevin Bacon number of this year's Oscar nominees.
 */
public class Solution_22 {
  public static void main(String[] args) {
    if (args.length != 2) {
      StdOut.println("Usage: <fname> <separator> \n");
      System.exit(-1);;
    }
    
    Common.SymbolGraph sg = new Common.SymbolGraph(args[0], args[1]);
    BreadthFirstPaths bfs = new BreadthFirstPaths(sg.graph(), sg.index("Bacon, Kevin"));
    while (!StdIn.isEmpty()) {
      String sink = StdIn.readLine();
      if (!sg.contains(sink)) {
        StdOut.println("Unknown...");
        continue;
      }
      
      if (!bfs.hasPathTo(sg.index(sink))) {
        StdOut.println("Not connected.");
      }
      
      int path_len = 0;
      for (int v : bfs.pathTo(sg.index(sink))) {
        StdOut.println("\t" + sg.name(v));
        path_len += 1;
      }
      StdOut.println("Kevin Bacon number is " + (path_len - 1)/ 2);
    }
  }
}
