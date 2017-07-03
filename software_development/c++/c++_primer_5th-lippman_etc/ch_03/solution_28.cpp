/*
 * Exercise 3.28, p. 115.
 *
 * What are the values in the following arrays?
 */

#include <iostream>
#include <string>

template <typename T>
void print_a10(T a[10]) {
  for (std::size_t i = 0; i < 9; ++i) {
    std::cout << a[i] << ", ";
  }
  std::cout << a[9] << std::endl;
}

std::string sa[10];
int ia[10];

int main(int, char**) {
  std::string sa2[10];
  int ia2[10];

  // sa - empty strings
  // ia - zeros
  // sa2 - empty strings
  // ia2 - indeterminate values
  print_a10(sa);
  print_a10(sa2);
  print_a10(ia);
  print_a10(ia2);
  return 0;
}
