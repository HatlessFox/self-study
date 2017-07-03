/*
 * Exercise 3.11, p. 96.
 *
 * Is the following range for legal? It so, what is the type of c?
 */

/* Answer: yes, it is legal. The type of c is a reference to const char. */

#include <string.h>
#include <iostream>
#include "../common/utils.h"

int main(int, char**) {
  const std::string s = "Keep out!";
  for (auto &c : s) { /* */ }

  for (auto &c : s) {
    std::cout << get_type<decltype(c)>() << std::endl;
    break;
  }
  return 0;
}
