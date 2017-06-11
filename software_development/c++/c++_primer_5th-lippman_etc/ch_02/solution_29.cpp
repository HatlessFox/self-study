/*
 * Exercise 2.29, p. 64.
 *
 * Which of the following assignments are legal?
 */

int main(int, char**) {
  {
    /* (a) legal. */
    int i; int const ic = 0;
    i = ic;
  }
  {
    /* (b) illegal, p1 points to a non-const value, p3 - to the const one */
    /*
    int *p1; const int *const p3 = nullptr;
    p1 = p3;
    */
  }
  {
    /* (c) illegal, p1 points to a non-const value. */
    /*
    int *p1; int const ic = 0;
    p1 = &ic;
    */
  }
  {
    /* (d) illegal, p3 is a const pointer -> its value can't be changed. */
    /*
    const int *const p3 = nullptr; int const ic = 0;
    p3 = &ic;
    */
  }
  {
    /* (e) illegal. p2 is a const pointer -> its value can't be changed */
    /*
    int *p1, *const p2 = nullptr;
    p2 = p1;
    */
  }
  {
    /* (f) illegal. ic is const -> its value can't be changed. */
    /*
    const int ic = 0, *const p3 = nullptr;
    ic = *p3;
    */
  }
  return 0;
}
