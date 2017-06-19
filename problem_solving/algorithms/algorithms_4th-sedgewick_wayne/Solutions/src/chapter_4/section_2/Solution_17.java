package chapter_4.section_2;

import edu.princeton.cs.algs4.KosarajuSharirSCC;
import edu.princeton.cs.algs4.StdOut;

/*
 * How many strong components are there in the digraph on page 591?
 */
public class Solution_17 {
  
  public static void main(String[] args) {
    StdOut.println(new KosarajuSharirSCC(Utils.p591digraph()).count());
  }
}
