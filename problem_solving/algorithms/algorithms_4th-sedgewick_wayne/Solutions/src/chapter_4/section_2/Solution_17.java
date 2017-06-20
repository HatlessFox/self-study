package chapter_4.section_2;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.KosarajuSharirSCC;
import edu.princeton.cs.algs4.StdOut;

/*
 * How many strong components are there in the digraph on page 591?
 */
public class Solution_17 {
  
  public static void main(String[] args) {
    StdOut.println(new KosarajuSharirSCC(Utils.p591digraph()).count());
    if (args.length == 0) { return; }

    // comparison of the p591 digraph with a digraph from a given file.
    Solution_04.EdgeCheckDigraph ug = new Solution_04.EdgeCheckDigraph(Utils.p591digraph());
    Solution_04.EdgeCheckDigraph mg = new Solution_04.EdgeCheckDigraph(new Digraph(new In(args[0])));
    for (int v = 0; v < mg.V(); ++v) {
      for (int w = 0; w < mg.V(); ++w) {
        if (mg.hasEdge(v, w) != ug.hasEdge(v, w)) {
          StdOut.println(v + " -> " + w + " (" + mg.hasEdge(v, w) + ", " + ug.hasEdge(v, w) + ")");
        }
      }
    }
  }
}
