/*
 * Exercise 2.11, p. 46
 *
 * Whether given statements declaration of definitions?
 */

#include <iostream>

extern int ix = 1024; // definition
int iy;               // definition
extern int iz;        // declaration

int main(int, char**) {
  std::cout << "(a) ix = " << ix << std::endl;
  std::cout << "(b) iy = " << iy << std::endl;
  //std::cout << "(c) iz = " << iz << std::endl; // linkage error
  return 0;
}
