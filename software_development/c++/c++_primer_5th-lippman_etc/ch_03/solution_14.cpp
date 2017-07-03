/*
 * Exercise 3.14, p. 102.
 *
 * Read a sequence of ints from cin and store those values in a vector.
 */

#include <iostream>
#include <vector>

void print_vi(std::vector<int> &vi) {
  for (auto &i : vi) {
    std::cout << i << " ";
  }
  std::cout << std::endl;
}

int main(int, char**) {
  int i = 0;
  std::vector<int> dst;
  while (std::cin >> i) {
    dst.push_back(i);
  }
  print_vi(dst);
  return 0;
}
