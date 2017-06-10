/*
 * Exercise 2.16, p. 51
 *
 * Which of the following assignements are invalid?
 */

#include <iostream>

int main(int, char**) {
  int i = 0, &r1 = i;
  double d = 0, &r2 = d;

  r2 = 3.14159; // (a) - valid
  r2 = r1;      // (b) - valid, _values_ are convertible
  i = r2;       // (c) - valid
  r1 = d;       // (d) - valid, _values_ are convertible
  std::cout << i << " " << r1 << " " << d << " " << r2 << std::endl;
}
