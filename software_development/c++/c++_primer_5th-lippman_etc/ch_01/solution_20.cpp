/*
 * Exercise 1.20, p. 22
 *
 * Download Sales_item and use it to write a program that
 * reads a set of book sales transactions, writing each transaction
 * to the standard output.
 */

#include <iostream>
#include "misc/Sales_item.h"

int main(int, char**) {
  Sales_item item;
  while (std::cin >> item) {
    std::cout << item << std::endl;
  }
}
