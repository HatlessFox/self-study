/*
 * Exercise 2.33, p. 117.
 *
 * What would happen if we did not initialize the scores array in the program
 * on page 116?
 */

/*
 * Answer: if not initialized, values of elements of the scores array
 *         would be indeterminate.
 */

#include <cstddef>
#include <iostream>

int main(int, char**) {
  unsigned scores[11];
  /* ... */
  for (std::size_t i = 0; i < 11; ++i) {
    std::cout << scores[i] << std::endl;
  }
}
