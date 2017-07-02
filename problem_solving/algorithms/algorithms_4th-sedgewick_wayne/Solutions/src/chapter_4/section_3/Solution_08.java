package chapter_4.section_3;

/*
 * Exercise 4.3.8
 * 
 * Prove the following, known as the cycle property: Given any cycle in an edge-weighted graph
 * (all edge weights distinct), the edge of maximum weight in the cycle does not belong to the MST
 * of the graph.
 */
public class Solution_08 {

  /*
   * [Proof by construction]
   * In order to be in the MST the max-edge must be at least in cut.
   * => some vertices from the cycle in one set, the rest is in another one
   * => exist an edge from the cycle that is also in cut
   * =(max-edge has max weight)=> the arbitrary edge from cycle is more preferable than max-edge
   * => all edges from the cycle are added before the max-edge
   * => adding max-edge creates a cycle in the MST
   * => max-edge is not in the MST
   */
}
