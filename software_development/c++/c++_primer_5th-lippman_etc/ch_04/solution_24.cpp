/*
 * Exercise 4.24, p.152.
 *
 * Our program that distiguished between high pass, pass, and fail depended on
 * the fact that the conditional operator is right associative. Describe how
 * that operator would be evaluated if the operator were lift associative.
 */

/*
 * Left associativity => type error - std::string vs bool
 * ((grade > 90) ? "high pass" : (grade < 60)) ? "fail" : "pass";
 */

#include <iostream>

int main(int, char**) {
  std::cout << "See src" << std::endl;
}
