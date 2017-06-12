/*
 * Execercise 2.38, p. 72.
 *
 * Describe differences in type deduction between decltype and auto.
 * Give examples.
 */

/*
 * Answer:
 * - result of decltype depends on the form of a given expression;
 * - auto drops top-level const and reference, decltype doesn't.
 */

#include <string>
#include <iostream>

template <typename T> std::string type_name() { return __PRETTY_FUNCTION__; }

int main(int, char**) {
  const int a = 3, &r = a;
  auto a_1 = a;
  auto a_2 = r;
  auto a_3 = (a);

  decltype(a) d_1 = a;
  decltype(r) d_2 = r;
  decltype((a)) d_3 = a;

  std::cout << "a_1 -> " << type_name<decltype(a_1)>() << std::endl;
  std::cout << "a_2 -> " << type_name<decltype(a_2)>() << std::endl;
  std::cout << "a_3 -> " << type_name<decltype(a_3)>() << std::endl;

  std::cout << "d_1 -> " << type_name<decltype(d_1)>() << std::endl;
  std::cout << "d_2 -> " << type_name<decltype(d_2)>() << std::endl;
  std::cout << "d_3 -> " << type_name<decltype(d_3)>() << std::endl;
}
