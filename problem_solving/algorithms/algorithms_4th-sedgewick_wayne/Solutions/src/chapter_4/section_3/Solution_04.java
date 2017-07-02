package chapter_4.section_3;

/*
 * Exercise 4.3.4
 * 
 * Consider the assertion that an edge-weighted graph has a unique MST iff its edge weights
 * are distinct. Give a proof or a counter example.
 */
public class Solution_04 {

  /*
   * Solution 4.3.3  proves <= direction, so we need to verify "unique MST => distinct edges".
   * This is not true. For example,
   * 
   * 1-(1)-2-(1)-3
   * 
   * Edges are not distinct, the MST is unique.
   * 
   * (Informal: uniqueness of MST says nothing since best edges with the same weight can be
   *            part of different cuts.
   */
}
