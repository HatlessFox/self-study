/*
 * Exercise 3.29, p. 115.
 *
 * List some of the drawback of using an array instead of a vector.
 */

/*
 * - an array isn't shrinked/expanded automatically like a vector;
 * - unable to pass an array of arbitrary size to a function
 *   (array size is a part of its type);
 * - default-initialized array values of a built-in type are indeterminate.
 */

#include <iostream>

int main(int, char**) {
  std::cout << "Look at the source" << std::endl;
  return 0;
}
