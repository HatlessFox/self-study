/*
 * Exercise 3.1, p.83.
 *
 * Rewrite the exercises from p.13 and p.76
 * with appropriate _using_ declarations.
 */

/* A program that sums two ints */

#include <iostream>

int main(int, char**) {
  using std::cin;
  using std::cout;
  using std::endl;

  int i, j;
  cin >> i >> j;
  cout << "Sum = " << i + j << endl;
  return 0;
}
