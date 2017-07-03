/*
 * Exercise 3.44, p. 130.
 *
 * Rewrite the programs from the previous exercises using a type alias
 * for the type of the loop control variables.
 */

#include <iostream>

int main(int, char**) {
  int ia[3][4] = {{0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}};

  std::cout << "== Range-for" << std::endl;
  using int_row_4 = int (&)[4];
  for (int_row_4 row : ia) {
    for (int &i : row) {
      std::cout << i << " " ;
    }
    std::cout << std::endl;
  }

  std::cout << "== Index-for" << std::endl;
  using index = std::size_t;
  for (index i = 0; i < 3; ++i) {
    for (index j = 0; j < 4; ++j) {
      std::cout << ia[i][j] << " ";
    }
    std::cout << std::endl;
  }

  std::cout << "== Pointer-for" << std::endl;
  using int_row_4_ptr = int (*)[4];
  for (int_row_4_ptr p = ia; p != ia + 3; ++p) {
    for (int *q = *p; q != *p + 4; ++q) {
      std::cout << *q << " ";
    }
    std::cout << std::endl;;
  }
  return 0;
}
