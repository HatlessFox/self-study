package chapter_4.section_3;

import java.io.File;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 4.3.17
 * 
 * Implement toString() for EdgeWeightedGraph.
 */
public class Solution_17 {

  static public class StringifiedEdgeWeightedGraph extends EdgeWeightedGraph {

    public StringifiedEdgeWeightedGraph(In in) {
      super(in);
    }
    
    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(V() + " " + E() + "\n");
      for (int v = 0; v < V(); ++v) {
        sb.append(v + ": ");
        for (Edge incid_e : adj(v)) {
          sb.append(String.format("%d-%d %.6f ", v, incid_e.other(v), incid_e.weight()));
        }
        sb.append("\n");
      }
      return sb.toString();
    }
  }
  
  static public void main(String args[]) {
    if (args.length != 1) {
      StdOut.println("Args: <graph-file>");
      System.exit(-1);
    }
    StdOut.println("= Original =");
    StdOut.println(new EdgeWeightedGraph(new In(new File(args[0]))));
    StdOut.println("= Custom =");
    StdOut.println(new StringifiedEdgeWeightedGraph(new In(new File(args[0]))));
  }
}
