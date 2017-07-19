/*
 * Exercise 4.22, p.152.
 *
 * Extend the program that assigned high pass, pass, and fail grades to also
 * assign low pass for grades between 60 and 75 inclusive. Write two versions:
 * One version that uses only conditinal operators;
 * the other should use one or more if statemens.
 * Which version do you think is easier to understand and why?
 */

#include <iostream>
#include <string>

std::string verdict_co(int grade) {
  return (90 < grade) ? "high pass"
                      : (75 < grade) ? "low pass"
                                     : (60 <= grade) ? "pass"
                                                     : "fail";
}

std::string verdict_if(int grade) {
  if (90 < grade) { return "high pass"; }
  else if (75 < grade) { return "low pass"; }
  else if (60 <= grade) { return "pass"; }
  else { return "fail"; }
}

int main(int, char**) {
  int grade;
  while (std::cin >> grade) {
    std::cout << "CO: " << verdict_co(grade) << std::endl;
    std::cout << "IF: " << verdict_if(grade) << std::endl;
  }
}

/*
 * I believe the 'if' version is more readable because of lower nesting.
 */
