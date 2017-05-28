package chapter_4.section_1;

/*
 * Exercise 4.1.6
 * 
 * Consider the four-vertex graph with edges 0-1, 1-2, 2-3 and 3-0. Draw an array of adjacency-lists
 * that could not have been built calling addEdge() for these edges no matter what order.
 */
public class Solution_06 {

  /*
   * Idea: loop 'the first added edge' look up.
   * 
   * 0: 1 3
   * 1: 2 0
   * 2: 3 1
   * 3: 0 2
   */
  
  public static void main(String[] args) {
    
  }
}
