/*
 * Exercise 4.23, p.152.
 *
 * The following expression fails to compile due to operator precedence,
 * explain why. How yould you fix it?
 */

#include <string>
#include <iostream>

int main(int, char**) {
  // Error: string (s+s[...])-char/int('s') operator== is undefined.
  /*
  std::string s = "word";
  std::string pl = s + s[s.size() - 1] == 's' ? "" : "s";
  */
  std::string s = "word";
  std::string pl = s + (s[s.size() - 1] == 's' ? "" : "s");
  std::cout << pl << std::endl;
}
