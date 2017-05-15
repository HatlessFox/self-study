/*
 * Exercise 1.23, p. 22
 *
 * Read several transactions and count how many transactions
 * occur for each ISBN.
 */

#include <iostream>
#include "misc/Sales_item.h"

int main(int, char**) {
  Sales_item prev_item, curr_item;
  int cnt = 0;
  bool curr_item_available = bool(std::cin >> curr_item);

  while (curr_item_available) {
    ++cnt;
    prev_item = curr_item;
    curr_item_available = bool(std::cin >> curr_item);
    if (!curr_item_available || curr_item.isbn() != prev_item.isbn()) {
      std::cout << prev_item.isbn() << ": " << cnt << std::endl;
      cnt = 0;
    }
  }

  return 0;
}
