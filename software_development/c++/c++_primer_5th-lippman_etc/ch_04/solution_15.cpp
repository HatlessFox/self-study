/*
 * Exercise 4.15, p.147.
 *
 * The following assignment is illegal. Why? How would you correct it?
 */

#include <iostream>

int main(int, char**) {
  double dval; int ival, *pi;
  //dval = ival = pi = 0; // unable to assignd int* to int
  dval = ival = 0;
  pi = nullptr;
  return 0;
}
