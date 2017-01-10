package chapter_1.section_3;

import chapter_1.section_3.common.CommonLinkedList.Node;

/*
 * Task 1.3.28
 * 
 * Develop a recursive solution to the 1.3.27.
 */
public class Task_28 {

    /* Implementation */
    
    private static int max(Node<Integer> ptr) {
        return ptr == null ? 0 : Math.max(ptr.item, max(ptr.next));
    }
    
    /* Tests */
  
    public static void main(String[] args) {
        Task_27.runTests(Task_28::max);
    }
}
