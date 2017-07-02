package chapter_4.section_3;

/*
 * Exercises 4.3.1
 * 
 * Prove that you can rescale the weights by adding a positive constant to all of them or
 * multiplying them all bya positive constant without affecting the MST.
 */
public class Solution_01 {

  /*
   * MST weight is a sum of V-1 edges. Is order to change MST the operation must change
   * the order of edges in a cut. Addition of a positive constant to all edges doesn't make one edge
   * greater than another as well as multiplication. So MST doesn't change. QED
   */
}
