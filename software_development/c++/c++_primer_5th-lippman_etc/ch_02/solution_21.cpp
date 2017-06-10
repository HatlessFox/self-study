/*
 * Exercise 2.21, p. 56
 *
 * Explain the following definitions.
 */

int main(int, char**) {
  int i = 0;
  //double *dp = &i; // (a) - invalid, types don't match
  //int *ip = i;     // (b) - invalid, must be a pointer
  int *p = &i;       // (c) - valid
}
