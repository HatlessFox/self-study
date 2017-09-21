/* 11559. Event Planning */

#include <cstdio>
#include <limits>
#include <algorithm>

int main(int, char**) {
  while (1) {
    int geeks_n, budget, hotels_n;
    if (std::scanf("%d %d %d", &geeks_n, &budget, &hotels_n) == EOF) {
      break;
    }
    int weekends_n = 0;
    std::scanf("%d", &weekends_n);

    int best_budget = std::numeric_limits<int>::max();
    while (hotels_n--) {
      int geek_price = 0;
      std::scanf("%d", &geek_price);
      int weekend_i = 0;
      while (weekend_i < weekends_n) {
        int avail_geeks = 0;
        std::scanf("%d", &avail_geeks);
        if (geeks_n <= avail_geeks) {
          best_budget = std::min<int>(best_budget, geek_price * geeks_n);
        }
        weekend_i++;
      }
    }
    if (best_budget <= budget) {
      printf("%d\n", best_budget);
    } else {
      printf("stay home\n");
    }
  }
}
