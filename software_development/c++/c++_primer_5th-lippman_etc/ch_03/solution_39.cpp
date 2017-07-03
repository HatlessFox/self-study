/*
 * Exercise 3.39, p. 124.
 *
 * Write a program to campare two strings. Now write a program to compare
 * the values of two C-style character strings.
 */

#include <string>
#include <iostream>

void cmp_strings() {
  std::string a, b;
  std::cin >> a >> b;
  std::cout << "a vs b = " << (a == b ? 0 : (a < b ? -1 : 1)) << std::endl;
}

void cmp_cstrings() {
  std::string a, b;
  std::cin >> a >> b;
  const char *pa = a.c_str(), *pb = b.c_str();
  std::cout << "a vs b = " << strcmp(pa, pb) << std::endl;
}

int main(int, char**) {
  //cmp_strings();
  cmp_cstrings();
  return 0;
}
