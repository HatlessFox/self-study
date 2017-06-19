package chapter_4.section_2;

/*
 * Exercise 4.2.12
 * 
 * Prove that the strong components in G^R are the same as in G.
 */
public class Solution_12 {

  /*
   * Graph reversal operation doesn't influence on strong connectivity relation 
   * that splits the set of G vertices into equivalence classes (strong components):
   * if two vertices are connected in G, they are connected in G^R (v->w became v<-w, v<-w - v->w).
   * Since the relation doesn't change, the classes are the same.
   * =QED=
   */
}
