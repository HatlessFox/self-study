/*
 * Exercise 2.28, p. 64.
 *
 * Explain the following definitions. Which of them are illegal?
 */

int main(int, char**) {
  {
    /*
     * (a) illegal. cp - const pointer, must be initialized.
     *     i - an int value, cp - a constant pointer to an int value.
     */
    //int i, *const cp;
  }
  {
    /*
     * (b) illegal. p2 - const pointer, must be initialized.
     *     pl - a pointer to an int value, p2 - a constant pointer to an int.
     */
    //int *pl, *const p2;
  }
  {
    /*
     * (c) illegal. ic - const int, must be initialized.
     *     ic - a constant int value , r - a reference to constant int value.
     */
    //const int ic, &r = ic;
  }
  {
    /*
     * (d) illegal. p3 - const pointer, must initialized.
     *     p3 - a constant pointer to a constant int value
     */
    //const int *const p3;
  }
  {
    /*
     * (e) legal
     *     p - a pointer to a constant int value.
     */
    const int *p;
  }
  return 0;
}
