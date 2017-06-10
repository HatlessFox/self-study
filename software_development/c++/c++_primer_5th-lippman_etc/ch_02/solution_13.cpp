/*
 * Exercise 2.13, p. 49
 *
 * What is the value of j?
 */

#include <iostream>

int i = 42;
int main(int, char**) {
  int i = 100;
  int j = i;

  std::cout << j << std::endl; // 100
  return 0;
}
