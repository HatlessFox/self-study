/*
 * Exercise 4.25, p.156.
 *
 * What is the value of ~'q' << 6 on a machine with 32-bit ints and 8 bit chars,
 * that uses Latin-1 character set in which 'q' has the bit pattern 01110001?
 */

#include <iostream>
#include <cstdint>

void print_bits(char c) {
  for (unsigned i = 0; i < 8; ++i) {
    std::cout << ((c & (1 << i)) ? 1 : 0);
  }
  std::cout << std::endl;
}

void print_bits(int32_t c) {
  for (unsigned i = 0; i < 32; ++i) {
    std::cout << ((c & (1 << i)) ? 1 : 0);
  }
  std::cout << std::endl;
}

int main(int, char**) {
  print_bits('q');
  print_bits(~'q');
  print_bits(~'q' << 6);
}
