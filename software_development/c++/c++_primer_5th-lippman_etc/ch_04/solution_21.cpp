/*
 * Exercise 4.21, p.152.
 *
 * Write a program to use a conditional operator to find the elements
 * in a vector <int> that have odd value and double the value
 * of each such element.
 */

#include <vector>
#include <iostream>

int main(int, char**) {
  auto vi = std::vector<int>{1, 2, 3, 4, 5, 6}, vii = std::vector<int>{};
  for (auto &e : vi) {
    vii.push_back(e % 2 ? 2*e : e);
  }
  for (auto &e : vii) { std::cout << e << " "; }
  std::cout << std::endl;
}
