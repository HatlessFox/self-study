/*
 * Exercise 4.30, p.158.
 *
 * Parenthesize the following expressions to match the default evaluation.
 */

#include <iostream>

struct Foo { int mem[10]; };
char f() { return 0; }

int main(int, char**) {
  std::size_t x = 1, y = 2, a = 3, b = 4;
  Foo *p = new Foo{};

  std::cout << (sizeof x + y) << " vs " << ((sizeof x) + y) << std::endl;
  std::cout << (sizeof p->mem[0]) << " vs "
            << sizeof ((p->mem)[0]) << std::endl;
  std::cout << (sizeof a < b) << " vs " << ((sizeof a) < b) << std::endl;
  std::cout << (sizeof f()) << " vs " << sizeof (f()) << std::endl;

  delete p;
  return 0;
}
