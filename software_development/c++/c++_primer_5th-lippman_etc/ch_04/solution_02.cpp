/*
 * Exercise 4.2, p.137.
 *
 * Using Table 4.12 (p. 166), parenthesize the following expressions
 * to indicate the order in wihch the operands are grouped.
 */

#include <iostream>
#include <vector>

int main(int, char**) {
  std::vector<int> vec = {1, 2, 3, 4, 5};
  {
    std::cout << * vec.begin() << " " << *(vec.begin()) << std::endl;
  }
  {
    std::cout << * vec.begin() + 1 << " " << (*(vec.begin())) + 1 << std::endl;
  }
  return 0;
}
