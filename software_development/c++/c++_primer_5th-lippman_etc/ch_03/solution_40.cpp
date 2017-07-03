/*
 * Exercise 3.40, p. 124.
 *
 * Write a program to define two character arrays initialized from string
 * literals. Now define a third character array to hold the concatenation
 * of the two arrays. Use strcpy and strcat to copy the two arrays into
 * the third.
 */

#include <cstring>
#include <iostream>

int main(int, char**) {
  const char s1[] = "Halo "; // 5 + 1
  const char s2[] = "pachany"; // 7 + 1
  char s3[13];
  strcpy(s3, s1);
  strcat(s3, s2);
  std::cout << s3 << std::endl;
  return 0;
}
