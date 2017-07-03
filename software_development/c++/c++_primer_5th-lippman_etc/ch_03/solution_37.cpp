/*
 * Exercise 3.37, p. 124.
 *
 * What does the following program do?
 */

#include <iostream>

int main(int, char**) {
  const char ca[] = {'h', 'e', 'l', 'l', 'o'};
  const char *cp = ca;
  while (*cp) {
    std::cout << *cp << std::endl;
    ++cp;
  }
}

/* Answer: it prints 'hello' each char per line */
