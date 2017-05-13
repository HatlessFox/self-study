/*
 * Exercise 1.12
 *
 * What doest the final value of the following sum?
 */

#include <iostream>

int main(int, char**) {
  int sum = 0;
  for (int i = -100; i <= 100; ++i) {
    sum += i;
  }
  std::cout << "sum = " << sum << std::endl;
  return 0;
}
