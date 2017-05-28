package chapter_4.section_1;

import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 4.1.3
 * 
 * Create a copy ctor for _Graph_ that takes as input a graph _G_ and creates and initializes a new
 * copy of the graph. Any changes a client makes to _G_ should not affect the newly created graph.
 */
public class Solution_03 {

  public static class ClonableGraph extends Common.Graph {
    
    public ClonableGraph(int vtces_nm) {
      super(vtces_nm);
    }
    
    public ClonableGraph(Common.Graph g) {
      this(g.vtcesNm());
      for (int vtx_id = 0; vtx_id < g.vtcesNm(); ++vtx_id) {
        for (Integer adj_id : g.adj(vtx_id)) {
          if (adj_id < vtx_id) { continue; }
          addEdge(vtx_id, adj_id);
        }
      }
    }
  }
  
  public static void main(String[] args) {
    Common.Graph g1 = new Common.Graph(Common.TINYG_EX_2_V_NM);
    Common.initWithManualTinyGex2(g1);
    
    StdOut.println("Original: ");
    StdOut.println(g1);
    ClonableGraph g2 = new ClonableGraph(g1);
    g2.addEdge(11, 9);
    StdOut.println("Modified copy: ");
    StdOut.println(g2);
    StdOut.println("Original: ");
    StdOut.println(g1);
  }
}
