package chapter_4.section_1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.IntStream;

import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 4.1.17
 * 
 * The _Wiener index_ of a graph is the sum of the lengths of the shortest paths between all pairs
 * of vertices. Mathematical chemists use this quantity to analyze molecular graphs, where vertices
 * correspond to atoms and edges correspond to chemical bonds. Add a method wiener() to
 * GraphProperties that returns the Wiener index of a graph.
 */
public class Solution_17 {
  
  public static class WeinerIndexEstimator {
    private int sum_of_shortest_path_len = 0;
    
    public WeinerIndexEstimator(Common.Graph g) {
      for (int v = 0; v < g.vtcesNm(); ++v) {
        sum_of_shortest_path_len += IntStream.of(findShortestPathLengthes(v, g)).sum();
      }
    }
    
    public int weiner_index() {
      return sum_of_shortest_path_len / 2;
    }
    
    private int[] findShortestPathLengthes(int start, Common.Graph g) {
      int path_len[] = new int[g.vtcesNm()];
      boolean visited[] = new boolean[g.vtcesNm()];
      
      Queue<Integer> frontier = new LinkedList<>();
      frontier.add(start);
      visited[start] = true;
      path_len[start] = 0;
      
      while (!frontier.isEmpty()) {
        int v = frontier.poll();
        for (int adj : g.adj(v)) {
          if (visited[adj]) { continue; }
        
          frontier.add(adj);
          visited[adj] = true;
          path_len[adj] = 1 + path_len[v];
        }
      }
      
      return path_len;
    }
    
  }
  
  public static void main(String[] args) {
    /*
     * G:
     * 
     *     0-----6
     *     |\   /|
     *     | \ / |
     *     |  2  |
     *     | / \ |
     *     |/   \|
     *     5--10-3
     *         |
     *     7---8--4
     *     |  /| /
     *     | / |/
     *     11--1
     *         |
     *         9
     * 
     */
    Common.Graph g = new Common.Graph(Common.TINYG_EX_2_V_NM);
    Common.initWithManualTinyGex2(g);
    g.addEdge(8, 10);
    g.addEdge(9, 1);
    
    StdOut.println(new WeinerIndexEstimator(g).weiner_index());
  }
}
