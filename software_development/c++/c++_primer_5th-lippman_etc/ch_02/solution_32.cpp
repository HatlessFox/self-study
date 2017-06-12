/*
 * Exercise 2.32, p. 67.
 *
 * Is the following code legal or not? If not, how might you make it legal?
 */

int main(int, char**) {
  //int null = 0, *p = null; // illegal, unable to init ptr with an int value.

  int null = 0, *p = &null; // fixed
  return 0;
}
