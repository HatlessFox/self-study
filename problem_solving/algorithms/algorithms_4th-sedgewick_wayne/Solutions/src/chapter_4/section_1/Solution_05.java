package chapter_4.section_1;

import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 4.1.5
 * 
 * Modify _Graph_ to disallow parallel edges and self-loops.
 */
public class Solution_05 {

  public static class NoSelfLoopParEdgesGraph extends Common.Graph {
    public NoSelfLoopParEdgesGraph(int v) { super(v); }

    public boolean hasEdge(int from, int to) {
      for (int adj_id : adj(from)) {
        if (adj_id == to) { return true; }
      }
      return false;
    }

    @Override
    public void addEdge(int from, int to) {
      if (from == to) { /* ignore self-loops */ return; }
      if (hasEdge(from, to)) { /* ignore parallel edge */ return; }
      super.addEdge(from, to);
    }
  }
  
  public static Common.Graph init_graph(Common.Graph g) {
    g.addEdge(1, 2);
    g.addEdge(3, 0);
    g.addEdge(0, 1);
    g.addEdge(2, 3);
    
    g.addEdge(4, 4);
    g.addEdge(0, 1);
    return g;
  }
  
  public static void main(String[] args) {
    NoSelfLoopParEdgesGraph nslpe_g = new NoSelfLoopParEdgesGraph(5);
    Common.Graph common_g = new Common.Graph(5);
    StdOut.println("= No Self-Loop, No Parallel Edges =");
    StdOut.println(init_graph(nslpe_g));
    StdOut.println("= Original =");
    StdOut.println(init_graph(common_g));
  }
}
