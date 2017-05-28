package chapter_4.section_1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.UF;

/*
 * Exercise 4.1.8
 * 
 * Develop an implementation for the Search API that uses UF.
 */
public class Solution_08 {
  
  public static class UFSearch implements Common.Search {
    private UF graph_connectivity;
    private int start;
    private int count = 0;
    
    public UFSearch(Common.Graph g, int s) {
      start = s;
      graph_connectivity = new UF(g.vtcesNm());
      for (int vtx_id = 0; vtx_id < g.vtcesNm(); ++vtx_id) {
        for (int adj_id : g.adj(vtx_id)) {
          graph_connectivity.union(vtx_id, adj_id);
        }
      }
      
      // update 'count'
      for (int vtx_id = 0; vtx_id < g.vtcesNm(); ++vtx_id) {
        if (!graph_connectivity.connected(start, vtx_id)) { continue; }
        ++count; 
      }
    }
    
    @Override public boolean marked(int v) { return graph_connectivity.connected(start, v); }
    @Override public int count() { return count; }
    
  }

  public static void main(String[] args) {
    Common.Graph g = new Common.Graph(Common.TINYG_EX_2_V_NM);
    Common.initWithManualTinyGex2(g);
    
    Common.Search connected_5 = new UFSearch(g, 5);
    StdOut.println("Number of vtces connected to the 5th: " + connected_5.count());
    StdOut.print("Connected vtces:");
    for (int vtx_id = 0; vtx_id < g.vtcesNm(); ++vtx_id) {
      if (!connected_5.marked(vtx_id)) { continue; }
      StdOut.print(" " + vtx_id);
    }
    StdOut.println();
  }
}
