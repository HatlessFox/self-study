/*
 * Exercise 4.20, p.151.
 *
 * Assuming that iter is a vector<string>::iterator, indicate which, if any,
 * of the following expressions are legal. Explain the behavior of the legal
 * expressions and why those that aren't legal are in erro.
 */

#include <vector>
#include <string>
#include <iostream>

int main(int, char**) {
  auto vs = std::vector<std::string>{"1", "2", "3", "4"};
  { // (a)
    // acesses an elements and move iter forward
    auto iter = vs.begin();
    std::cout << *iter++ << " -> ";
    std::cout << *iter << std::endl;
  }
  {
    // (b)
    // illegal. std::string doesn't have operator++.

    //auto iter = vs.begin();
    //(*iter)++;
  }
  {
    // (c)
    // illegal. vector<string>::iterator doesn't have the empty method.

    // auto iter = vs.begin();
    // *iter.empty();
  }
  {
    // (d)
    // returns the flag indicating if the pointee is empty
    auto iter = vs.begin();
    std::cout << iter->empty() << std::endl;
  }
  {
    // (e)
    // illegal. string doesn't have operator++ imlemented.

    //auto iter = vs.begin();
    //++*iter;
  }
  {
    // (f)
    // returns the flag indicating if the pointee is empty;
    // moves "ptr" to the next element

    auto vs = std::vector<std::string>{"", "1"};
    auto iter = vs.begin();
    std::cout << iter++->empty() << " ";
    std::cout << iter->empty() << std::endl;
  }
  return 0;
}
