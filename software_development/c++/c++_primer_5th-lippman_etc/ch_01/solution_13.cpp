/*
 * Excercise 1.13, p. 14
 *
 * Solve 1.9 and 1.10 using _for_ loops.
 */

#include <iostream>

int main(int, char**) {
  std::cout << "=== 1.13 -> 1.9 ===" << std::endl;
  int sum = 0;
  for (int i = 50; i <= 100; ++i) {
    sum += i;
  }
  std::cout << sum << std::endl;

  std::cout << "=== 1.13 -> 1.10 ===" << std::endl;
  for (int i = 10; 0 <= i; --i) {
    std::cout << i << " ";
  }
  std::cout << std::endl;
}
