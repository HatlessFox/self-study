/*
 * Exercise 2.12, p. 47.
 *
 * Which names are invalid?
 */

//int double = 3.14;  // (a) - invalid
int _;                // (b) - depends on ctx (std reserve). In general, valid.
//int catch-22;       // (c) - invalid
//int 1_or_2 = 2;     // (d) - invalid
double Double = 3.14; // (e) - valid

int main(int, char**) {
  return 0;
}
