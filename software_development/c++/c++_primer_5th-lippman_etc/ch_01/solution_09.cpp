/*
 * Excercise 1.9, p. 13.
 *
 * Write a program that uses a _while_ to sum the numbers from 50 to 100.
 */

#include <iostream>

int main(int, char**) {
  int v = 50, sum = 0;
  while (v <= 100) {
    sum += v++;
  }
  std::cout << "Calculated: " << sum << std::endl;
  std::cout << "Expected: " << 51 * 150 / 2 << std::endl;
}
