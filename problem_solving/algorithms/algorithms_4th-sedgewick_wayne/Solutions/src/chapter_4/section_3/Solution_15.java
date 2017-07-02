package chapter_4.section_3;

/*
 * Exercise 4.3.15
 * 
 * Given an MST for an edge-weighted graph G and a new edge e with weight w,
 * describe how to find an MST of the new graph in time proportional to V.
 */
public class Solution_15 {

  /*
   * 1. Add the e edge to the MST => this adds a unique cycle (O(V) - loop edge detection).
   * 2. Find the max edge in the cycle (O(V)) and remove it.
   */
}
