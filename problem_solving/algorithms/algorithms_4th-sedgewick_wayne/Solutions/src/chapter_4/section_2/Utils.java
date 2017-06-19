package chapter_4.section_2;

import java.util.stream.StreamSupport;

import edu.princeton.cs.algs4.Digraph;

public class Utils {

  public static Digraph digraph_tiny2() {
    // from page 596
    Digraph g = new Digraph(12);
    g.addEdge(8, 4);
    g.addEdge(2, 3);
    g.addEdge(0, 5);
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
    g.addEdge(3, 10);
    g.addEdge(8, 1);
    g.addEdge(4, 1);
    return g;
  }
  
  public static Digraph dag() {
    Digraph dag = new Digraph(13);
    dag.addEdge(0, 1);
    dag.addEdge(0, 5);
    dag.addEdge(0, 6);
    dag.addEdge(2, 0);
    dag.addEdge(2, 3);
    dag.addEdge(3, 5);
    dag.addEdge(5, 4);
    dag.addEdge(6, 4);
    dag.addEdge(6, 9);
    dag.addEdge(7, 6);
    dag.addEdge(8, 7);
    dag.addEdge(9, 10);
    dag.addEdge(9, 11);
    dag.addEdge(9, 12);
    dag.addEdge(11, 12);
    return dag;
  }
  
  private static void addEdges(Digraph g, int v, int[] dsts) {
    for (int dst : dsts) { g.addEdge(v, dst); }
  }
  
  public static Digraph p591digraph() {
    Digraph g = new Digraph(50);
    addEdges(g, 0, new int[]{7, 34});
    addEdges(g, 1, new int[]{22, 22, 49, 45, 14, 21});
    addEdges(g, 2, new int[]{19, 25, 33});
    addEdges(g, 3, new int[]{36, 42, 27, 4, 17});
    addEdges(g, 4, new int[]{27, 17, 17});
    addEdges(g, 5, new int[]{43});
    addEdges(g, 6, new int[]{28, 28, 13, 13});
    addEdges(g, 7, new int[]{44, 41});
    addEdges(g, 8, new int[]{19, 48});
    addEdges(g, 9, new int[]{9, 30, 46});
    addEdges(g, 10, new int[]{28, 28, 28, 29, 29, 41, 7, 0, 34});
    addEdges(g, 11, new int[]{30, 21});
    addEdges(g, 12, new int[]{26, 21, 21, 11, 9});
    addEdges(g, 13, new int[]{22, 23, 47});
    addEdges(g, 14, new int[]{8, 48, 21});
    addEdges(g, 15, new int[]{49, 34, 8});
    addEdges(g, 16, new int[]{9});
    addEdges(g, 17, new int[]{20, 24, 48});
    addEdges(g, 18, new int[]{42, 28, 6, 32});
    addEdges(g, 19, new int[]{15, 40});
    addEdges(g, 20, new int[]{3, 38, 35, 46});
    addEdges(g, 21, new int[]{22});
    addEdges(g, 22, new int[]{6});
    addEdges(g, 23, new int[]{22, 21, 11});
    addEdges(g, 24, new int[]{4, 5, 38});
    addEdges(g, 25, new int[]{2, 34});
    addEdges(g, 26, new int[]{9, 12, 48, 16});
    addEdges(g, 27, new int[]{42, 31, 32, 5, 24});
    addEdges(g, 28, new int[]{39, 44, 29, 22});
    addEdges(g, 29, new int[]{49, 22});
    addEdges(g, 30, new int[]{37, 23});
    addEdges(g, 31, new int[]{18, 32});
    addEdges(g, 32, new int[]{5, 6, 13, 47, 37});
    addEdges(g, 33, new int[]{8, 19, 2});
    addEdges(g, 34, new int[]{29, 40, 2});
    addEdges(g, 35, new int[]{37, 9, 46});
    addEdges(g, 36, new int[]{42, 20});
    addEdges(g, 37, new int[]{5, 47, 47, 9, 35});
    addEdges(g, 38, new int[]{37, 38, 35});
    addEdges(g, 39, new int[]{42, 18});
    addEdges(g, 40, new int[]{15});
    addEdges(g, 41, new int[]{44, 28});
    addEdges(g, 42, new int[]{31});
    addEdges(g, 43, new int[]{37, 38});
    addEdges(g, 44, new int[]{39});
    addEdges(g, 45, new int[]{49, 15, 8, 14, 14});
    addEdges(g, 46, new int[]{16});
    addEdges(g, 47, new int[]{23, 30});
    addEdges(g, 48, new int[]{12, 21, 33, 33});
    addEdges(g, 49, new int[]{49, 22, 34});
    return g;
  }
  
  public static String toString(Iterable<Integer> ints) {
    StringBuilder sb = new StringBuilder();
    StreamSupport.stream(ints.spliterator(), false).forEach(i -> sb.append(i + " "));
    return sb.toString();
  }
}
