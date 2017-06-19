package chapter_4.section_2;

/*
 * Exercise 4.2.21
 * 
 * True or false: if we consider the vertices of a digraph G (or its reverse G^R) in postorder,
 * then vertices in the same strong component will be consecutive in that oder.
 */
public class Solution_21 {

  /*
   * False. Counterexample
   * 
   * 0->1->2->3. Start DFS from 2: (goto 0, goto 1, fin 1, fin 0, goto 3, fin 3, fin 2) 1 0 3 2.
   * ^     |
   *  \---/
   */

}
