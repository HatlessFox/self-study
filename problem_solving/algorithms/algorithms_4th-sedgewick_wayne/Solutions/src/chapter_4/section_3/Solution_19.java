package chapter_4.section_3;

/*
 * Exercise 4.3.19
 * 
 * Suppose that you implement PrimMST but instead of using a priority queue to find the next vertex
 * to add to the tree, you scan through all V entries in the distTo[] array to find the non-tree
 * vertex with the smallest weight. What would be the order of growth of the running time for graphs
 * with V vertices and E edges? When would this method be appropriate if ever? Defend your answer.
 */
public class Solution_19 {

  /*
   * Order of growth: ~V**2
   * The method is appropriate for dense graphs (E ~ V**2)
   */
}
