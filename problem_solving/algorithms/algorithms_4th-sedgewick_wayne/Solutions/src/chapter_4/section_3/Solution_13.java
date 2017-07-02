package chapter_4.section_3;

/*
 * Exercise 4.3.13
 * 
 * Give a counterexample that shows why the following strategy does not necessarily find the MST:
 * 'Start with any vertex as a single-vertex MST, then add V-1 edges to it, always taking next
 * a min-weight edge incident to the vertex most recently added to the MST.'
 */
public class Solution_13 {

  /*
   * 1--(1)--2--(4)--4
   *  \     /
   *  (2) (3)
   *    \/
   *    3
   *    
   * Reason: the given strategy doesn't takes into account a graph structure (cycles).
   */
}
