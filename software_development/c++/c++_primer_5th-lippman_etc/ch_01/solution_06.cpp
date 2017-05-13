/*
 * Excercise 1.6, p. 9
 *
 * Explain whether the following program fragment is legal; fix it if necessary.
 */

#include <iostream>

int main(int, char**) {
  int v1, v2;
  std::cin >> v1 >> v2;

  // start of the fragment
  std::cout << "The sum of " << v1/*Fix* ; */
            << " and " << v2/*Fix* ; */
            << " is " << v1 + v2 << std::endl;
  // end of the fragment
}
