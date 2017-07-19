/*
 * Exercise 4.36, p.165.
 *
 * Assuming i is an int and d is a double write the expression i *= d
 * so that is does integral, rather that floating point, multiplication.
 */

#include <iostream>

int main(int, char**) {
  {
    int i = 2;
    double d = 2.7;
    i *= d;
    std::cout << "Double mult: " << i << std::endl;
  }
  {
    int i = 2;
    double d = 2.3;
    i *= static_cast<int>(d);
    std::cout << "Int mult: " << i << std::endl;
  }
  return 0;
}
