package chapter_4.section_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.KosarajuSharirSCC;
import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 4.2.19
 * 
 * What happens if you run the Kosaraju-Sharir algorithm on a DAG?
 */
public class Solution_19 {

  /* it assigns SCC ids to vertices in reversed topological order */
  
  public static void main(String[] args) {
    Digraph dag = Utils.dag();
    KosarajuSharirSCC scc = new KosarajuSharirSCC(dag);
    Map<Integer, List<Integer>> scc_vtcs = new HashMap<>();
    for (int v = 0; v < dag.V(); ++v) {
      int scc_id = scc.id(v);
      if (!scc_vtcs.containsKey(scc_id)) {
        scc_vtcs.put(scc_id, new ArrayList<>());
      }
      scc_vtcs.get(scc_id).add(v);
    }
    
    List<Integer> order = new ArrayList<>();
    for (int scc_id = scc.count() - 1; scc_id <= 0; --scc_id) {
      order.addAll(scc_vtcs.get(scc_id));
    }
    StdOut.println(Solution_09.isTopological(order, dag));
  }
}
