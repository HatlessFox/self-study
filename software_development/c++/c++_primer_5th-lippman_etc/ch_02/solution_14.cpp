/*
 * Exercise 2.14, p. 49
 *
 * What does the program print?
 */

#include <iostream>

int main(int, char**) {
  int i = 100, sum = 0;
  for (int i = 0; i != 10; ++i) {
    sum += i;
  }
  std::cout << i << " " << sum << std::endl; // 100 45
  return 0;
}
