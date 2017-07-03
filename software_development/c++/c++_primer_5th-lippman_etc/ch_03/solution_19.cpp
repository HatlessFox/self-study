/*
 * Exercise 3.19, p. 105.
 *
 * List three ways to define a vector and give it ten elements,
 * each with the value 42. Indicate whether there is a preferred way
 * to do so and why.
 */

#include <vector>
#include <iostream>

void print_vi(const std::vector<int> vi) {
  for (auto i : vi) { std::cout << i << " "; }
  std::cout << std::endl;
}

int main(int, char**) {
  { /* 1 */
    std::vector<int> vi(10, 42);
    print_vi(vi);
  }
  { /* 2 */
    std::vector<int> vi{42, 42, 42, 42, 42, 42, 42, 42, 42, 42};
    print_vi(vi);
  }
  { /* 3 */
    auto vi =  std::vector<int>{42, 42, 42, 42, 42, 42, 42, 42, 42, 42};
    print_vi(vi);
  }
  return 0;
}

/* I prefer the 1st method because of less typing. */
