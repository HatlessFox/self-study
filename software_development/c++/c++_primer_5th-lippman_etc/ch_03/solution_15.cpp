/*
 * Exercise 3.15, p. 102.
 *
 * Repeat the previous program but read strings this time.
 */

#include <iostream>
#include <vector>
#include <string>

void print_vs(std::vector<std::string> &vs) {
  for (auto &s : vs) {
    std::cout << s << " ";
  }
  std::cout << std::endl;
}

int main(int, char**) {
  std::string s;
  std::vector<std::string> dst;
  while (std::cin >> s) {
    dst.push_back(s);
  }
  print_vs(dst);
  return 0;
}
