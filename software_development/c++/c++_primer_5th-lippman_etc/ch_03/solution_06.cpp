/*
 * Exercise 3.6, p. 96.
 *
 * Use a range for to change all the characters in a string to X.
 */

#include <string>
#include <iostream>

std::string xify(std::string dst) {
  for (auto &c : dst) { c = 'X'; }
  return dst;
}

int main(int, char**) {
  std::string s;
  std::cin >> s;
  std::cout << xify(s) << std::endl;
  return 0;
}
