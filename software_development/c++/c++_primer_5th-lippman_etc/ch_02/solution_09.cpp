/*
 * Exercise 2.9, p. 44
 */

#include <iostream>

int main(int, char**) {
  {
    // std::cout >> int input_value;
    int input_value;
    std::cin >> input_value;
  }
  {
    //int i = { 3.14 }; // error: narrowing
    int i = int(3.14);
    std::cout << i << std::endl;
  }
  {
    //double salary = wage = 9999.99;
    double salary = 9999.99, wage = salary;
    std::cout << salary << " " << wage << std::endl;
  }
  {
    // int i = 3.14; // warning
    int i = int(3.14);
    std::cout << i << std::endl;
  }
  return 0;
}
