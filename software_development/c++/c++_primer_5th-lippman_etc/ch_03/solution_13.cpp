/*
 * Exercise 3.13, p. 100.
 *
 * How many elements are there in each of the following vectors?
 * What are the values of the elements?
 */

#include <vector>
#include <string>
#include <iostream>

using std::vector;
using std::string;

template <typename T>
void print_vector(const vector<T> &v) {
  std::cout << "Size: " << v.size() << std::endl;
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
