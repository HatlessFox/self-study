package chapter_4.section_1;

import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SymbolGraph;

/*
 * Exercise 4.1.23
 * 
 * Write a program BaconHistogram that prints a histogram of Kevin Bacon numbers,
 * indicating how many performers from movies.txt have a Bacon number of 0, 1, 2, 3... .
 * Include a category for those who have an infinite number (not connected to Kevin Bacon).
 */
public class Solution_23 {

  private static String KEVIN_BACON_NAME = "Bacon, Kevin";
  
  private static void incHistogramRecord(ST<Integer, Integer> hist, int record) {
    if (hist.contains(record)) {
      hist.put(record, hist.get(record) + 1);
    } else {
      hist.put(record, 1);
    }
  }
  
  public static void main(String[] args) {
    if (args.length != 1) {
      StdOut.println("Usage: <movies db>");
      System.exit(-1);
    }
    
    SymbolGraph sg = new SymbolGraph(args[0], "/");
    BreadthFirstPaths pathes = new BreadthFirstPaths(sg.G(), sg.index(KEVIN_BACON_NAME));
    ST<Integer, Integer> histogram = new ST<>();
    
    for (int vtx = 0; vtx < sg.G().V(); ++vtx) {
      if (!pathes.hasPathTo(vtx)) {
        incHistogramRecord(histogram, -1);
        continue;
      }
      
      int dist = pathes.distTo(vtx);
      if (dist == 0) {
        incHistogramRecord(histogram, 0);
      } else {
        incHistogramRecord(histogram, (dist - 1) / 2);
      }
    }
    
    for (int num : histogram.keys()) {
      if (num == -1) {
        StdOut.println("Infinity: " + histogram.get(-1));
      } else {
        StdOut.println("" + num + ": " + histogram.get(num));
      }
    }
  }
}
