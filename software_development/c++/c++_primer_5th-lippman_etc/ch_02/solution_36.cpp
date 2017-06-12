/*
 * Execercise 2.36, p. 72.
 *
 * Determine the type and the value of each variable when the code finishes.
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
  decltype((b)) d = a; // int&
  std::cout << "d -> " << type_name<decltype(d)>() << std::endl;
  ++c;
  ++d;

  std::cout << a << " " << b << " " << c << " " << d << std::endl; // 4 4 4 4
}
