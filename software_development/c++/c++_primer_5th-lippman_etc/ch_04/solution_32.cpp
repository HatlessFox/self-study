/*
 * Exercise 4.32, p.158.
 *
 * Explain the following loop.
 */

#include <iostream>

// traverses array with ptr and index

int main(int, char**) {
  constexpr int size = 5;
  int ia[size] = {1, 2, 3, 4, 5};
  for (int *ptr = ia, ix = 0;
       ix != size && ptr != ia + size;
       ++ix, ++ptr) {
    std::cout << "[" << ix << "] = " << *ptr << std::endl;
  }
  return 0;
}
