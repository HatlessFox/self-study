/*
 * Exercise 3.9, p. 96.
 *
 * What does the following program do? IS it valid? If not, why not?
 */

/*
 * Answer: program's behavior is undefined -
 *         an access the 1st char of an empty string.
 */

#include <string>
#include <iostream>

int main(int, char**) {
  std::string s;
  std::cout << s[0] << std::endl;
  return 0;
}
