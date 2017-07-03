/*
 * Exercise 3.34, p. 121.
 *
 * Given that p1 and p2 point to elements in the same array,
 * what does the following code do? Are there value of p1 and p2
 * that make this code illegal?
 */

int main(int, char**) {
  int arr[10000];
  int *p1 = arr, *p2 = arr + 100;
  p1 += p2 - p1; // := p1 = p1 + (p2 - p1) => assigns p1 to p2
  // Looks like there are no p1, p2 values that make the code illegal,
  // but it's 4:13 A.M. right now, so this statement requires a review.
}
