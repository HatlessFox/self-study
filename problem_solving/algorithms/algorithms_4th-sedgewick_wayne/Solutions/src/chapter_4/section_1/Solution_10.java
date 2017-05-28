package chapter_4.section_1;

import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 4.1.10
 * 
 * Prove that every connected graph has a vertex has a vertex whose removal (including all incident
 * edges) will not disconnected the graph, and write a DFS method that finds such a vertex.
 * 
 * _Hint_: Consider a vertex whose adjacent vertices are all marked.
 */
public class Solution_10 {
  /* Proof.
   * 
   * If G is connected -> degree of any vertex is > 0.
   * By cases:
   *   1) Exists vertex, s.t. degree(v) = 1 -> the v can be removed.
   *   // TODO: more formal reasoning
   *   2) All vertices has degree > 1 -> G has a loop -> a vertex from the loop can be removed.
   * 
   */
  
  public static class RemovableVtxFinder {
    private boolean visited[];
    private int removable_vtx;
    
    public int find_removable_vtx(Common.Graph g) {
      final int START_VTX_ID = 0;
      removable_vtx = -1;
      visited = new boolean[g.vtcesNm()];
      
      if (!g.adj(START_VTX_ID).iterator().hasNext()) {
        throw new IllegalArgumentException("Given graph is not connected.");
      }
      dfs(g, START_VTX_ID);
      return removable_vtx;
    }
    
    private void dfs(Common.Graph g, int vtx) {
      if (search_is_done() || visited[vtx]) { return; }
      visited[vtx] = true;
      boolean found_unvisited = false;
      for (int adj_id : g.adj(vtx)) {
        if (visited[adj_id]) { continue; }
        found_unvisited = true;
        dfs(g, adj_id);
      }
      if (!found_unvisited) {
        removable_vtx = vtx;
      }
    }
    
    private boolean search_is_done() {
      return removable_vtx != -1;
    }
  }
  
  public static void main(String[] args) {
    {
      Common.Graph g = new Common.Graph(Common.TINYG_EX_2_V_NM);
      Common.initWithManualTinyGex2(g);
      StdOut.println(new RemovableVtxFinder().find_removable_vtx(g));
    }
    {
      Common.Graph line = new Common.Graph(4);
      line.addEdge(0, 1);
      line.addEdge(1, 2);
      line.addEdge(2, 3);
      StdOut.println(new RemovableVtxFinder().find_removable_vtx(line));
    }
  }
}
