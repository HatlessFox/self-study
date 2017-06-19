package chapter_4.section_2;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.KosarajuSharirSCC;
import edu.princeton.cs.algs4.StdOut;

/*
 * Exercise 4.2.18
 * 
 * What are the strong components of a DAG?
 */
public class Solution_18 {

  /* DAG's vertices */
  
  public static void main(String[] args) {
    Digraph dag = Utils.dag();
    StdOut.println("V: " + dag.V() + "; CC: " + new KosarajuSharirSCC(dag).count());
  }
}
