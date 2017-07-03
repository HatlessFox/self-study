/*
 * Exercise 3.24, p. 113.
 *
 * Redo the last exercise from 3.3.3 (p. 105) using iterators.
 */

#include <vector>
#include <iostream>

auto read_ints() {
  std::vector<int> vi;
  int i;
  while (std::cin >> i) { vi.push_back(i); }
  return vi;
}

void print_adj_sum(const std::vector<int> &vi) {
  for (auto it = vi.begin() + 1; it != vi.end(); ++it) {
    std::cout << *it + *(it - 1) << " ";
  }
  std::cout << std::endl;
}

void print_opp_sum(const std::vector<int> &vi){
  for (decltype(vi.size()) i = 0; i < vi.size() / 2; ++i) {
    std::cout << *(vi.begin() + i) + *(vi.end() - 1 - i) << " ";
  }
  if (vi.size() % 2) {
    std::cout << *(vi.begin() + vi.size() / 2);
  }
  std::cout << std::endl;
}

int main(int, char**) {
  auto vi = read_ints();
  print_adj_sum(vi);
  print_opp_sum(vi);
  return 0;
}
