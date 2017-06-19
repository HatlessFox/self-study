package chapter_4.section_2;

/*
 * Exercise 4.2.20
 * 
 * True or false: the reverse postorder of a digraph's reverse is the same as
 * the postorder of the digraph.
 */
public class Solution_20 {

  /*
   * False: The first vertex in the reverse postorder of a digraph's reverse is guaranteed
   *        to be a member of a sink SCC in a digraph (~4.2.15).
   *        The first vertex in the postorder of the digraph is not necessary a sink
   *        (depends on traversal order):
   *        
   *        0--->1->3, if DFS starts from 1, possible postorder - 0 2 1 3, 0 is not a sink. 
   *        ^-2</
   */
}
