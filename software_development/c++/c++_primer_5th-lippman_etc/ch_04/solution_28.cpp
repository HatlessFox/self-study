/*
 * Exercise 4.28, p.158.
 *
 * Write a program to print the size of each of the build-in types.
 */

#include <iostream>

int main(int, char**) {
  #define PRINT_TYPE(type)\
    std::cout << #type << ": " << sizeof(type) << std::endl;

  PRINT_TYPE(char);
  PRINT_TYPE(int);
  PRINT_TYPE(unsigned);
  PRINT_TYPE(long);
  PRINT_TYPE(float);
  PRINT_TYPE(double);
  return 0;
}
