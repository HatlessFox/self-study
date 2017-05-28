package chapter_4.section_1;

import java.util.Arrays;
import java.util.stream.IntStream;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SymbolGraph;

/*
 * Exercise 4.1.24
 * 
 * Compute the number of connected components in movies.txt, the size of the largest component,
 * and the number of components of size less than 10. Find the eccentricity, diameter, radius,
 * a center, and the girth of the largest component in the graph.
 */
public class Solution_24 {

  public static class ConnectedComponents {
    protected int count = 0;
    protected int[] vtx2component;
    
    public ConnectedComponents(Graph g) {
      vtx2component = new int[g.V()];
      Arrays.fill(vtx2component, -1);
      
      for (int v = 0; v < vtx2component.length; ++v) {
        if (visited(v)) { continue; }
        
        dfs(v, g);
        ++count;
      }
    }
    
    private boolean visited(int v) { return vtx2component[v] != -1; }
    
    private void dfs(int v, Graph g) {
      vtx2component[v] = count;
      
      for (int adj : g.adj(v)) {
          if (visited(adj)) { continue; }
          dfs(adj, g);
      }
    }
    
    public int count() { return count; }
    public Bag<Integer> component(int id) {
      Bag<Integer> vtces = new Bag<>();
      for (int v = 0; v < vtx2component.length; ++v) {
        if (vtx2component[v] == id) {
          vtces.add(v);
        }
      }
      return vtces;
    }
  }
  
  public static class SubGraph { // inherit from Graph if it is going to be used somewhere else
    private ST<Integer, Integer> l2g = new ST<>();
    private ST<Integer, Integer> g2l = new ST<>();
    private Graph sup_graph;
    
    public SubGraph(Iterable<Integer> vtces, Graph g) {
      sup_graph = g;
      for (int global_v : vtces) {
        int local_v = l2g.size();
        l2g.put(local_v, global_v);
        g2l.put(global_v, local_v);
      }
    }

    public int vtcesNm() { return l2g.size(); }
    public int global_v(int local_v) { return l2g.get(local_v); }
    public int local_v(int global_v) { return g2l.get(global_v); }
    
    public Iterable<Integer> adj(int local_v) {
      Bag<Integer> adj_vtces = new Bag<>();
      for (int v : sup_graph.adj(global_v(local_v))) {
        adj_vtces.add(local_v(v));
      }
      return adj_vtces;
    }
  }
  
  public static class GraphProperties {
    private int eccentricities[];
    //private int eccentricities_v[];
    
    //private int path_len[];
    private SubGraph g;
    
    public GraphProperties(Bag<Integer> component, Graph graph) {
      g = new SubGraph(component, graph);
      eccentricities = new int[g.vtcesNm()];
      //eccentricities_v = new int[g.vtcesNm()];
      //path_len = new int[g.vtcesNm()];
      
      // initialize eccentricities*
      /*
      for (int v = 0; v < g.vtcesNm(); ++v) {
        StdOut.println(v);
        bfs(v, g);
        int farthest_node = v, longest_shortest_path_len = path_len[farthest_node];
        for (int i = 0; i < g.vtcesNm(); ++i) {
          if (longest_shortest_path_len < path_len[i]) {
            longest_shortest_path_len = path_len[i];
            farthest_node = i;
          }
        }
        eccentricities[v] = longest_shortest_path_len;
        eccentricities_v[v] = farthest_node;
      }
      */
    }
    /*
    private void bfs(int start, SubGraph g) {
      Arrays.fill(path_len, 0);
      boolean known[] = new boolean[g.vtcesNm()];
      
      Queue<Integer> frontier = new LinkedList<>();
      frontier.add(start);
      known[start] = true;
      path_len[start] = 0;
      
      while (!frontier.isEmpty()) {
        int v = frontier.poll();
        for (int adj : g.adj(v)) {
          if (known[adj]) { continue; }
          frontier.add(adj);
          known[adj] = true;
          path_len[adj] = path_len[v] + 1;
        }
      }
    }
    */
    public int diameter() { return IntStream.of(eccentricities).max().getAsInt(); }
    public int radius() { return IntStream.of(eccentricities).min().getAsInt(); }
    
    public int center() {
      return -1;
      /*
      int r = radius();
      for (int v = 0; v < g.vtcesNm(); ++v) {
        if (eccentricities[v] == r) {
          return g.local_v(eccentricities_v[v]);
        }
      }
      return -1;
      */
    }
    
    public int girth() {
      // TODO
      return -1;
    }
  }
  
  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Usage: <path to movies.txt>");
      System.exit(-1);
    }
    
    SymbolGraph sg = new SymbolGraph(args[0], "/");
    ConnectedComponents cc = new ConnectedComponents(sg.G());
    
    StdOut.println("Number of components: " + cc.count());
    int max_component_id = 0, max_component_sz = cc.component(max_component_id).size();
    int small_components_nm = 0;
    for (int ci = 0; ci < cc.count(); ++ci) {
      int component_sz = cc.component(ci).size();
      if (max_component_sz < component_sz) {
        max_component_sz = component_sz;
        max_component_id = ci;
      }
      if (component_sz < 10) {
        ++small_components_nm;
      }
    }
    StdOut.println("Largest component: " + max_component_id + " (size = " + max_component_sz + ")");
    StdOut.println("Number of components less than 10: " + small_components_nm);
    
    GraphProperties gp = new GraphProperties(cc.component(max_component_id), sg.G());
    StdOut.println("Diameter: " + gp.diameter());
    StdOut.println("Radius: " + gp.radius());
    StdOut.println("Center: " + gp.center());
    StdOut.println("Girth: " + gp.girth());
  }
}
