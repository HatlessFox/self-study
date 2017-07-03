/*
 * Exercise 3.10, p. 96.
 *
 * Write a program that reads a string of characters including punctuation
 * and writes what was read but with the punctuation removed.
 */

#include <string>
#include <iostream>
#include <cctype>

auto filter_punctuation(const std::string &data) {
  std::string no_punc;
  for (auto c : data) {
    if (ispunct(c)) { continue; }
    no_punc.push_back(c);
  }
  return no_punc;
}

int main(int, char**) {
  std::string data;
  std::getline(std::cin, data);
  std::cout << filter_punctuation(data) << std::endl;
  return 0;
}
