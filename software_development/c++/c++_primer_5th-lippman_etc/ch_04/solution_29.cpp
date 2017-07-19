/*
 * Exercise 4.29, p.158.
 *
 * Predict the output of the following code and explain your reasoning.
 * Run the program. Is the output what you expected?
 */

#include <iostream>

int main(int, char**) {
  int x[10];
  int *p = x;

  std::cout << sizeof(x) / sizeof(*x) << std::endl; // 10
  std::cout << sizeof(p) / sizeof(*p) << std::endl; // 8 / 4 = 2
  return 0;
}
