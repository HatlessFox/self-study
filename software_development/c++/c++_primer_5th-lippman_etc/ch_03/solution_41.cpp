/*
 * Exercise 3.41, p. 126.
 *
 * Write a program to initialize a vector from an array of ints.
 */

#include <iostream>
#include <vector>

int main(int, char**) {
  int a[] = {1, 2, 3, 6};
  for (auto &i : std::vector<int>{std::begin(a), std::end(a)}) {
    std::cout << i << " ";
  }
  std::cout << std::endl;
}
