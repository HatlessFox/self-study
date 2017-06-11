/*
 * Exercise 2.27, p. 64.
 *
 * Which of the following initializations are legal?
 */

int main(int, char**) {
  //int i = -1, &r = 0; // (a) - illegal, literal -> non-const ref is forbidden.
  {
    const int i2 = -1;
    //int *const p2 = &i2; // (b) - illegal, ptr to const -> ptr to non-const
  }
  const int i = -1, &r = 0; // (c) - legal, literal -> const ref is ok.
  {
    const int i2 = -1;
    const int *const p3 = &i2; // (d) - legal, ptr2const -> const ptr2const
  }
  {
    const int i2 = -1;
    const int *p1 = &i2; // (e) - legal, ptr2const -> ptr2const
  }
  //const int &const r2; // (f) - illegal, ref must be initialized
  {
    int i = -1;
    const int i2 = i, &r = i; //(g) - legal
  }
  return 0;
}
