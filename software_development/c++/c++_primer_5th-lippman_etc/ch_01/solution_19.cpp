/*
 * Excercise 1.19, p. 18
 *
 * Revise the solution of 1.11 so that it handles input in which
 *  the first number is smaller than the second.
 */

#include <iostream>

int main(int, char**) {
  std::cout << "Enter range (2 ints): ";
  int beg, end;
  std::cin >> beg >> end;
  if (beg > end) {
    std::cout << "<empty range>" << std::endl;
    return 0;
  }

  while (beg <= end) {
    std::cout << beg++ << std::endl;
  }
  return 0;
}
