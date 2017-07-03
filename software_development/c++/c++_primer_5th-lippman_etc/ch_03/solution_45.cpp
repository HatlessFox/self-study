/*
 * Exercise 3.44, p. 130.
 *
 * Rewiret the programs again, this time using auto.
 */

#include <iostream>

int main(int, char**) {
  int ia[3][4] = {{0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}};

  std::cout << "== Range-for" << std::endl;
  for (auto &row : ia) {
    for (int &i : row) {
      std::cout << i << " " ;
    }
    std::cout << std::endl;
  }

  std::cout << "== Index-for" << std::endl;
  for (auto i = 0; i < 3; ++i) {
    for (auto j = 0; j < 4; ++j) {
      std::cout << ia[i][j] << " ";
    }
    std::cout << std::endl;
  }

  std::cout << "== Pointer-for" << std::endl;
  for (auto p = ia; p != ia + 3; ++p) {
    for (auto q = *p; q != *p + 4; ++q) {
      std::cout << *q << " ";
    }
    std::cout << std::endl;;
  }
  return 0;
}
