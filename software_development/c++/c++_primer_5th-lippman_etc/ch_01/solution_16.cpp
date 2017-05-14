/*
 * Excercise 1.16, p. 17
 *
 * Write your own version of a program that prints
 * the sum of a set of integers road from cin.
 */

#include <iostream>

int main(int, char**) {
  int v, sum = 0;
  while (std::cin >> v) {
    sum += v;
  }
  std::cout << sum << std::endl;
  return 0;
}
