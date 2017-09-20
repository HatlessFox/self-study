/* 11498. Division of Nlogonia */

#include <cstdio>
#include <assert.h>

int main(int, char**) {
  while (true) {
    int n = 0;
    if (!(scanf("%d", &n), n)) { break; }

    int x0, y0;
    scanf("%d %d", &x0, &y0);
    while (n--) {
      int x, y;
      scanf("%d %d", &x, &y);
      if (x == x0 || y == y0) {
        printf("divisa");
      } else {
        printf("%s", y0 < y ? "N" : "S");
        printf("%s", x0 < x ? "E" : "O");
      }
      printf("\n");
    }
  }

  return 0;
}
