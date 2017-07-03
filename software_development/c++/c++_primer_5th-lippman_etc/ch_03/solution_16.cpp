/*
 * Exercise 3.16, p. 105.
 *
 * Write a program to print the size and contents of the vectors from
 * exercise 3.13. Check whether your answers to that exercise were correct.
 * If not, restudy 3.3.1 (p. 97) until you understand why you were wrong.
 */

#include <vector>
#include <string>
#include <iostream>

using std::vector;
using std::string;

template <typename T>
void print_vector(const vector<T> &v) {
  std::cout << "Size: " << v.size() << "; vals: ";
  for (auto &e : v) {
    std::cout << e << ", ";
  }
  std::cout << std::endl;
}

int main(int, char**) {
  vector<int> v1; // sz: 0
  vector<int> v2(10); // sz 10, vals: 0 0 ... 0
  vector<int> v3(10, 42); // sz: 10, vals: 42 42 ... 42
  vector<int> v4{10}; // sz: 1, vals: 10
  vector<int> v5{10, 42}; // sz: 2, vals: 10 42
  vector<string> v6{10}; // sz: 10, vals: "" "" ... ""
  vector<string> v7{10, "hi"}; // sz: 10, vals: "hi" "hi" ... "hi"

  print_vector(v1);
  print_vector(v2);
  print_vector(v3);
  print_vector(v4);
  print_vector(v5);
  print_vector(v6);
  print_vector(v7);
  return 0;
}
