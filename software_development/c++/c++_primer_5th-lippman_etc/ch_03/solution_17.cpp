/*
 * Exercise 3.17, p. 105.
 *
 * Read a sequence of words from cin and store the values in a vector.
 * After you've read all the worhs, process the vector and change each
 * word to uppercase. Print the transformed elements, eight words to a line.
 */

#include <iostream>
#include <vector>
#include <string>

auto read_words() {
  std::vector<std::string> ws;
  std::string w;
  while (std::cin >> w) {
    ws.push_back(w);
  }
  return ws;
}

void print_words(const std::vector<std::string> &vs) {
  int i = 0;
  for (auto &s : vs) {
    if (i++ == 8) {
      std::cout << std::endl;
      i = 0;
    }
    std::cout << s << " ";
  }
  std::cout << std::endl;
}

int main(int, char**) {
  auto ws = read_words();
  for (auto &s : ws) {
    for (decltype(s.size()) i = 0; i < s.size(); ++i) {
      s[i] = std::toupper(s[i]);
    }
  }
  print_words(ws);
  return 0;
}
