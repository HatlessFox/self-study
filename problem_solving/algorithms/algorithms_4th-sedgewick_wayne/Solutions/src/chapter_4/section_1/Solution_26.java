package chapter_4.section_1;

import edu.princeton.cs.algs4.DepthFirstPaths;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SymbolGraph;

/*
 * Exercise 4.1.26
 * 
 * Write a SymbelGraph client like DegreesOfSeparation that uses depth-first search instead of bfs
 * to find paths connecting two performers, producing output like that shown on the facing page.
 * 
 * NB: -Xss option increases jvm stack size. 
 */
public class Solution_26 {
  public static void main(String args[]) {
    if (args.length != 3) {
      StdOut.println("Usage: <movies db> <separator> <name>");
      System.exit(-1);
    }
    
    SymbolGraph sg = new SymbolGraph(args[0], args[1]);
    DepthFirstPaths pathes = new DepthFirstPaths(sg.G(), sg.index(args[2]));
    
    while (!StdIn.isEmpty()) {
      String sink = StdIn.readLine();
      if (!pathes.hasPathTo(sg.index(sink))) {
        StdOut.println("Not connected.");
        continue;
      }
      
      for (int v : pathes.pathTo(sg.index(sink))) {
        StdOut.println("\t" + sg.name(v));
      }
    }
  }
}
