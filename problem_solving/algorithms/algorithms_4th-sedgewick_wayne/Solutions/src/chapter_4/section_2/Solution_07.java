package chapter_4.section_2;

import java.util.stream.StreamSupport;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 4.2.7
 * 
 * Write a program that implements the API on page 596.
 */
public class Solution_07 {

  public static class Degrees {
    private Digraph g;
    public Degrees(Digraph dg) { g = dg; }
    
    public int indegree(int v) {
      int indegree = 0;
      for (int w = 0; w < g.V(); ++w) {
        indegree += StreamSupport.stream(g.adj(w).spliterator(), false)
                                 .filter(adj_w -> adj_w == v).count(); 
      }
      return indegree;
    }
    
    public int outdegree(int v) {
      return (int) StreamSupport.stream(g.adj(v).spliterator(), false).count();
    }
    
    public Iterable<Integer> sources() {
      Bag<Integer> sources = new Bag<>();
      for (int w = 0; w < g.V(); ++w) {
        if (indegree(w) == 0) { sources.add(w); }
      }
      return sources;
    }
    
    public Iterable<Integer> sinks() {
      Bag<Integer> sinks = new Bag<>();
      for (int w = 0; w < g.V(); ++w) {
        if (outdegree(w) == 0) { sinks.add(w); }
      }
      return sinks;
    }
    
     public boolean isMap() {
      for (int w = 0; w < g.V(); ++w) {
        if (outdegree(w) != 1) { return false; }
      }
      return true;
    }
  }
  
  
  public static void main(String[] args) {
    Degrees d = new Degrees(Utils.digraph_tiny2());
    
    StdOut.println("Tiny2 Digraph:");
    StdOut.println("\tindegree(5) = " + d.indegree(5));
    StdOut.println("\toutdegree(10) = " + d.outdegree(10));
    StdOut.println("\tsources = " + Utils.toString(d.sources()));
    StdOut.println("\tsinks = " + Utils.toString(d.sinks()));
    StdOut.println("\tisMap = " + d.isMap());
  }
  
}
