package chapter_4.section_3;

/*
 * Exercise 4.3.20
 * 
 * True or false: At any point during the execution of Kruskal's algorithm, each vertex is closer
 * to some vertex in its subtree than to any vertex not in its subtree. Prove your answer.
 */
public class Solution_20 {
  
  /*
   * NB: not sure whether I got the question.
   * 
   * True. Prove by contradiction. Suppose there are no vertex in a substree that is closer to
   * a vertex (A) than some vertex (B) not in its substree.
   * => the A-B edge must be added into the interim MSF before any of A's substree edges
   * => there is an edge that connects A with C that is in A's substree and closer than B.
   * QED.
   */

}
