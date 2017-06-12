#include <iostream>
#include "Sales_data.h"

Sales_data read_sales_data() {
  Sales_data sd;
  std::cin >> sd.book_no >> sd.units_sold >> sd.revenue;
  return sd;
}

void print_sales_data(const Sales_data &sd) {
  std::cout << "ISBN: " << sd.book_no << "; Sold: " << sd.units_sold
            << "; Revenue: " << sd.revenue << std::endl;
}

Sales_data add(const Sales_data &sd1, const Sales_data &sd2) {
  //assert(sd1.book_no == sd2.book_no);
  return {sd1.book_no, sd1.units_sold + sd2.units_sold,
          sd1.revenue + sd2.revenue};
}

//----------------------------------------------------------------------------//

void task_1_23() {
  std::cout << "** Task 1.23 **" << std::endl;
  Sales_data prev;
  if (std::cin) {
    prev = read_sales_data();
  }
  unsigned cnt = 1;
  while (1) {
    Sales_data curr = read_sales_data();
    if (!std::cin) { break; }
    if (curr.book_no != prev.book_no) {
      std::cout << prev.book_no << ": " << cnt << std::endl;
      cnt = 0;
    }
    prev = curr;
    cnt++;
  }

  std::cout << prev.book_no << ": " << cnt << std::endl;
}


int main(int, char**) {
  task_1_23();
  return 0;
}
