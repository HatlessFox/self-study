/*
 * Exercise 3.26, p. 113.
 *
 * In the binary search program on page 112, why did we write
 * mid = beg + (end - beg) / 2; instead of mid = (beg + end) / 2; ?
 */

/* Answore: since sum of two iterators is illegal */

#include <vector>

int main(int, char**) {
  // std::vector<int> vi(10);
  // vi.begin() + vi.begin();
  return 0;
}
