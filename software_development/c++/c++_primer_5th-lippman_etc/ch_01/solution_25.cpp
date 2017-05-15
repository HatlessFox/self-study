/*
 * Exercise 1.25, p.25
 *
 * Using the Sales_item.h header from the Web site, compile and execute
 * the bookstore program presented in this section.
 */

#include <iostream>
#include "misc/Sales_item.h"

int main(int, char**) {
  Sales_item total;

  if (!(std::cin >> total)) {
    std::cerr << "No data?!" << std::endl;
    return -1;
  }

  Sales_item trans;
  while (std::cin >> trans) {
    if (total.isbn() == trans.isbn()) {
      total += trans;
    } else {
      std::cout << total << std::endl;
      total = trans;
    }
  }

  std::cout << total << std::endl;
}
