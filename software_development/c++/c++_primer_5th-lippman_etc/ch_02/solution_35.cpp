/*
 * Exercise 2.35, p. 70.
 *
 * Determine the types deduced in each of the following definitions.
 */

#include <iostream>
#include <typeinfo>
#include <string>

template <class T> std::string type_name() {
  return __PRETTY_FUNCTION__;
}

int main(int, char**) {
  const int i = 42;

  auto j = i; // j -> int
  std::cout << "j -> " << type_name<decltype(j)>() << std::endl;

  const auto &k = i; // k -> const int &
  std::cout << "k -> " << type_name<decltype(k)>() << std::endl;

  auto *p = &i; // p -> const int *
  std::cout << "p -> " << type_name<decltype(p)>() << std::endl;

  const auto j2 = i, &k2 = i; // j2 -> const int, k2 -> const int &
  std::cout << "j2 -> " << type_name<decltype(j2)>() << std::endl;
  std::cout << "k2 -> " << type_name<decltype(k2)>() << std::endl;
}
