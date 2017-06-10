/*
 * Exercise 2.18, p. 56
 *
 * Write some code that changes the value of a pointer,
 *                              the value to which the pointer points.
 */

#include <iostream>

int main(int, char**) {
  int a = 1, b = 2;
  int *p = &a;

  std::cout << p << " ";
  p = &b;
  std::cout << p << std::endl;
  std::cout << *p << " ";
  *p = 6;
  std::cout << *p << std::endl;
}
