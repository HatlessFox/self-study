package chapter_4.section_1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.IntStream;

import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 4.1.16
 * 
 * The _eccentricity_ of a vertex v is the length of the shortest path from that vertex to
 * the furthest vertex v. The _diameter_ of a graph is the maximum eccentricity of any vertex.
 * The _radius_ of a graph is the smallest eccentricity of any vertex. A _center_ is a vertex whose
 * eccentricity is the radius. Implement the API on page 559.
 */
public class Solution_16 {

  public static class GraphProperties {
    protected int eccentricities[];

    public GraphProperties(Common.Graph g) {
      eccentricities = new int[g.vtcesNm()];
      for (int v = 0; v < g.vtcesNm(); ++v) {
        eccentricities[v] = eccentricity(v, g);
      }
    }

    public int diameter() {
      return IntStream.of(eccentricities).max().orElse(-1);
    }
    
    public int radius() {
      return IntStream.of(eccentricities).min().orElse(-1);
    }
    
    public int center() {
      int min_e = Integer.MAX_VALUE;
      int c = -1;
      for (int i = 0; i < eccentricities.length; ++i) {
        if (eccentricities[i] < min_e) {
          min_e = eccentricities[i];
          c = i;
        }
      }
      return c;
    }
    
    protected int eccentricity(int v, Common.Graph g) {
      int path_len[] = new int[g.vtcesNm()];
      boolean visited[] = new boolean[g.vtcesNm()];
      
      Queue<Integer> frontier = new LinkedList<>();
      path_len[v] = 0;
      frontier.add(v);
      visited[v] = true;
      
      while (!frontier.isEmpty()) {
        int vtx = frontier.poll();
        for (int adj : g.adj(vtx)) {
          if (visited[adj]) { continue; }
          path_len[adj] = path_len[vtx] + 1;
          frontier.add(adj);
          visited[adj] = true;
        }
      }
      
      return Arrays.stream(path_len).reduce(0, (res, e) -> res < e ? e : res);
    }
  }
  
  public static void main(String[] args) {
    Common.Graph g = new Common.Graph(Common.TINYG_EX_2_V_NM);
    Common.initWithManualTinyGex2(g);
    g.addEdge(1, 9);
    g.addEdge(10, 8);
    
    GraphProperties gp = new GraphProperties(g);
    StdOut.printf("Diameter: %d; Radius: %d; Center: %d\n", gp.diameter(), gp.radius(), 
                                                            gp.center());
  }
}
