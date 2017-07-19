/*
 * Exercise 4.31, p.158.
 *
 * The program in this section used the prefix increment and decrement
 * operators. Explain why we used prefix and not postfix. What changes
 * would have to be made to use the postfix versions? Rewrite the
 * program using postfix operators.
 */

/*
 * [Maybe I picked the program that wasn't meant by the question]
 * There is basically no difference between prefix and postfix ops.
 */

#include <iostream>
#include <vector>

int main(int, char**) {
  auto ivec = std::vector<int>{0, 1, 2, 3, 4, 5};
  auto cnt = ivec.size();
  for (decltype(ivec.size()) ix = 0; ix != ivec.size(); ix++, cnt--) {
    ivec[ix] = cnt;
  }

  for (auto &e : ivec) { std::cout << e << " "; }
  std::cout << std::endl;
  return 0;
}
