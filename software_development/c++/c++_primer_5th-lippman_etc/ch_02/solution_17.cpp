/*
 * Exercise 2.17, p. 51
 *
 * What does the code given below print?
 */

#include <iostream>

int main(int, char**) {
  int i = 0, &ri = i;
  i = 5;
  ri = 10;
  std::cout << i << " " << ri << std::endl; // 10 10
}
