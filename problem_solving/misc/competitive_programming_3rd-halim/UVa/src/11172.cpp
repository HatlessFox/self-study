/* 11172. Relational Operator */

#include <cstdio>

int main(int, char**) {
  int N;
  scanf("%d", &N);
  while (N--) {
    int a, b;
    scanf("%d %d", &a, &b);
    if      (a == b) { printf("="); }
    else if (a < b)  { printf("<"); }
    else             { printf(">"); }
    printf("\n");
  }
  return 0;
}
