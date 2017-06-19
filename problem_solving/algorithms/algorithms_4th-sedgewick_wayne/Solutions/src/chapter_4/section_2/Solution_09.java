package chapter_4.section_2;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Topological;

/*
 * Exercise 4.2.9
 * 
 * Write a method that checks whether a given permutation of a DAG's vertices is
 * a topological of that DAG.
 */
public class Solution_09 {

  public static boolean isTopological(Iterable<Integer> order, Digraph g) {
    boolean processed[] = new boolean[g.V()];
    for (int v : order) {
      // NB: the check below if order holds all vertices,
      //     otherwise check whether v is reachable from processed vertices
      for (int adj : g.adj(v)) {
        // is back edge?
        if (processed[adj]) { return false;}
      }
      processed[v] = true;
    }
    return true;
  }
  
  public static void main(String[] args) {
    Digraph dag = Utils.dag();
    StdOut.println(isTopological(new Topological(dag).order(), dag));
    
    List<Integer> non_topo = new ArrayList<>();
    non_topo.add(1);
    non_topo.add(5);
    non_topo.add(3);
    non_topo.add(4);
    StdOut.println(isTopological(non_topo, dag));
  }
  
}
