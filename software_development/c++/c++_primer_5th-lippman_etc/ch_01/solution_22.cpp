/*
 * Exercise 1.22, p. 22
 *
 * Write a program that reads several transactions for the same ISBN.
 * Write the sum of all the transactions that were read.
 */

#include <iostream>
#include "misc/Sales_item.h"

int main(int, char**) {
  Sales_item item, sum;
  while (std::cin >> item) {
    sum += item;
  }
  std::cout << "Sum: " << sum << std::endl;
  return 0;
}
