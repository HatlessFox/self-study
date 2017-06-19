package chapter_4.section_2;

/*
 * Exercise 4.2.13
 * 
 * Prove that 2 vertices in a digraph G are in the same strong component iff there is
 * a directed cycle (not necessary simple) containing both of them.
 */
public class Solution_13 {

  /* 
   * = belong to the same strong component -> are in cycle =
   * 
   * If v, w belong to the same strong component, they are strongly connected =>
   * exist paths from v to w and from w to v. The paths can be combined into an arbitrary directed
   * cycle (a chain is not possible since different edges are used by paths because G is directed).
   * =QED=
   */
  
  /* 
   * = are in cycle -> belong to the same strong component =
   * 
   * If v, w belong are in cycle, there are paths from v to w and vice versa =>
   * v and w are strongly connected.
   * =QED=
   */
}
