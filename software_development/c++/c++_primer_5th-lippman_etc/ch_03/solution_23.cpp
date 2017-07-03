/*
 * Exercise 3.23, p. 110.
 *
 * Write a program to create a vector with ten int elements. Using an iterator,
 * assign each element a value that is twice its current value.
 * Test your program by printing the vector.
 */

#include <vector>
#include <iostream>

int main(int, char**) {
  std::vector<int> vi;
  int v;
  for (std::vector<int>::size_type i = 0; i < 10; ++i) {
    std::cin >> v;
    vi.push_back(v);
  }

  for (auto it = vi.begin(); it != vi.end(); ++it) {
    *it = 2 * *it;
  }

  for (auto &i : vi) { std::cout << i << " "; }
  std::cout << std::endl;
  return 0;
}
