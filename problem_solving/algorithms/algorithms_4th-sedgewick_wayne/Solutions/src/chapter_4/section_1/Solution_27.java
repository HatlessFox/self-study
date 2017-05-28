package chapter_4.section_1;

/*
 * Exercise 4.1.27
 * 
 * Determine the amount of memory used by Graph to represent a graph with V vertices and E edges,
 * using the memory-cost model of SECTION 1.4.
 */
public class Solution_27 {
  /*
   * Graph
   *   + [object overhead]   <= 16 bytes
   *   + E, V [int]          <= 4 + 4 = 8 bytes
   *   + adj  [array reference]       <= 8 bytes
   *      + [object overhead]    <= 16 bytes
   *      + [length field, int]       <= 4 bytes
   *      + [8 bytes alignment]      <= 4 bytes
   *      + [references to Bag<Integer>]  <= 8*V
   * 
   * <A bag of integers, Linked list> - 32 + 64N
   * Total number of Integers, that correspond to edges - 2*E
   * Memory spent on edge storage in Bags - V*32 (Bag overhead) + 64*2E (elements)
   * 
   * Answer: 16 + 8 + 8 + 16 + 4 + 4 + 8*V + 32*V + 128*E = 56 + 40*V + 128*E
   *
   */
}
