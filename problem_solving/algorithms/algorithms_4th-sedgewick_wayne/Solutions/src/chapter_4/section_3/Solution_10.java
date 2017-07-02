package chapter_4.section_3;

import java.io.File;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 4.3.10
 * 
 * Develop an EdgeWeightedGraph implementation for dense graphs that uses an adjacency-matrix
 * (two-dimensional array of weights) representation. Disallow parallel edges.
 */
public class Solution_10 {

  public static class AdjMtxEdgeWeightedGraph {
    private double[][] mtx;
    private int e_nm;
    
    public AdjMtxEdgeWeightedGraph(In in) {
      this(in.readInt());
      int e = in.readInt();
      for (int i = 0; i < e; ++i) {
        addEdge(new Edge(in.readInt(), in.readInt(), in.readDouble()));
      }
    }
    
    public AdjMtxEdgeWeightedGraph(int v) {
      mtx = new double[v][v];
    }
    
    public int v() { return mtx.length; }
    public int e() { return e_nm; }
    public void addEdge(Edge e) {
      int v = e.either(), w = e.other(v);
      if (mtx[v][w] != 0) { return; }
      mtx[v][w] = mtx[w][v] = e.weight();
      ++e_nm;
    }
    public Iterable<Edge> adj(int v) {
      Bag<Edge> edges = new Bag<>();
      for (int adj = 0; adj < mtx[v].length; ++adj) {
        if (mtx[v][adj] == 0) { continue; }
        edges.add(new Edge(v, adj, mtx[v][adj]));
      }
      return edges;
    }
    public Iterable<Edge> edges() {
      Bag<Edge> edges = new Bag<>();
      for (int v = 0; v < mtx.length; ++v) {
        for (int w = 0; w < mtx[v].length; ++w) {
          if (w < v || mtx[v][w] == 0) { continue; }
          edges.add(new Edge(v, w, mtx[v][w]));
        }
      }
      return edges;
    }
    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(v() + " ");
      sb.append(e() + "\n");
      for (int v = 0; v < mtx.length; ++v) {
        sb.append(v + ": ");
        for (Edge e : adj(v)) {
          sb.append(v + "-" + e.other(v) + " " + e.weight() + " ");
        }
        sb.append("\n");
      }
      return sb.toString();
    }
  }
  
  public static void main(String[] args) {
    if (args.length != 1) {
      StdOut.println("Args: <graph file>");
      System.exit(-1);
    }
    
    StdOut.println(new EdgeWeightedGraph(new In(new File(args[0]))));
    StdOut.println(new AdjMtxEdgeWeightedGraph(new In(new File(args[0]))));
  }
}
