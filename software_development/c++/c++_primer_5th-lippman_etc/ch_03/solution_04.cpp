/*
 * Exercise 3.4, p. 90.
 *
 * Write a program to read two strings and report whether the strings are equal.
 * If not, report which of the two is larger. Now, change the program to report
 * whether the strings have the same length, and if not, report which is longer.
 */

#include <iostream>
#include <string>

using CSR = const std::string &;

void analyze_str_content(CSR a, CSR b) {
  if (a == b) {
    std::cout << "Strings are equal" << std::endl;
    return;
  }

  const auto &larger = a < b ? b : a;
  std::cout << larger << " is larger" << std::endl;
}

void analyze_str_length(CSR a, CSR b) {
  if (a.size() == b.size()) {
    std::cout << "Strings have the same length: " << a.size() << std::endl;
    return;
  }
  const auto &longer = a.size() < b.size() ? b : a;
  std::cout << longer << " is longer" << std::endl;
}

int main(int, char**) {
  std::string a, b;
  std::cin >> a >> b;

  //analyze_str_content(a, b);
  analyze_str_length(a, b);
  return 0;
}
