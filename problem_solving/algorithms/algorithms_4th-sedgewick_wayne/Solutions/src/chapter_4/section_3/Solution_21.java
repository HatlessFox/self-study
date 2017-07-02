package chapter_4.section_3;

import java.io.File;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 4.3.21
 * 
 * Provide an implementation of edges() for PrimMST (p. 622)
 */
public class Solution_21 {

  public static class EagerPrimMST {
    private Edge[] edge_to;
    private boolean[] in_tree;
    private IndexMinPQ<Edge> frontier;
    
    public EagerPrimMST(EdgeWeightedGraph g) {
      edge_to = new Edge[g.V()];
      in_tree = new boolean[g.V()];
      frontier = new IndexMinPQ<>(g.V());
      
      addToMst(g, null);
      while (!frontier.isEmpty()) {
        Edge e = frontier.minKey();
        frontier.delMin();
        addToMst(g, e);
      }
    }
    
    private void addToMst(EdgeWeightedGraph g, Edge edge) {
      int v = 0;
      if (edge != null) {
        int w = edge.either();
        v = in_tree[w] ? edge.other(w) : w;
      }
      assert !in_tree[v];
      in_tree[v] = true;
      edge_to[v] = edge;
      for (Edge e : g.adj(v)) {
        int external_v = e.other(v);
        if (in_tree[external_v]) { continue; }
        if (frontier.contains(external_v)) {
          if (e.weight() < frontier.keyOf(external_v).weight()) {
            frontier.changeKey(external_v, e);
          }
        } else {
          frontier.insert(external_v, e);
        }
      }
    }
    
    public Iterable<Edge> edges() {
      Bag<Edge> edges = new Bag<Edge>();
      for (int v = 1; v < edge_to.length; ++v) { edges.add(edge_to[v]); }
      return edges;
    }
  }
  
  public static void main(String[] args) {
    if (args.length != 1) {
      StdOut.println("");
      System.exit(-1);
    }
    for (Edge e : new EagerPrimMST(new EdgeWeightedGraph(new In(new File(args[0])))).edges()) {
      StdOut.println(e);
    }
  }
}
