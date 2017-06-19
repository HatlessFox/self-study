package chapter_4.section_2;

/*
 * Exercise 4.2.14
 * 
 * Let C be a strong component in a digraph G and let v be any vertex not in C.
 * Prove that if there is an edge e pointing from v to any vertex in C,
 * then vertex v appears before every vertex in C in the reverse postorder of G.
 */
public class Solution_14 {

  /*
   * Proof by cases:
   * a) {v is visited before any vertex in C}
   *    Vertices in C are visited and finished before v
   *      (since there no edge from C to v, otherwise v would be in C because of e)
   *    => v appears before every vertex in C.
   * b) {v is visited after some vertex w in C}
   *    Vertices in C have already been visited and finished
   *      (since there are paths from w to every vertex in C and no path to v)
   *    => v is finished after every vertex in C
   *    => v appears before every vertex in C
   * =QED=
   * 
   */
}
