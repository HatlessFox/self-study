package chapter_4.section_2;

/*
 * Exercise 4.2.22
 * 
 * True or false: if we modify the Kosaraju-Sharir algorithm to run the first depth-first search
 * in the digraph G (instead of the reverse digraph G^R) and the second depth-first search in G^R
 * (instead of G), then it will find the strong components.
 */
public class Solution_22 {
  /* True. Graph reversal doesn't change connected components
   * (imagine reversed graph given as an input: since the algorithm is correct, 
   *                                            SCC should be found).
   */
}
