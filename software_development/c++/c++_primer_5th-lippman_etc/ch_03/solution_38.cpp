/*
 * Exercise 3.38, p. 124
 *
 * In this section, we noted that it was not only illegal but meaningless to try
 * to add two pointers. Why would adding two pointers be meaningless?
 */

/*
 * Adding two pointers is meaningless by the following reasons:
 *  - pointers are just sequential ids.
 *    Adding two ids hoping to obtain a meaningful one is meaningless.
 *  - a pointer is start_block + offset. Sum of pointers:
 *    start_block1 + (offset1 + start_block2 + offset2).
 *    Correctness of the result depends on block1 size and
 *    start_block2 (that may change depending on where the block is allocated).
 */

#include <iostream>

int main(int, char **) {
  std::cout << "Look at the source" << std::endl;
  return 0;
}
