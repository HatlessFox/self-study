package chapter_4.section_2;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 4.2.5
 * 
 * Modify Digraph to disallow parallel edges and self-loops.
 */
public class Solution_05 {
  public static class HealthyDigraph extends Solution_04.EdgeCheckDigraph {

    public HealthyDigraph(Digraph G) { super(G); }
    @Override
    public void addEdge(int v, int w) {
      if (v == w) { throw new RuntimeException("Any self-loop is forbidden"); }
      if (hasEdge(v, w)) { throw new RuntimeException("Any parallel edge is forbidden"); }
      super.addEdge(v, w);
    }
  }
  
  public static void main(String[] args) {
    HealthyDigraph hd = new HealthyDigraph(Utils.digraph_tiny2());
    
    hd.addEdge(1, 9);
    try {
      hd.addEdge(1, 1);
    } catch (RuntimeException e){
      StdOut.println("[Self-loops rejection] " + e.getMessage());
    }
    try {
      hd.addEdge(1, 9);
    } catch (RuntimeException e){
      StdOut.println("[Parallel edges rejection] " + e.getMessage());
    }
  }
}
