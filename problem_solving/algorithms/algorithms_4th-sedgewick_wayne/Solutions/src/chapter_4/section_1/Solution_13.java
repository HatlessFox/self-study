package chapter_4.section_1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 4.1.13
 * 
 * Add a distTo() method to the BreadthFirstPaths API and implementation, which returns the number
 * of edges on the shortest  path from the source to a given vertex.
 * A distTo() query should run in constant time.
 */
public class Solution_13 {
  public static class BreadthFstSearch {
    private int dist_to[];
    
    private void bfs(Common.Graph g, int st) {
      boolean visited[] = new boolean[g.vtcesNm()];
      
      Queue<Integer> frontier = new LinkedList<>();
      frontier.add(st);
      visited[st] = true;
      
      dist_to[st] = 0;
      while (!frontier.isEmpty()) {
        int v = frontier.poll();
        
        for (int adj_v : g.adj(v)) {
          if (visited[adj_v]) { continue; }
          
          frontier.add(adj_v);
          visited[adj_v] = true;
          dist_to[adj_v] = dist_to[v] + 1;
        }
      }
    }
    
    public BreadthFstSearch(Common.Graph g, int st) {
      dist_to = new int[g.vtcesNm()];
      Arrays.fill(dist_to, -1);
      bfs(g, st);
    }
    
    public int distTo(int v) { return dist_to[v]; }
  }
  
  public static void main(String[] args) {
    Common.Graph g = new Common.Graph(Common.TINYG_EX_2_V_NM);
    Common.initWithManualTinyGex2(g);
    
    BreadthFstSearch srch = new BreadthFstSearch(g, 0);
    for (int vi = 0; vi < g.vtcesNm(); ++vi) {
      StdOut.printf("%s is %d edges apart from 0\n", vi, srch.distTo(vi));
    }
  }
}
