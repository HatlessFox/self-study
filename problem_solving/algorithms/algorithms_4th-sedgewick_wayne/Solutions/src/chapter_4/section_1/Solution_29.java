package chapter_4.section_1;

import chapter_4.section_1.Common.Graph;
import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 4.1.29
 * 
 * Modify Cycle so that is works even if the graph contains self-loops and parallel edges
 */
public class Solution_29 {

  public static class Cycle {
    private boolean known[];
    private boolean has_cycle = false;
    
    public boolean hasCycle() { return has_cycle; }
    public Cycle(Graph g) {
      known = new boolean[g.vtcesNm()];
      for (int v = 0; v < g.vtcesNm(); ++v) {
        if (known[v]) { continue; }
        traverse_dfs(v, -1, g);
      }
    }
    
    private void traverse_dfs(int v, int prev, Graph g) {
      known[v] = true;
      for (int adj : g.adj(v)) {
        if (known[adj]) {
          if (adj != prev) { has_cycle = true; }
          continue;
        }
        traverse_dfs(adj, v, g);
      }
    }
  }
  
  public static void main(String[] args) {
    { // No loop
      Graph g = new Graph(2);
      //  0-1
      g.addEdge(0, 1);
      StdOut.println("1. " + new Cycle(g).hasCycle() + " (expected: false)");
    }
    { // Has loop
      Graph g = new Graph(3);
      /*  0-1-2
       *   \_/
       */
      g.addEdge(0, 1); g.addEdge(1, 2); g.addEdge(2, 0);
      StdOut.println("2. " + new Cycle(g).hasCycle() + " (expected: true)");
    }
    { // Self-loop
      Graph g = new Graph(1);
      /*   __
       *   |/
       *   0
       */
      g.addEdge(0, 0);
      StdOut.println("3.1 " + new Cycle(g).hasCycle() + " (expected: true)");
    }
    { // Self-loop
      Graph g = new Graph(2);
      /*   __
       *   |/
       * 0-1
       */
      g.addEdge(0, 1); g.addEdge(1, 1);
      StdOut.println("3.2 " + new Cycle(g).hasCycle() + " (expected: true)");
    }
    { // Parallel edge
      Graph g = new Graph(2);
      /*  _
       * | |
       * 0-1
       */
      g.addEdge(0, 1); g.addEdge(0, 1);
      StdOut.println("4. " + new Cycle(g).hasCycle() + " (expected: true)");
    }
  }
}
