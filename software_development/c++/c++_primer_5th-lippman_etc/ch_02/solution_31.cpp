/*
 * Exercise 2.31, p. 65.
 *
 * Determine whether the following assignments are legal.
 */

int main(int, char**) {
  const int v2 = 0; int v1 = v2;
  int *p1 = &v1, &r1 = v1;
  const int *p2 = &v2, *const p3 = &v1;

  r1 = v2; // legal, top-level const of v2 is ignored.
  //p1 = p2; // illegal, p2 'has' low-level const, p1 - doesn't
  //p1 = p3; // illegal, p3 'has' low-level const, p1 - doesn't
  p2 = p1; // legal
  p2 = p3; // legal, top-level const of p2 is ignored
  return 0;
}
