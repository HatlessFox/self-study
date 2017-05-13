/*
 * Excercise 1.5, p. 9
 *
 * We wrote the output in one large statement. Rewrite the program to use
 * a separate statement to print each operand.
 */

#include <iostream>

int main(int, char**) {
  int a, b;
  std::cin >> a >> b;
  std::cout << "The sum of ";
  std::cout << a;
  std::cout << " and ";
  std::cout << b;
  std::cout << " is ";
  std::cout << a + b;
  std::cout << std::endl;
  return 0;
}
