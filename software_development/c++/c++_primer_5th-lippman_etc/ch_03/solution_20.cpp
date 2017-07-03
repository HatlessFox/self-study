/*
 * Exercise 3.20, p. 105.
 *
 * Read as set of integers into a vector. Print the sum of each pair of adjacent
 * elements. Change your program so that it prints the sum of the first and last
 * elements, followed by the sum of the second and second-to-last, and so on.
 */

#include <vector>
#include <iostream>

auto read_vi() {
  std::vector<int> vi;
  int i;
  while (std::cin >> i) {
    vi.push_back(i);
  }
  return vi;
}

void print_adj(const std::vector<int> &vi) {
  for (decltype(vi.size()) i = 1; i < vi.size(); ++i) {
    std::cout << vi[i - 1] + vi[i] << " ";
  }
  std::cout << std::endl;
}

void print_sums(const std::vector<int> &vi) {
  for (decltype(vi.size()) i = 0; i < vi.size() / 2; ++i) {
    std::cout << vi[i] + vi[vi.size() - i - 1] << " ";
  }
  if (vi.size() % 2) {
    std::cout << vi[vi.size() / 2] << " ";
  }
  std::cout << std::endl;
}

int main(int, char**) {
  auto vi = read_vi();
  print_adj(vi);
  print_sums(vi);
  return 0;
}
