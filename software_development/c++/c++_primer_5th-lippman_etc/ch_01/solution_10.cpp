/*
 * Excercise 1.10, page 13
 *
 * Use the decrement operator to write a _while_ that prints
 * the number from 10 down to 0
 */

#include <iostream>

int main(int, char**) {
  int v = 10;
  while (0 <= v) {
    std::cout << v-- << std::endl;
  }
  return 0;
}
