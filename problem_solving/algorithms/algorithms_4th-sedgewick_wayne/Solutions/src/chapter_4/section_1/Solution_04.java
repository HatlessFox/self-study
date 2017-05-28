package chapter_4.section_1;

import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 4.1.4
 * 
 * Add a member _hasEdge()_ to _Graph_ which takes two _int_ arguments _v_ and _w_
 * and returns _true_ if the graph has an edge v-w, _false_ otherwise.
 */
public class Solution_04 {

  public static class HasEdgeGraph extends Common.Graph {
    public HasEdgeGraph(int v) { super(v); }
    
    public boolean hasEdge(int from, int to) {
      for (int adj_id : adj(from)) {
        if (adj_id == to) { return true; }
      }
      return false;
    }
  }
  
  public static void main(String[] args) {
    HasEdgeGraph g = new HasEdgeGraph(Common.TINYG_EX_2_V_NM);
    Common.initWithManualTinyGex2(g);
    
    StdOut.println(g);
    StdOut.println("5 -> 2: " + g.hasEdge(5, 2));
    StdOut.println("4 -> 11: " + g.hasEdge(4, 11));
  }
}
