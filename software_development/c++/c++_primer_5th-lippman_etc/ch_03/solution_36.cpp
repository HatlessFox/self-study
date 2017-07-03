/*
 * Exercise 3.36, p. 121.
 *
 * Write a program to compare two arrays for equality.
 * Write a similar program to compare two vectors.
 */

#include <cassert>
#include <vector>
#include <iterator>
#include <iostream>

template <typename T>
int cmp(T b1, T e1, T b2, T e2) {
  while (b1 != e1 && b2 != e2) {
    if (*b1 < *b2) { return -1; }
    if (*b1 > *b2) { return 1; }
    assert(*b1 == *b2);
    ++b1, ++b2;
  }
  if (b1 == e1 && b2 == e2) { return 0; }
  return b1 != e1 ? 1 : -1;
}

template <typename Container>
void test_cmp() {
  std::cout << "=> Run: " << __PRETTY_FUNCTION__ << std::endl;
  Container a4 = {1, 2, 3, 4};
  Container a4_c = {1, 2, 3, 4};
  Container a3 = {1, 2, 3};
  Container b = {1, 5};
  std::cout << "a4 vs a4_c = " << cmp(std::begin(a4), std::end(a4),
                                      std::begin(a4_c), std::end(a4_c))
            << std::endl;
  std::cout << "a4 vs a3 = " << cmp(std::begin(a4), std::end(a4),
                                    std::begin(a3), std::end(a3))
            << std::endl;
  std::cout << "a4 vs b = " << cmp(std::begin(a4), std::end(a4),
                                   std::begin(b), std::end(b)) << std::endl;
}

int main(int, char**) {
  test_cmp<int[]>();
  test_cmp<std::vector<int>>();
}
