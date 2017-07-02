package chapter_4.section_3;

import java.io.File;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.UF;

/*
 * Exercise 4.3.18
 * 
 * Give traces that show the process of computing the MST of the graph defined in Exercise 4.3.6
 * with lazy version of Prim's algorithm, the eager version of Prim's algorithm, and Kruskal's algo.
 */
public class Solution_18 {

  static public interface MstBuilder {
    public Iterable<Edge> mstEdges();
    public default double weight() {
      double w = 0;
      for (Edge e : mstEdges()) { w += e.weight(); }
      return w;
    }
    public default String stringify() {
      StringBuilder sb = new StringBuilder();
      sb.append(String.format("<%.3f>", weight()));
      for (Edge e : mstEdges()) {
        int v = e.either();
        sb.append(" ");
        sb.append(String.format("%d-%d %.3f", v, e.other(v), e.weight()));
      }
      return sb.toString();
    }
  }
  
  static public class LazyPrimMst implements MstBuilder {
    private boolean in_tree[];
    private Edge edge_to[];

    public LazyPrimMst(EdgeWeightedGraph g) { buildMst(g); }
    
    private void buildMst(EdgeWeightedGraph g) {
      in_tree = new boolean[g.V()];
      edge_to = new Edge[g.V()];
      
      MinPQ<Edge> frontier = new MinPQ<>();
      addToMst(0, null, frontier, g);
      
      while (!frontier.isEmpty()) {
        Edge min_e = frontier.delMin();
        int edge_v = min_e.either(), edge_w = min_e.other(edge_v);
        if (in_tree[edge_v] && in_tree[edge_w]) {
          StdOut.println("(Frontier) Ignore [" + min_e + "].");
          continue;
        }
        assert !in_tree[edge_v] && !in_tree[edge_w]; // edge is not in frontier
        
        addToMst(!in_tree[edge_v] ? edge_v : edge_w, min_e, frontier, g);
      }
    }
    
    private void addToMst(int v, Edge e, MinPQ<Edge> frontier, EdgeWeightedGraph g) {
      StdOut.println("(MST) Add " + v + " by [" + e + "].");
      in_tree[v] = true;
      edge_to[v] = e;
      for (Edge incid_e : g.adj(v)) {
        StdOut.println("(Frontier) Add [" + incid_e + "].");
        frontier.insert(incid_e);
      }
    }

    @Override
    public Iterable<Edge> mstEdges() {
      Bag<Edge> edges = new Bag<>();
      for (Edge e : edge_to) {
        if (e != null) { edges.add(e); }
      }
      return edges;
    }
    
    @Override
    public String toString() { return stringify(); }
  }

  static public class EagerPrimMst implements MstBuilder {
    private boolean in_tree[];
    private Edge edge_to[];

    public EagerPrimMst(EdgeWeightedGraph g) { buildMst(g); }
    
    private void buildMst(EdgeWeightedGraph g) {
      in_tree = new boolean[g.V()];
      edge_to = new Edge[g.V()];
      
      IndexMinPQ<Edge> frontier = new IndexMinPQ<>(g.V());
      addToMst(0, null, frontier, g);
      
      while (!frontier.isEmpty()) {
        Edge min_e = frontier.minKey();
        frontier.delMin();
        int edge_v = min_e.either(), edge_w = min_e.other(edge_v);
        addToMst(!in_tree[edge_v] ? edge_v : edge_w, min_e, frontier, g);
      }
    }
    
    private void addToMst(int v, Edge e, IndexMinPQ<Edge> frontier, EdgeWeightedGraph g) {
      StdOut.println("(MST) Add " + v + " by [" + e + "].");
      in_tree[v] = true;
      edge_to[v] = e;
      for (Edge incid_e : g.adj(v)) {
        int edge_v = incid_e.either(), edge_w = incid_e.other(edge_v);
        if (in_tree[edge_v] && in_tree[edge_w]) { continue; }
        assert !in_tree[edge_v] && !in_tree[edge_w]; // edge is not in frontier
        if (in_tree[edge_w]) { int tmp = edge_w; edge_w = edge_v; edge_v = tmp; }
        
        if (frontier.contains(edge_w)) {
          if  (incid_e.weight() < frontier.keyOf(edge_w).weight()) {
            StdOut.println("(Frontier) Update dist to " + edge_w + " by [" + incid_e + "].");
            frontier.changeKey(edge_w, incid_e);
          }
        } else {
          StdOut.println("(Frontier) Add [" + incid_e + "].");
          frontier.insert(edge_w, incid_e);
        }
      }
    }
    
    @Override
    public Iterable<Edge> mstEdges() {
      Bag<Edge> edges = new Bag<>();
      for (Edge e : edge_to) {
        if (e != null) { edges.add(e); }
      }
      return edges;
    }
    
    @Override
    public String toString() { return stringify(); }
  }

  static public class KruskalPrimMst implements MstBuilder {
    private Bag<Edge> edges;

    public KruskalPrimMst(EdgeWeightedGraph g) { buildMst(g); }
    
    private void buildMst(EdgeWeightedGraph g) {
      edges = new Bag<>();
      
      MinPQ<Edge> sorted_edges = new MinPQ<>();
      for (Edge e : g.edges()) { sorted_edges.insert(e); }
      UF connections = new UF(g.V());
      
      while (edges.size() != g.V() - 1) {
        Edge e = sorted_edges.delMin();
        int v = e.either(), w = e.other(v);
        if (connections.connected(v, w)) {
          StdOut.println("(Edges) Ignore [" + e + "].");
          continue;
        }
        StdOut.println("(MST) Add [" + e + "].");
        connections.union(v, w);
        edges.add(e);
      }
    }
    
    @Override public Iterable<Edge> mstEdges() { return edges; }
    @Override public String toString() { return stringify(); }
  }
  
  // Tests
  
  public static void main(String[] args) {
    if (args.length != 1) {
      StdOut.println("Args: <graph-input>");
      System.exit(-1);
    }
    
    EdgeWeightedGraph g = new EdgeWeightedGraph(new In(new File(args[0])));
    StdOut.println("== Lazy Prim ==");
    StdOut.println(new LazyPrimMst(g));
    StdOut.println("== Eager Prim ==");
    StdOut.println(new EagerPrimMst(g));
    StdOut.println("== Kruskal Prim ==");
    StdOut.println(new KruskalPrimMst(g));
    
  }
}
