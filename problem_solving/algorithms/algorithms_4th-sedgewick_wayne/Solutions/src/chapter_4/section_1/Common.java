package chapter_4.section_1;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

public class Common {

  public static class Graph {
    protected int edges_nm;
    protected Bag<Integer>[] edges;
    
    @SuppressWarnings("unchecked")
    public Graph(int v) {
      edges_nm = 0;
      edges = (Bag<Integer>[])new Bag[v];
      for (int vtx_id = 0; vtx_id < edges.length; ++vtx_id) {
        edges[vtx_id] = new Bag<Integer>();
      }
    }
    
    public void addEdge(int from, int to) {
      edges[from].add(to);
      edges[to].add(from);
      ++edges_nm;
    }
    
    public int edgesNm() { return edges_nm; }
    public int vtcesNm() { return edges.length; }
    public Iterable<Integer> adj(int vtx) { return edges[vtx]; }
    
    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(vtcesNm()).append(" vertices, ").append(edgesNm()).append(" edges.");
      sb.append("\n");
      for (int vtx_id = 0; vtx_id < vtcesNm(); ++vtx_id) {
        sb.append(vtx_id).append(":");
        for (Integer adj_id : adj(vtx_id)) {
          sb.append(" ").append(adj_id);
        }
        sb.append("\n");
      }
      return sb.toString();
    }
  }

  public static interface Search {
    boolean marked(int v);
    int count();
  }
  
  public static int TINYG_EX_2_V_NM = 12;
  public static void initWithManualTinyGex2(Graph g) {
    g.addEdge(8, 4);
    g.addEdge(2, 3);
    g.addEdge(1, 11);
    g.addEdge(0, 6);
    g.addEdge(3, 6);
    g.addEdge(10, 3);
    g.addEdge(7, 11);
    g.addEdge(7, 8);
    g.addEdge(11, 8);
    g.addEdge(2, 0);
    g.addEdge(6, 2);
    g.addEdge(5, 2);
    g.addEdge(5, 10);
    g.addEdge(5, 0);
    g.addEdge(8, 1);
    g.addEdge(4, 1);
  }

  public static class SymbolGraph {
    protected ST<String, Integer> str2ind = new ST<>();
    protected String[] ind2str;
    protected edu.princeton.cs.algs4.Graph g;
    
    public SymbolGraph(String stream_name, String separator) {
      In in = new In(stream_name);
      while (in.hasNextLine()) {
        String[] str_id = in.readLine().split(separator);
        for (int i = 0; i < str_id.length; ++i) {
          if (str2ind.contains(str_id[i])) { continue; }
          str2ind.put(str_id[i], str2ind.size());
        }
      }
      // init ind->str
      ind2str = new String[str2ind.size()];
      for (String str_id : str2ind.keys()) {
        ind2str[str2ind.get(str_id)] = str_id;
      }
      
      g = new edu.princeton.cs.algs4.Graph(str2ind.size());
      in = new In(stream_name);
      while (in.hasNextLine()) {
        String data[] = in.readLine().split(separator);
        int v = str2ind.get(data[0]);
        for (int i = 1; i < data.length; ++i) {
          g.addEdge(v, str2ind.get(data[i]));
        }
      }
    }
    
    public boolean contains(String s) { return str2ind.contains(s); }
    public int index(String s) { return str2ind.get(s); }
    public String name(int i) { return ind2str[i]; }
    public edu.princeton.cs.algs4.Graph graph() { return g; }
  }
}
