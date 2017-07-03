/*
 * Exercise 3.42, p. 126.
 *
 * Write a program to copy a vector of ints into an array of ints.
 */

#include <iostream>
#include <vector>
#include <algorithm>

int main(int, char**) {
  auto vi = std::vector<int>{1, 2, 3, 4};
  int a[4];
  std::copy(vi.begin(), vi.end(), std::begin(a));
  for (auto &i : a) {
    std::cout << i << " ";
  }
  std::cout << std::endl;
}
