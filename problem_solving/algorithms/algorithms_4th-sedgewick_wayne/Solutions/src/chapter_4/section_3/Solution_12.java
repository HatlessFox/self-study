package chapter_4.section_3;

/*
 * Exercise 4.3.12
 * 
 * Suppose that a graph has distinct edge weights. Does it lightest edge have to belong to the MST?
 * Can its heaviest edge belong to the MST? Does a min-weight edge on every cycle have to belong to
 * the MST? Prove your answer to each question or give a counterexample.
 */
public class Solution_12 {

  /*
   * A graph has distinct edge weights.
   * (a) Does its lightest edge have to belong to the MST?
   *   Yes, it does, since it must belong to a cut, and it is the lightest in the cut,
   *   so must go to the MST.
   *   Both incident vertices can be added to the MST w/o the lightest-edge.
   * (b) Can its heaviest edge belong to the MST?
   *   Yes, it can.
   *   
   *   0--(1000)--1---(0)---0
   * (c) Does a min-weight edge on every cycle have to belong to the MST?
   *   No, it doesn't. Reason: any vertex of the cycle may be reachable by a lighter edge.
   *   
   *   1--(10)s-2
   *   |\      /|
   *   |(12)(11)|
   *   |  \ /   |
   *  (1)  4   (2)
   *    \ (3)  /
   *     \ |  /
   *       3/
   *       
   */
}
