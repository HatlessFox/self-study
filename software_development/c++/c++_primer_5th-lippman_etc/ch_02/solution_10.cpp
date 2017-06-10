/*
 * Exercise 2.10, p. 44
 */

#include <iostream>
#include <string>

std::string global_str; // default value -> empty
int global_int; // default value -> 0

int main(int, char**) {
  int local_int; // junk
  std::string local_str; // defalut value -> empty

  std::cout << "Globals. Str: " << global_str
            << "; Int: " << global_int << std::endl;
  std::cout << "Locals. Str: " << local_str
            << "; Int: " << local_int << std::endl;
  return 0;
}
