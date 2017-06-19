package chapter_4.section_2;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 4.2.5
 * 
 * Add a method hasEdge() to Digraph which takes two int arguments v and w and return true
 * if the groph has an edge v->w, false otherwise.
 */
public class Solution_04 {

  public static class EdgeCheckDigraph extends Digraph {

    public EdgeCheckDigraph(Digraph G) { super(G); }
    
    public boolean hasEdge(int v, int w) {
      for (int adj_v : adj(v)) {
        if (adj_v != w) { continue; }
        return true;
      }
      return false;
    }
  }

  public static void main(String[] args) {
    EdgeCheckDigraph ecd = new EdgeCheckDigraph(Utils.digraph_tiny2());
    StdOut.println("5 -> 10: " + ecd.hasEdge(5, 10));
    StdOut.println("5 -> 8: " + ecd.hasEdge(5, 8));
  }
}
