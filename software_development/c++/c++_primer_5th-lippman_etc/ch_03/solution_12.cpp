/*
 * Exercise 3.12, p. 100.
 *
 * Which, if any, of the following vector definitions are in error?
 * For those that are legal, explain what the definition does.
 * For those that are not legal, explain why they are illegal.
 */

#include <vector>
#include <string>
#include <iostream>

int main(int, char**) {
  using std::vector;
  using std::string;

  /* (a) */
  vector<vector<int>> ivec; // ok, vector of int vectors
  /* (b) */
  //vector<string> svec1 = ivec; // illegal, unable to convert vvi to vs
  /* (c) */
  vector<string> svec2(10, "null"); // ok, vector of strings (10 "null"s)
  return 0;
}
