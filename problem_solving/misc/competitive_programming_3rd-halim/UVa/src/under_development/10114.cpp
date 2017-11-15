/* 10114. Loansome Car Buyer */

#include <cstdio>
#include <cassert>
#include <vector>

std::vector<double> init_mnth2depr(std::size_t months) {
  auto month_nm = months + 1;
  auto mnth2depr = std::vector<double>(month_nm, 0.0);

  std::size_t deprecation_nm;
  std::scanf("%zu", &deprecation_nm);
  assert(deprecation_nm != 0);
  std::size_t month_i = 0, next_depr_month;
  double depr_rate;
  std::scanf("%zu", &next_depr_month);

  while (month_i < month_nm) {
    if (month_i == next_depr_month) {
      std::scanf("%lf", &depr_rate);
      assert(deprecation_nm != 0);
      if (--deprecation_nm) {
        std::scanf("%zu", &next_depr_month);
      } else {
        next_depr_month = month_nm;
      }
    }
    mnth2depr[month_i++] = depr_rate;
  }
  
  return mnth2depr;
}

void print_months(int months) {
  std::printf("%d month%c", months, "s\0"[months == 1]);
  std::printf("\n");
}

int run_simulation() {
  int months;
  double down_payment, loan;
  if (std::scanf("%d %lf %lf", &months, &down_payment, &loan) != 3) {
    return 0;
  }
  if (months < 0) { return 0; }
  double car_cost = loan + down_payment, payment = loan / months;
  auto mnth2depr = init_mnth2depr(months);

  car_cost *= 1 - mnth2depr[0];
  if (loan < car_cost) {
    print_months(0);
    return 1;
  }

  int month_i = 1;
  while (month_i < months) {
    car_cost *= 1 - mnth2depr[month_i];
    loan -= payment;

    if (loan < car_cost) {
      print_months(month_i);
      break;
    }
    month_i++;
  }
  if (month_i == months) {
    print_months(month_i);
  }
  return 1;
}

int main(int, char**) {
  while (run_simulation());
  return 0;
}
