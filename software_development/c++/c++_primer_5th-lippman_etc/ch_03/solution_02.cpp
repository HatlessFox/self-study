/*
 * Exercise 3.2, p. 90.
 *
 * Write a program to read the standard input a line at a time.
 * Modify your program to read a word at a time.
 */

#include <iostream>
#include <string>

void read_line_at_a_time() {
  while (std::cin) {
    std::string buf;
    std::getline(std::cin, buf);
    std::cout << buf << std::endl;
  }
}

void read_word_at_a_time() {
  while (std::cin) {
    std::string buf;
    std::cin >> buf;
    std::cout << buf << std::endl;
  }
}

int main(int, char**) {
  //read_line_at_a_time();
  read_word_at_a_time();
  return 0;
}
