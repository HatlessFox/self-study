package chapter_4.section_2;

/*
 * Exercise 4.2.11
 * 
 * Describe a family of sparse digraphs whose number of directed cycles grows exponentially in
 * the number of vertices.
 */
public class Solution_11 {

  /*
   * Answer:
   *                 +---+ 
   *                 v   |
   * Start from N=2: *-->*
   * 
   * For each next vertex (v): pick arbitrary 2 vertices (a, b), add edges (a,v), (v, b).
   * 
   * Cycles grow exponentially (by a factor of 2) since from each existent cycle we have 2:
   * after v addition, part (a)->...->(b) of a cycle can be replace with (a)->v->(b).
   * This gives an extra new cycle for each existent one.
   */
}
