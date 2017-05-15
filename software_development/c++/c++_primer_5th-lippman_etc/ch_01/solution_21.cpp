/*
 * Exercise 1.21
 *
 * Read two Sales_item objects that have the same ISBN and print their sum.
 */

#include <iostream>
#include "misc/Sales_item.h"

int main(int, char**) {
  Sales_item item1, item2;
  std::cin >> item1 >> item2;
  if (item1.isbn() != item2.isbn()) {
    std::cout << "Items do not match" << std::endl;
    return 0;
  }
  std::cout << "Sum: " << item1 + item2 << std::endl;
  return 0;
}
