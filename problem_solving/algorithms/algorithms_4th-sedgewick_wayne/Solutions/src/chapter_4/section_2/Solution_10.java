package chapter_4.section_2;

/*
 * Exercise 4.2.10
 * 
 * Given a DAG, does there exist a topological order that cannot result from applying
 * a DFS-based algorithm, no matter in what order the vertices adjacent to each vertex are chosen?
 */
public class Solution_10 {

  /*
   * No, it doesn't.
   * 
   * DFS implicitly builds a tree (a subgraph) with topology depending on traversal order.
   * If a subgraph (tree) is part of a graph there is always exist a traversal order that leads
   * to the tree (proof by construction: just go from the root to leaves).
   * 
   * Since a topological order is an order with no backedges, its vertices can be viewed as a tree.
   * According to the previous statement, there is a traversal order that allows DFS to build
   * any given topological order.
   */
}
