/*
 * Exercise 4.10, p.144.
 *
 * Write the condition for a while loop that would read ints from
 * the standard input and stop when the value read is equal to 42.
 */

#include <iostream>

int main(int, char**) {
  int v = 0;
  while ((std::cin >> v) && v != 42) {}
  return 0;
}
