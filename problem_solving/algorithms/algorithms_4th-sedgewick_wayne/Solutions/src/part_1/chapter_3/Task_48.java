package part_1.chapter_3;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Supplier;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import part_1.chapter_3.Task_33.BackwardIterableDeque;
import part_1.chapter_3.Task_33.DoubleLinkedListDeque;

/*
 * Task 1.3.49: Two stacks with a deque.
 * 
 * Implement two with a single deque so that each operation takes a constant number of deque
 * operations (see Exercise 1.3.33 <p. 167>).
 */
public class Task_48 {

    public static class TwoStackOnDeque<T, D extends BackwardIterableDeque<T>> {
        private BackwardIterableDeque<T> holder;
        private int st1_sz = 0, st2_sz = 0;
        
        public TwoStackOnDeque(D holder) { this.holder = holder; }
        
        public StackView getStackView(int n) {
            return new StackView(n);
        }
        
        public class StackView implements Iterable<T> {
            private int stack_n = 0;
            public StackView(int n) { stack_n = n; }
            
            private boolean isStack1View() { return stack_n == 1; }
            
            private void updateStackSize(int delta) {
                if (isStack1View()) { st1_sz += delta; }
                else                { st2_sz += delta; }
            }
            
            public int size() { return isStack1View() ? st1_sz : st2_sz; }
            public boolean isEmpty() { return size() == 0; }
            public void push(T e) {
                Consumer<T> cons = isStack1View() ? holder::pushLeft : holder::pushRight;
                
                cons.accept(e);
                updateStackSize(1);
            }
            
            public T pop() {
                if (isEmpty())
                    return null;
                Supplier<T> supp = isStack1View() ? holder::popLeft : holder::popRight;
                updateStackSize(-1);
                return supp.get();
            }

            @Override
            public Iterator<T> iterator() { return new StackViewIterator(); }
            
            public class StackViewIterator implements Iterator<T> {
                private Iterator<T> deque_itr;
                private int elems_to_go;
                
                public StackViewIterator() {
                    if (isStack1View()) {
                        elems_to_go = st1_sz;
                        deque_itr = holder.iterator();
                    } else {
                        elems_to_go = st2_sz;
                        deque_itr = holder.backIterator();
                    }
                }
                
                @Override public boolean hasNext() { return elems_to_go != 0; }
                @Override public T next() {
                    elems_to_go--;
                    return deque_itr.next();
                }
            }
            

            @Override
            public String toString() {
                StringBuilder sb = new StringBuilder();
                forEach(e -> sb.append(e).append(" "));
                return sb.toString().trim();
            }
        }
        
    }
    
    public static void main(String[] args) {
        TwoStackOnDeque<String, DoubleLinkedListDeque<String>> two_st_deq =
                new TwoStackOnDeque<>(new DoubleLinkedListDeque<String>());
        while (true) {
            StdOut.print("Op (ph <n> <e>; pp <n>): ");
            String op = StdIn.readString();
            TwoStackOnDeque<String, DoubleLinkedListDeque<String>>.StackView
                sv = two_st_deq.getStackView(StdIn.readInt());
            switch (op) {
            case "ph": sv.push(StdIn.readString()); break;
            case "pp": StdOut.println(sv.pop()); break;
            }
            StdOut.printf("-> S1: %s\n-> S2: %s\n",
                    two_st_deq.getStackView(1), two_st_deq.getStackView(2));
        }
    }
    
}
