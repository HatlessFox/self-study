package chapter_4.section_1;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SymbolGraph;

/*
 * Exercise 4.1.25
 * 
 * Modify DegreesOfSeparation to take an int value y as a command-line argument and ignore movies
 * that are more than y years old.
 */
public class Solution_25 {
  
  public static class YearMoviesRejectorBFS {
    private int src;
    private int path_to[];
    private boolean known[];
    
    public YearMoviesRejectorBFS(String source, SymbolGraph sg, int oldest_year) {
      Graph g = sg.G();
      path_to = new int[g.V()];
      known = new boolean[g.V()];
      boolean is_movie[] = new boolean[g.V()];
      
      src = sg.index(source);
      Queue<Integer> frontier = new LinkedList<>();
      frontier.add(src);
      known[src] = true;
      // The graph is bipartite, assume start from an actor 
      is_movie[src] = false;
      
      while (!frontier.isEmpty()) {
        int v = frontier.poll();
        for (int adj : g.adj(v)) {
          if (known[adj]) {
            if (is_movie[adj] == is_movie[v]) {
              throw new IllegalArgumentException("Given graph is not bipartite");
            }
            continue;
          }

          is_movie[adj] = !is_movie[v];
          if (is_movie[adj] && shouldSkipMovie(sg.name(adj), oldest_year)) {
            continue;
          }
          frontier.add(adj);
          known[adj] = true;
          path_to[adj] = v;
        }
      }
    }
    
    public boolean hasPathTo(int v) { return known[v]; }
    public Iterable<Integer> pathTo(int v) {
      Stack<Integer> path = new Stack<>();
      if (!hasPathTo(v)) { return path; }

      for (int vtx = v; vtx != src; vtx = path_to[vtx]) {
        path.push(vtx);
      }
      path.push(src);
      return path;
    }
    
    private boolean shouldSkipMovie(String movie_name, int year_limit) {
      Matcher year_matcher = Pattern.compile(".*\\((\\d{4}).*").matcher(movie_name);
      if (!year_matcher.find()) {
        throw new IllegalArgumentException("Unable to extract year from \"" + movie_name + "\"");
      }
      String extracted_year = year_matcher.group(1);
      int movie_year = Integer.parseInt(extracted_year);
      
      return movie_year < year_limit;
    }
  }
  
  public static void main(String[] args) {
    if (args.length != 3) {
      StdOut.println("Usage: <path to movies.txt> <year limit> <source>");
      System.exit(-1);
    }
    
    int oldest_year = Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(args[1]);
    
    SymbolGraph sg = new SymbolGraph(args[0], "/");
    YearMoviesRejectorBFS bfs = new YearMoviesRejectorBFS(args[2], sg, oldest_year);
    StdOut.println("BFS done.");
    
    while (!StdIn.isEmpty()) {
      String sink = StdIn.readLine();
      if (!sg.contains(sink)) {
        StdOut.println("Unknown");
        continue;
      }
      int sink_id = sg.index(sink);
      if (!bfs.hasPathTo(sink_id)) {
        StdOut.println("Not connected");
        continue;
      }
      
      for (int v : bfs.pathTo(sink_id)) {
        StdOut.println("\t" + sg.name(v));
      }
    }
  }
}
