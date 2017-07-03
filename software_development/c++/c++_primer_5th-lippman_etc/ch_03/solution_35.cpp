/*
 * Exercise 3.35, p. 121.
 *
 * Using pointers, write a program to set the elements in an array to zero.
 */

#include <iostream>

void zero_out(int *b, int *e) {
  while (b != e) { *b++= 0; }
}

void print(int *b, int *e) {
  while (b != e) {
    std::cout << *b++ << " ";
  }
  std::cout << std::endl;
}

int main(int, char**) {
  int arr[10];
  constexpr size_t arr_sz = sizeof(arr) / sizeof(*arr);
  std::cout << "= Init =" << std::endl;
  print(arr, arr + arr_sz);
  std::cout << "zeroing out..." << std::endl;
  std::cout << "= Result =" << std::endl;
  zero_out(arr, arr + arr_sz);
  print(arr, arr + arr_sz);
}
