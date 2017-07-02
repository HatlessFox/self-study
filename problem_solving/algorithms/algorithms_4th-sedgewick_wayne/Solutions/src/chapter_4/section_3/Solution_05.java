package chapter_4.section_3;

/*
 * Show that the greedy algorithm is valid even when edge weights are not distinct.
 */
public class Solution_05 {

  /*
   * Proof by cases:
   * 1) All minimums are distinct (unique) is their cuts. => The greedy algorithm works, since
   *    other distinct edges are not taken into account during MST building.
   * 2) A cut contains several minimums with the same value.
   *  2.1) The edges points to the same vertex. => OK, since it doesn't matter which one to choose, 
   *       the MST will have the same weight and almost the same shape.
   *  2.2) The edges points to different nodes (v, w).
   *       It this case any of edges can be picked (weight of the MST is the same). The picks are
   *       equivalent since it doesn't influence on cuts (direct and indirect) between v and w.
   */
}
