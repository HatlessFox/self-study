/*
 * Exercise 3.21, p. 110.
 *
 * Redo the first exercise from 3.3.3 (p. 105) using iterators.
 */

#include <vector>
#include <iostream>

void print_vector(const std::vector<int> &vi) {
  std::cout << "sz: " << vi.size() << "; el: ";
  for (auto it = vi.begin(); it != vi.end(); ++it) {
    std::cout << *it << " ";
  }
  std::cout << std::endl;
}

int main(int, char**) {
  std::vector<int> v3(10, 42);
  std::vector<int> v5{10, 42};
  print_vector(v3);
  print_vector(v5);
  return 0;
}
