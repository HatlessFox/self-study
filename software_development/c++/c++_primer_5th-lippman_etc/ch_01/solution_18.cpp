/*
 * Excercise 1.18, page 18
 *
 * Compile and run the program from section 1.4.4 giving it:
 *   a) only equal values;
 *   b) values in which no number is repeated
 * as input.
 */

#include <iostream>

int main(int, char**) {
  int curr_v = 0, prev_v = 0;
  if (!(std::cin >> curr_v)) { return 0; }

  int prev_cnt = 0;
  do {
    ++prev_cnt;
    prev_v = curr_v;
    std::cin >> curr_v;
    if (!std::cin || prev_v != curr_v) {
      std::cout << prev_v << " occurs " << prev_cnt << " times" << std::endl;
      prev_cnt = 0;
    }
  } while (std::cin);

  return 0;
}
