/*
 * Exercise 4.37, p.165.
 *
 * Rewrite each of the following old-style casts to use a named cast.
 */

#include <iostream>
#include <string>

int main(int, char**) {
  int i;
  double d;
  const std::string *ps = nullptr;
  char c;
  char *pc = &c;
  void *pv;

  // (a)
  pv = const_cast<std::string*>(ps);
  // (b)
  i = static_cast<int>(*pc);
  // (c)
  pv = static_cast<void*>(&d);
  // (d)
  pc = reinterpret_cast<char*>(pv);

  return 0;
}
