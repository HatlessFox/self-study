/*
 * Execercise 2.37, p. 72.
 *
 * Determine variables' types.
 */

#include <string>
#include <iostream>

template <typename T> std::string type_name() { return __PRETTY_FUNCTION__; }

int main(int, char**) {
  int a = 3, b = 4; // a,b -> int
  std::cout << "a -> " << type_name<decltype(a)>() << std::endl;
  std::cout << "b -> " << type_name<decltype(b)>() << std::endl;

  decltype(a) c = a; // int
  std::cout << "c -> " << type_name<decltype(c)>() << std::endl;
  decltype(a = b) d = a; // int&
  std::cout << "d -> " << type_name<decltype(d)>() << std::endl;
}
