/*
 * Excercise 1.8, p. 11
 *
 * Indicat which, if any, of the following output statements are legal.
 */

#include <iostream>

int main(int, char**) {
  std::cout << "/*";
  std::cout << "*/";
  // Incorrect -> // std::cout << /* "*/" */;
  std::cout << /* "*/" /* "/*" */;
  return 0;
}
