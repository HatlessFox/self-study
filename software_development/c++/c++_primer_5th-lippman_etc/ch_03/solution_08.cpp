/*
 * Exercise 3.8, p. 96.
 *
 * Rewrite the program in the first exercise (3.6), first use a while and again
 * using a traditional for loop.
 * Which of the 3 approaches do you prefer and why?
 */

/* Answer: I prefer range for loop, since it doesn't require
 *         manual index managing.
 */

#include <string>
#include <iostream>

std::string xify_range_for(std::string s) {
  for (char &c : s) {
    c = 'X';
  }
  return s;
}

std::string xify_while(std::string s) {
  std::string::size_type i = 0;
  while (i < s.size()) {
    s[i++] = 'X';
  }
  return s;
}

std::string xify_plain_for(std::string s) {
  for (decltype(s.size()) i = 0; i < s.size(); ++i) {
    s[i] = 'X';
  }
  return s;
}

int main(int, char**) {
  std::string origin;
  std::cin >> origin;

  std::cout << xify_range_for(std::string(origin)) << std::endl;
  std::cout << xify_while(std::string(origin)) << std::endl;
  std::cout << xify_plain_for(std::string(origin)) << std::endl;
  return 0;
}
