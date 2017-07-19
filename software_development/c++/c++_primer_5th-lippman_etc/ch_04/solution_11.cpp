/*
 * Exercise 4.11, p.144.
 *
 * Write an expression that tests four values, a, b, c, and d, and ensures
 * that a is greater than b, whiceh is greater than c, which is greater than d.
 */

#include <iostream>

int main(int, char**) {
  int a, b, c, d;
  std::cin >> a >> b >> c >> d;
  bool is_ok = (a < b) && (b < c) && (c < d);
  std::cout << (is_ok ? "ok" : "not ok") << std::endl;
  return 0;
}
