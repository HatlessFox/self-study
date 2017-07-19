/*
 * Exercise 4.27, p.156.
 *
 * What is the result of each of these expressions?
 */

#include <iostream>

int main(int, char**) {
  unsigned long ul1 = 3, ul2 = 7;
  std::cout << (ul1 & ul2) << std::endl;  // 4
  std::cout << (ul1 && ul2) << std::endl; // 1
  std::cout << (ul1 | ul2) << std::endl;  // 7
  std::cout << (ul1 || ul2) << std::endl; // 1
}
