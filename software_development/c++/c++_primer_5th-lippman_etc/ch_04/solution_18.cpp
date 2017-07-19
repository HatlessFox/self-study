/*
 * Exercise 4.18, p. 150.
 *
 * What would happen if the while loop on page 148 that prints the elements from
 * a vector used the prefix increment operator?
 */

/*
 * prints [beg + 1 , end] -> undefined behavior
 */

#include <iostream>
#include <vector>

int main(int, char**) {
  auto v = std::vector<int>{1, 2, 3, 4};
  auto pbeg = v.begin();
  while (pbeg != v.end() && *pbeg >= 0) {
    std::cout << *++pbeg << std::endl;
  }
  return 0;
}
