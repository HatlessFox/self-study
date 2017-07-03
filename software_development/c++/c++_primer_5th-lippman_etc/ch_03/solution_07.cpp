/*
 * Exercise 3.7, p. 96.
 *
 * What would happen if you define the loop control variable in
 * the previous exercite as type char? Predict the results and
 * then change your program to use a char to see if you were right.
 */

/* Answer: string won't change */

#include <string>
#include <iostream>

std::string xify(std::string dst) {
  for (char c : dst) { c = 'X'; } // NB: char instread of auto&
  return dst;
}

int main(int, char**) {
  std::string s;
  std::cin >> s;
  std::cout << xify(s) << std::endl;
  return 0;
}
