/*
 * Exercise 3.25, p. 113.
 *
 * Rewrite the grade clustering program from 3.3.3 (p. 104) using iterators
 * instead of subscripts.
 */

#include <vector>
#include <iostream>

int main(int, char**) {
  std::vector<unsigned> scores(11, 0);
  decltype(scores)::value_type grade;
  while (std::cin >> grade) {
    if (100 < grade) { continue; }
    ++scores[grade / 10];
  }

  for (auto s : scores) { std::cout << s << " "; }
  std::cout << std::endl;
}
