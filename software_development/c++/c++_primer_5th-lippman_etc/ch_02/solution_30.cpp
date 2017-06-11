/*
 * Exercise 2.30, p. 65.
 *
 * For each of the following declaration indicate whether the object being
 * declared has top-level or low-level conts.
 */

int main(int, char**) {
  const int v2 = 0;        // top-level
  int v1 = v2;             // no const
  int *p1 = &v1, &r1 = v1; // no const
  const int *p2 = &v2,     // low-level
    *const p3 = &v1,       // top-level and low-level
    &r2 = v2;              // low-level
  return 0;
}
