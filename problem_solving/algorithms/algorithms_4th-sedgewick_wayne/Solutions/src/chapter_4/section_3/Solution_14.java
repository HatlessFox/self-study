package chapter_4.section_3;

/*
 * Exercise 4.3.14
 * 
 * Given an MST for an edge-weighted graph G, suppose that an edge in G
 * that does not disconnect G is deleted. Describe how to find an MST of the new graph
 * in time proportional to E.
 */
public class Solution_14 {

  /*
   * Since G\{e} in connected, there is an MST'.
   * If e is not in the given MST, no actions are required.
   * Otherwise,
   * 1. MST is split on 2 sets of vertices (can be found by BFS on the MST in O(E)).
   * 2. the cut that splits G\{e} into the set must be found (O(E)),
   * 3. {min edge from the cut} U MST\{e} = MST'
   */
}
