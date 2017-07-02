package chapter_4.section_3;

import java.io.File;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 4.3.9
 * 
 * Implement the constructor for EdgeWeightedGraph that read an edge-weighted graph from the input
 * stream, by suitable modifying the Constructor from Graph (see p. 526).
 */
public class Solution_09 {

  public static class StreamGraph extends EdgeWeightedGraph {

    public StreamGraph(In in) {
      super(in.readInt());
      int e = in.readInt();
      for (int i = 0; i < e; ++i) {
        addEdge(new Edge(in.readInt(), in.readInt(), in.readDouble()));
      }
    }
  }
  
  public static void main(String[] args) {
    if (args.length != 1) {
      StdOut.println("Args: <graph file>");
      System.exit(-1);
    }
    
    StdOut.println(new StreamGraph(new In(new File(args[0]))));
  }
}
