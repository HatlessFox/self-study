/*
 * Exercise 3.43, p. 130.
 *
 * Write three different versions of a program to print the elements of ia.
 * One version should use a range for to manage the iteration,
 * the other two should use an ordinary for loop in one case using
 * subscripts and in the other using pointers. In all three programs write all
 * the types directly. That is, do not use a type alias, auto,
 * or decltype to simplify the code.
 */

#include <iostream>

int main(int, char**) {
  int ia[3][4] = {{0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}};

  std::cout << "== Range-for" << std::endl;
  for (int (&row)[4] : ia) {
    for (int &i : row) {
      std::cout << i << " " ;
    }
    std::cout << std::endl;
  }

  std::cout << "== Index-for" << std::endl;
  for (std::size_t i = 0; i < 3; ++i) {
    for (std::size_t j = 0; j < 4; ++j) {
      std::cout << ia[i][j] << " ";
    }
    std::cout << std::endl;
  }

  std::cout << "== Pointer-for" << std::endl;
  for (int (*p)[4] = ia; p != ia + 3; ++p) {
    for (int *q = *p; q != *p + 4; ++q) {
      std::cout << *q << " ";
    }
    std::cout << std::endl;
  }
  return 0;
}
