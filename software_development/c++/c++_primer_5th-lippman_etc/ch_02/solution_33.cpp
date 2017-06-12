/*
 * Exercise 2.33, p. 70
 *
 * What happens in each of the following assignements?
 */

#include <iostream>

int main(int, char**) {
  {
    int i = 0, &r = i;
    auto a = r;
    a = 42; // a is changed; i, r - aren't
    std::cout << "i = " << i << "; r = " << r << "; a = " << a << std::endl;
  }
  {
    const int ci = 0, &cr = ci;
    auto b = ci;
    b = 42; // b is changed; ci, cr - aren't
    std::cout << "ci = " << ci << "; cr = " << cr << "; b = " << b << std::endl;
  }
  {
    const int ci = 0, &cr = ci;
    auto c = cr;
    c = 42; // c is changed; ci, cr - aren't
    std::cout << "ci = " << ci << "; cr = " << cr << "; c = " << c << std::endl;
  }
  {
    int i = 0;
    auto d = &i;
    //d = 42; // compilation error: "int -> int*" is forbidden
  }
  {
    const int ci = 0;
    auto e = &ci;
    //e = 42; // compilation error: "int -> const int *" is forbidder
  }
  {
    const int ci = 0;
    auto &g = ci;
    //g = 42; // compilation error "int -> cont int &" is forbidden
  }
}
