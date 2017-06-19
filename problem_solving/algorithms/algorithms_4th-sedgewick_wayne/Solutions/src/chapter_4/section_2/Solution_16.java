package chapter_4.section_2;

/*
 * Exercise 4.2.16
 * 
 * Given a digraph G, prove that the first vertex in the reverse postorder of G is in a strong
 * component that is a _source_ of G's kernel DAG. Then, prove that the first vertex in the reverse
 * postorder of G^R is in a strong component that is a _sink_ of G's kernel DAG.
 */
public class Solution_16 {

  /* 1. The 1st vertex in the r-postorder of G is in a SC that is a _source_ of G's kernel DAG.
   * 
   * Proof By Contradiction.
   * {Suppose the first vertex w is in SC1 and SC1 is not a source}
   * SC1 is not a source
   * -> (by _source_ definition) exists a SC that points to SC1
   * -> (by SC definition) exists a v not in SC1 that points to it
   * -> (by 4.2.14) v appears before every vertex in SC1 in r-postorder of G
   * (?!) v must be before w, but w is the first.
   * 
   * =QED=
   */
  
  /*
   * 2. The 1st vertex in the r-postorder of G^R is in a SC that is a _sink_ of G's kernel DAG.
   * 
   * Proof By Contradiction.
   * {Suppose the first vertex w is in SC1 and SC1 is not a sink}
   * SC1 is not a sink
   * -> (by _sink_ definition) exists a SC that is pointed by SC1
   * -> (by SC definition) exists a v not in SC1 that is pointed from SC1
   * -> (by 4.2.15) v appears before every vertex in SC1 in r-postorder of G^R
   * (?!) v must be before w, but w is the first.
   * 
   * =QED=
   */
}
