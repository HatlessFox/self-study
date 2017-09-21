/* 11799. Horror Dash */

#include <cstdio>
#include <algorithm>

int main(int, char**) {
  int cases_nm, case_i = 1;
  std::scanf("%d", &cases_nm);
  while (case_i <= cases_nm) {
    int creatures_nm = 0;
    std::scanf("%d", &creatures_nm);
    int max_speed = 0;
    while (creatures_nm--) {
      int creature_speed;
      std::scanf("%d", &creature_speed);
      max_speed = std::max<int>(max_speed, creature_speed);
    }
    
    std::printf("Case %d: %d\n", case_i, max_speed);
    case_i++;
  }
  return 0;
}
