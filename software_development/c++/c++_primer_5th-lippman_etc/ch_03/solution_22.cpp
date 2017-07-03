/*
 * Exercise 3.22, p. 110.
 *
 * Revise the loop that printed the first paragraph in text to instead change
 * the elements in text that correspond to the first paragrah to all uppercase.
 * After you've updated text, print its contens.
 */

// Looks like I don't understand the task completely,
// Plain paragraph uppercasing was implemented.

#include <iostream>
#include <vector>
#include <string>

void upcase_paragraph(std::vector<std::string> &pargr) {
  for (auto &s : pargr) {
    for (auto it = s.begin(); it != s.end(); ++it) {
      *it = std::toupper(*it);
    }
  }
}

int main(int, char**) {
  std::vector<std::string> vs;
  std::string s;
  while (std::cin >> s) { vs.push_back(s); }
  upcase_paragraph(vs);
  for (const auto &s : vs) { std::cout << s << " "; }
  std::cout << std::endl;
  return 0;
}
