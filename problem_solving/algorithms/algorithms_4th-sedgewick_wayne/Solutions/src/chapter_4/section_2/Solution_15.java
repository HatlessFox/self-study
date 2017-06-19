package chapter_4.section_2;

/*
 * Exercise 4.2.15
 * 
 * Let C be a strong component in a digraph G and let v be any vertex not in C.
 * Prove that if there is an edge e pointing from any vertex in C to v,
 * then vertex v appears before every vertex in C in the reverse postoredr of G^R
 */
public class Solution_15 {

  /*
   * According to 4.2.12 strong components stay same in a digraph is reversed.
   * If e points from w in C to v in G, then e' points from v to w in G^R.
   * According to 4.2.12, e':v->w, v is not in C
   * => v appears before any vertex in C in reverse postorder of G^R.
   */
}
