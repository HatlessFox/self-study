package part_1.chapter_1;

import edu.princeton.cs.algs4.StdOut;

public class OtherTasks {

    /* Common utilities */
    public static void printTaskTitle(int task_num) {
        StdOut.printf("== Taks %d ==\n", task_num);
    }
    
    /* Tasks */
    
    public static void task1() {
        printTaskTitle(1);
        StdOut.printf("a) %d; b) %f; c) %b\n",
                /* a) */ (0 + 15) / 2,
                /* b) */ 2.0e-6 * 100000000.1,
                /* c) */ true && false || true && true);
    }
    
    public static void task2() {
        printTaskTitle(2);
        StdOut.printf("a) %f; b) %f; c) %b; d) %s\n",
                /* a) */ (1 + 2.234) / 2,
                /* b) */ 1+2+3+4.0,
                /* c) */ 4.1 >= 4,
                /* d) */ 1+2+"3");
    }
    
    public static void task8() {
        printTaskTitle(8);
        StdOut.print("a) ");
        System.out.println('b');
        StdOut.print("b) ");
        System.out.println('b' + 'c');
        StdOut.print("c) ");
        System.out.println((char)('a' + 4));
    }
    
    public static void task10() {
        printTaskTitle(10);
        
        int[] a = new int[10];
        for (int i = 0; i < 10; i++) { a[i] = 9 - i; }
        for (int i = 0; i < 10; i++) { a[i] = a[a[i]]; } 
        for (int i = 0; i < 10; i++) { System.out.println(a[i]); }
    }
    
    public static void main(String[] args) {
        task1();
        task2();
        task8();
        task10();
    }

}
