/*
 * Exercise 2.26, p. 60.
 *
 * Which of the following are legal?
 */

int main(int, char**) {
  // const int buf;   // (a)    - illegal, must be initialized;
  int cnt = 0;        // (b)    - legal
  const int sz = cnt; // (c)    - legal
  ++cnt;              // (d-I)  - legal
  // ++sz;            // (d-II) - illegal, const modification is denied
  return 0;
}
