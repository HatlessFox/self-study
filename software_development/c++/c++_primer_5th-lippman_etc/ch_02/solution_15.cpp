/*
 * Exercise 2.15, p. 51
 *
 * Which of the definitions are invalid?
 */

#include <iostream>

int main(int, char**) {
  int ival = 1.01;     // (a) - valid
  //int &rval1 = 1.01; // (b) - invalid, can't init with literal (rvalue)
  int &rval2 = ival;   // (c) - valid
  //int &rval3;        // (d) - invalid, a reference must be initialized

  std::cout << ival << " " << rval2 << std::endl;
  return 0;
}
