package chapter_4.section_3;

/*
 * Exercise 4.3.11
 * 
 * Determine the amount of memory used by EdgeWeightedGraph to represent a graph with V vertices
 * and E edges, using the memory-cost model of Section 1.4.
 */
public class Solution_11 {

  /*
   * EdgeWeightedGraph:
   *  + [object overhead]      <== 16 bytes
   *  + [int E, int V]         <== 4 + 4 = 8 bytes
   *  + [Array of bags, ref]   <== 8 bytes
   *    + [obj. overhead]      <== 16 bytes
   *    + [length field, int]  <== 4 bytes
   *    + [8 bytes alignment]  <== 4 bytes
   *    + [refs to Bag<Edge>]  <== 8*V
   *    
   * <A bag of smth, Linked list> - 32 + 40N
   * 
   * Edge Size: 16 (overhead) + 2*4 (ints) + 8 (double) = 32
   * 
   * Answer: 16 + 8 + 8 + 16 + 4 + 4 + 8*V + 32*V + (40)*2*E + 32*E
   *       = 56 + 40V + 112E
   */
}
