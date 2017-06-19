package chapter_4.section_2;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 4.2.3
 * 
 * Create a copy constructor for Digraph that takes as input a digraph G and creates and initializes
 * a new copy of the digraph.
 */
public class Solution_03 {
  
  public static class CopyedDigraph extends Digraph {

    public CopyedDigraph(Digraph g) {
      super(g.V());
      for (int v = 0; v < g.V(); ++v) {
        Stack<Integer> adj_list = new Stack<>();
        for (int adj_v : g.adj(v)) { adj_list.push(adj_v); }
        for (int adj_v : adj_list) { addEdge(v, adj_v); }
      }
    }
    
    public static void main(String[] args) {
      Digraph dg = new Digraph(5);
      dg.addEdge(0, 1);
      dg.addEdge(1, 2);
      
      CopyedDigraph cdg = new CopyedDigraph(dg);
      dg.addEdge(2, 3);
      cdg.addEdge(3, 4);
      
      StdOut.println(dg);
      StdOut.println(cdg);
    }
  }
}
