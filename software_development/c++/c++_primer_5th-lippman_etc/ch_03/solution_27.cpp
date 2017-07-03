/*
 * Exercise 3.27, p. 115
 *
 * Assuming txt_size is a function that takes no args and returns an int value,
 * which of the following definition are illegal? Explain why.
 */

int txt_size() { return 42; }

int main(int, char**) {

  { /* a */
    // illegal, since buf_size is not a constant (g++ has an extension)
    /*
    unsigned buf_size = 1024;
    int ia[buf_size];
    */
  }
  { /* b */
    // legal.
    int ia[4 * 7 - 14];
  }
  { /* c */
    // illegal, result of txt_size is not a constant.
    //int ia[txt_size()];
  }
  { /* d */
    // illegal, 11 < strlen("fundamental") = 12
    //char st[11] = "fundamental";
  }
  return 0;
}
