/*
 * Exercise 3.18, p. 105.
 *
 * Is the following program legal? If not, how might you fix it?
 */

#include <iostream>
#include <vector>

int main(int, char**) {
  /*
  std::vector<int> ivec;
  ivec[0] = 42;

  // Illegal, the code leads to undefined behavior, since an element is accesed
  // by invalid index (ivec is empty).
  */

  // FIX
  std::vector<int> ivec{42};
  std::cout << ivec[0] << std::endl;
}
