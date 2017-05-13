/*
 * Excercise 1.11, page 13
 *
 * Write a program that prompts the user for two integers.
 * Print each number in the range specified by those two integers.
 */

#include <iostream>

int main(int, char**) {
  std::cout << "Enter range (2 ints): ";
  int beg, end;
  std::cin >> beg >> end;
  while (beg <= end) {
    std::cout << beg++ << std::endl;
  }
  return 0;
}
