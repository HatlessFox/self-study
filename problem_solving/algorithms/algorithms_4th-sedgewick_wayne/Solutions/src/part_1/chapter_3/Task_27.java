package part_1.chapter_3;

import java.util.Arrays;
import java.util.function.Function;

import part_1.chapter_3.common.CommonLinkedList;
import part_1.chapter_3.common.CommonLinkedList.Node;
import utils.QA;

/*
 * Task 1.3.27
 * 
 * Write a method max() that takes a reference to the first node in a linked list as argument and 
 * returns the value of the maximum key in the list. Assume that all keys are positive integers, and
 * return 0 if the list is empty.
 */
public class Task_27 {

    /* Implementation */
    
    private static int max(Node<Integer> ptr) {
        int max = 0;
        while (ptr != null) {
            if (max < ptr.item) {
                max = ptr.item;
            }
            ptr = ptr.next;
        }
        return max;
    }
    
    /* Tests */
    
    private static CommonLinkedList<Integer> generateList(int... args) {
        CommonLinkedList<Integer> ll = new CommonLinkedList<>();
        Arrays.stream(args).forEach(i -> ll.add(i));
        return ll;
    }
    
    public static void runTests(Function<Node<Integer>, Integer> testee) {
        QA<Node<Integer>, Integer> qa = new QA<>(testee);
        
        qa.runTest(generateList().first, 0);
        qa.runTest(generateList(1, 2, 3, 4, 3).first, 4);
        qa.runTest(generateList(3, 2, 1).first, 3);
        qa.runTest(generateList(1, 2, 5).first, 5);
    }
    
    public static void main(String[] args) {
        runTests(Task_27::max);
    }
}
