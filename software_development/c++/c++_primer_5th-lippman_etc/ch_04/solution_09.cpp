/*
 * Exercise 4.9, p.144.
 *
 * Explain the behavior of the condition in the following if:
 *   const char *cp = "Hello World";
 *   if (cp && *cp)
 */

/*
 * cp && *cp -> cp is not null and its content is not empty.
 */

#include <iostream>

void verify(const char *str) {
  if (str && *str) { std::cout << "passed" << std::endl; }
  else { std::cout << "not passed" << std::endl; }
}

int main(int, char**) {
  verify("Hello World");
  verify(nullptr);
  verify("");
  return 0;
}
