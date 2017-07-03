/*
 * Exercise 3.30, p. 117.
 *
 * Identify the indexing errors in the following code.
 */

#include <cstddef>

int main(int, char**) {
  constexpr std::size_t array_size = 10;
  int ia[array_size];
  for (std::size_t ix = 1; ix <= array_size; ++ix) { // 1..10
    ia[ix] = ix; // ia[10] does not exist
  }
}
