/*
 * Exercise 4.14, p.147.
 *
 * Exprain what happens in each of the following if tests.
 */

#include <iostream>

int main(int, char**) {
  int i;
  //if (42 = i); // error, try to assign value to the 42 literal
  if ((i = 42)); // ok, assigns 42 to i
  std::cout << i << std::endl;
  return 0;
}
