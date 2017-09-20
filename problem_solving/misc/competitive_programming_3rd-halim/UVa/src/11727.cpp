/* 11727. Cost Cutting */

#include <cstdio>
#include <algorithm>
#include <iterator>

int main(int, char**) {
  int n, i = 0;
  std::scanf("%d", &n);

  while (i < n) {
    ++i;
    int a[3];
    std::scanf("%d %d %d", a, a + 1, a + 2);
    std::sort(std::begin(a), std::end(a));
    std::printf("Case %d: %d\n", i, a[1]);
  }
  return 0;
}
