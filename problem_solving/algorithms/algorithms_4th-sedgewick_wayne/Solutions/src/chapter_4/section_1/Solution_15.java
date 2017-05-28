package chapter_4.section_1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 4.1.15
 * 
 * Modify the input stream constructor for Graph to also allow adjacency lists from standard input
 * (in a manner similar to SymbolGraph), as in the example tinyGadj.txt shown at right. After the
 * number of vertices and edges, each line contains a vertex and its list of adjacent vertices. 
 */
public class Solution_15 {

  public static class AdjInitGraph extends Common.Graph {
    public AdjInitGraph(int n) { super(n); }
    
    public AdjInitGraph(In in) {
      super(in.readInt());

      in.readInt(); // ignore edges number
      while (in.hasNextLine()) {
        int vtx = in.readInt();
        for (String adj_id : in.readLine().trim().split(" ")) {
          addEdge(vtx, Integer.parseInt(adj_id));
        }
      }
    }
  }
  
  public static void main(String[] args) {
    if (args.length != 1) {
      StdOut.println("Args: <graph file>");
      System.exit(-1);
    }
    
    StdOut.println(new AdjInitGraph(new In(args[0])));
  }
}
