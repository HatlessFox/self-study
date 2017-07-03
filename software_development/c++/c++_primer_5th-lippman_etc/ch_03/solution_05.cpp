/*
 * Exercise 3.5, p. 90.
 *
 * Write a program to read strings from the standard input, concatenating
 * what is read into one large string. Print the concatenated string.
 * Next, change the program to separate adjacent input strings by a space.
 */

#include <string>
#include <iostream>

void concat_str(const std::string &a, const std::string &b) {
  std::cout << a + b << std::endl;
}

void concat_with_space(const std::string &a, const std::string &b) {
  std::cout << a + " " + b << std::endl;
}

int main(int, char **) {
  std::string a, b;
  std::cin >> a >> b;
  //concat_str(a,b);
  concat_with_space(a, b);
  return 0;
}
