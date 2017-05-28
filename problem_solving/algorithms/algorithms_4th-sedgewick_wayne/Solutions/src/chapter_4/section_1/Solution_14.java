package chapter_4.section_1;

/*
 * Exercise 4.1.14
 * 
 * Suppose you use a stack instead of a queue when running breadth-first search.
 * Does it still computes shortest paths?
 */
public class Solution_14 {

  /* 
   * No, it doesn't.
   * 
   * Counterexample (edges are processed in increasing order of vertex id):
   * 
   * 1 - 2 - 3 - 4
   *  \         /
   *   5-------
   */
}
