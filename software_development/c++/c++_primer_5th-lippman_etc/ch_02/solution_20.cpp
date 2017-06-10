/*
 * Exercise 2.20, p. 56
 *
 * What does the following program do?
 */

#include <iostream>

int main(int, char**) {
  int i = 42;
  int *pi = &i;
  *pi = *pi * *pi;
  std::cout << *pi << " == " << 42*42 << std::endl; // squares the value of i
}
