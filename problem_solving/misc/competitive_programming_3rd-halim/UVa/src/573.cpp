/* 573. The Snail. */

#include <cstdio>
#include <algorithm>

int run_snail_simulation() {
  double max_height, up, down, fatigue_rate;
  std::scanf("%lf %lf %lf %lf", &max_height, &up, &down, &fatigue_rate);
  if (max_height == 0) { return 0; }

  double curr_height = 0, curr_up = up;
  int day_i = 0;
  while (1) {
    day_i++;
    curr_height += curr_up;
    if (max_height < curr_height) {
      std::printf("success on day %d\n", day_i);
      break;
    }
    curr_height -= down;
    if (curr_height < 0) {
      std::printf("failure on day %d\n", day_i);
      break;
    }
    curr_up = std::max(0.0, curr_up - up * fatigue_rate / 100);
  }
  return 1;
}

int main(int, char**) {
  while (run_snail_simulation());
  return 0;
}
