/*
 * Exercise 3.32, p. 117.
 *
 * Copy the array you defined in the previous exercise into another array.
 * Rewrite your program to use vectors.
 */

#include <cstddef>
#include <iostream>
#include <vector>

void do_array_stuff() {
  // init ia
  constexpr std::size_t Sz = 10;
  int ia[Sz], ia_copy[Sz];
  for (std::size_t i = 1; i <= Sz; ++i) {
    ia[i - 1] = i;
  }

  // copy
  for (std::size_t i = 0; i < Sz; ++i) {
    ia_copy[i] = ia[i];
  }

  // print ia
  for (std::size_t i = 0; i < Sz; ++i) {
    std::cout << ia_copy[i] << " ";
  }
  std::cout << std::endl;
}

void do_vector_stuff() {
  // init ia
  std::vector<int> vi, vi_copy;
  for (int i = 1; i <= 10; ++i) {
    vi.push_back(i);
  }
  for (auto &i : vi) { vi_copy.push_back(i); }
  for (auto &i : vi_copy) { std::cout << i << " "; }
  std::cout << std::endl;
}

int main(int, char**) {
  do_array_stuff();
  do_vector_stuff();
  return 0;
}
