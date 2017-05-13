/*
 * Excercise 1.4, p. 9
 *
 * Our program used the addition operator, +, to add two numbers.
 * Write a program that uses the multiplication operator, *,
 * to print the product instead.
 */

#include <iostream>

int main(int, char**) {
  int a, b;
  std::cin >> a >> b;
  std::cout << a * b << std::endl;
  return 0;
}
