/*
 * Exercise 3.31, p. 117.
 *
 * Write a program to define an array of ten ints. Give each element
 * the same value as its position in the array.
 */

#include <cstddef>
#include <iostream>

int main(int, char**) {
  // init ia
  constexpr std::size_t Sz = 10;
  int ia[Sz];
  for (std::size_t i = 1; i <= Sz; ++i) {
    ia[i - 1] = i;
  }

  // print ia
  for (std::size_t i = 0; i < Sz; ++i) {
    std::cout << ia[i] << " ";
  }
  std::cout << std::endl;
  return 0;
}
