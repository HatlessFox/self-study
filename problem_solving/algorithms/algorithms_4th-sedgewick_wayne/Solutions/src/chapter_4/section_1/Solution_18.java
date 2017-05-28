package chapter_4.section_1;

import java.util.LinkedList;
import java.util.Queue;

import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 4.1.18
 * 
 * The _girth_ of a graph is the length of its shortest cycle. If a graph is acyclic, then its girth
 * is infinite. Add a method girth() to GraphProperties that returns the girth of the graph.
 * 
 * _Hint_: Run BFS from each vertex. The shortest cycle containing s is an edge between s and some
 *         vertex v concatenated with a shortest path between s and v
 *         (that doesn't use the edge s-v).
 */
public class Solution_18 {
  
  // lasso - a loop with an optional chain to start
  private static double minLassoLengthWithVtx(int start, Common.Graph g) {
    double girth = Double.POSITIVE_INFINITY;
    
    boolean visited[] = new boolean[g.vtcesNm()];
    int path_len_to[] = new int[g.vtcesNm()];
    int path_to[] = new int[g.vtcesNm()];
    
    Queue<Integer> frontier = new LinkedList<>();
    frontier.add(start);
    visited[start] = true;
    path_len_to[start] = 0;
    
    while (!frontier.isEmpty()) {
      int v = frontier.poll();
      for (int adj : g.adj(v)) {
        if (visited[adj]) {
          if (path_to[v] == adj) {
            // adj is parent of v in a search tree -> no loop
            continue;
          }
          // consider a lasso that has v, adj and start.
          girth = Math.min(path_len_to[v] + path_len_to[adj] + 1.0, girth);
          continue;
        }
        
        frontier.add(adj);
        visited[adj] = true;
        path_len_to[adj] = path_len_to[v] + 1;
        path_to[adj] = v;
      }
    }
    
    return girth;
  }
  
  public static double girth(Common.Graph g) {
    double girth = Double.POSITIVE_INFINITY;
    
    for (int v = 0; v < g.vtcesNm(); ++v) {
      // correctness: since the search is performed in a way that each vertex
      //              is considered as the start one, eventually the "loop" part of the lasso
      //              will be found.
      girth = Math.min(girth, (double)minLassoLengthWithVtx(v, g));
    }
    
    return girth;
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
     * 
     *     7---8--4
     *     |  /| /
     *     | / |/
     *     11--1
     *          
     *         9
     * 
     */
    {
      Common.Graph g = new Common.Graph(Common.TINYG_EX_2_V_NM);
      Common.initWithManualTinyGex2(g);
    
      StdOut.println(girth(g));
    }
    
    /*
     * G2:
     *   1
     *   
     *   3---2----0
     *   |   |   /
     *   4---5  /
     *   |     /
     *   6----/
     *   
     *   
     */
    {
      Common.Graph g = new Common.Graph(7);
      g.addEdge(0, 2);
      g.addEdge(2, 3);
      g.addEdge(3, 4);
      g.addEdge(4, 5);
      g.addEdge(5, 2);
      g.addEdge(4, 6);
      g.addEdge(0, 6);
      StdOut.println(girth(g));
    }
  }
}
