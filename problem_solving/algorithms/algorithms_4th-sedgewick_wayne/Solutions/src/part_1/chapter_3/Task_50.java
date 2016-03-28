package part_1.chapter_3;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;
import part_1.chapter_3.common.CommonStack;
import utils.CliUtils;
import utils.QA;

/*
 * Task 1.3.50: Fail-fast iterator.
 * 
 * Modify the iterator code in Stack to immediately throw a
 * java.util.ConcurrentModificationException if the client modifies the collection
 * (via push() or pop()) during iteration.
 * 
 * _Solution_: Maintain a counter that counts the number of push() and pop() operations. When 
 * creating an iterator, store this value as an Iterator instance variable. Before each call to 
 * hasNext() and next(), check that this value has not changed since construction of the iterator;
 * if it has, throw the exception.
 */
public class Task_50 {
    
    public static class ConcurrentAwareStack<T> extends CommonStack<T> {
        public int modif_stamp = 0;
        @Override
        public void push(T item) {
            modif_stamp++;
            super.push(item);
        }
        
        @Override
        public T pop() {
            modif_stamp++;
            return super.pop();
        }
        
        @Override
        public Iterator<T> iterator() {
            return new FailFastIterator();
        }
        
        public class FailFastIterator extends StackIterator {
            private final int EXPECTED_MODIF_STAMP = modif_stamp;
            
            private void validateModificationStamp() {
                if (EXPECTED_MODIF_STAMP == modif_stamp)
                    return;
                throw new ConcurrentModificationException();
            }
            
            @Override
            public boolean hasNext() {
                validateModificationStamp();
                return super.hasNext();
            }
            
            @Override
            public T next() {
                validateModificationStamp();
                return super.next();
            }
        }
    }
    
    /* Testing */
    
    private static void testPlainIteration() {
        ConcurrentAwareStack<String> st = new ConcurrentAwareStack<>();
        st.push("a");
        st.push("b");
        st.push("c");
        StdOut.println(QA.runTest(0, CliUtils::iterableToString, st, "c b a"));
        st.push("d");
        st.pop();
    }
    
    private static void stub(String s) {}
    
    private static void testPushInterruptedIteration(ConcurrentAwareStack<String> st) {
        final String TEST_ID = "Push Interr Iter";
        try {
            for (String s : st) {
                st.push("boo");
                stub(s);
            }
        } catch (ConcurrentModificationException e) {
            StdOut.printf("%s: OK\n", TEST_ID);
            return;
        }
        StdOut.printf("%s: FAILED\n", TEST_ID);
    }
    
    private static void testPopInterruptedIteration(ConcurrentAwareStack<String> st) {
        final String TEST_ID = "Pop Interr Iter";
        try {
            for (String s : st) {
                st.pop();
                stub(s);
            }
        } catch (ConcurrentModificationException e) {
            StdOut.printf("%s: OK\n", TEST_ID);
            return;
        }
        StdOut.printf("%s: FAILED\n", TEST_ID);
    }
    
    public static void main(String args[]) {
        testPlainIteration();
        
        ConcurrentAwareStack<String> st = new ConcurrentAwareStack<>();
        st.push("a");
        st.push("b");
        st.push("c");
        
        testPushInterruptedIteration(st);
        testPopInterruptedIteration(st);
    }
}
