/*
 * Exercise 4.13, p.147.
 *
 * What are the values of i and d after each assignment?
 */

#include <iostream>

int main(int, char**) {
  int i;
  double d;
  d = i = 3.5;
  std::cout << "d = " << d << "; i = " << i << std::endl;
  i = d = 3.5;
  std::cout << "i = " << i << "; d = " << d << std::endl;
  return 0;
}
