package part_1.chapter_3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/* Task 1.3.38: Delete kth element.
 * 
 * Implement a class that supports the following API: <skipped p. 169>
 * First, develop an implementation that uses an array implementation, and then develop one that
 * uses a linked-list implementation.
 * _Note_: the algorithms and data structures that we introduce in Ch. 3 make it possible to develop
 *         an implementation that that can guarantee that both insert() and delete() take time 
 *         proportional to the algorithm of the number of items in the queue -- see exercise 3.5.27.
 */
public class Task_38 {

    public static interface GeneralizedQueue<T> {
        boolean isEmpty();
        void insert(T x);
        T delete(int k);
        String toString();
    }
    
    public static class LLGeneralizedQueue<T> implements GeneralizedQueue<T> {
        private class Node {
            T payload;
            Node next;
            
            public Node(T data) { payload = data; }
        }
        private Node first = null, last = null;
        
        @Override public boolean isEmpty() { return first == null; }

        @Override
        public void insert(T x) {
            Node new_node = new Node(x);
            if (isEmpty()) {
                last = first = new_node;
            } else {
                last = last.next = new_node;
            }
            
        }

        @Override
        public T delete(int k) {
            if (isEmpty() || k <= 0) {
                return null;
            } else if (k == 1) {
                T data = first.payload;
                first = first.next;
                return data;
            }
            
            Node pre_victim = first;
            while (1 < --k && pre_victim != null) {
                pre_victim = pre_victim.next;
            }
            if (pre_victim == null || pre_victim == last) {
                return null;
            }
            Node victim = pre_victim.next;
            pre_victim.next = victim.next;
            if (victim == last) {
                last = pre_victim;
            }
            return victim.payload;
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node ptr = first;
            while (ptr != null) {
                sb.append(ptr.payload);
                sb.append(" ");
                ptr = ptr.next;
            }
            return sb.toString();
        }
    }
    
    public static class ArrayGeneralizedQueue<T> implements GeneralizedQueue<T> {
        @SuppressWarnings("unchecked")
        private T[] holder = (T[])new Object[1];
        private int fst;
        private int sz;
        
        private int effectiveIndex(int i) {
            return (fst + i) % holder.length;
        }
        
        private void resize(int new_sz) {
            @SuppressWarnings("unchecked")
            T[] new_holder = (T[])new Object[new_sz];
            int suff_sz = Math.min(holder.length - fst, sz);
            System.arraycopy(holder, fst, new_holder, 0, suff_sz);
            System.arraycopy(holder, 0, new_holder, suff_sz, sz - suff_sz);
            holder = new_holder;
            fst = 0;
        }
        
        @Override
        public boolean isEmpty() { return sz == 0; }

        @Override
        public void insert(T x) {
            if (sz == holder.length) {
                resize(2*sz);
            }
            holder[effectiveIndex(sz++)] = x;
        }

        @Override
        public T delete(int k) {
            if (k <= 0 || sz < k)
                return null;
            k -= 1; // 1-based index
            int i = effectiveIndex(k);
            T data = holder[i];
            for (int j = k; j < sz; j++) {
                holder[effectiveIndex(j)] = holder[effectiveIndex(j + 1)];
            }
            sz--;
            if (sz < holder.length / 4) {
                resize(sz / 2);
            }
            return data;
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < sz; i++) {
                sb.append(holder[effectiveIndex(i)]);
                sb.append(" ");
            }
            return sb.toString();
        }
    }
    
    public static void main(String[] args) {
        GeneralizedQueue<String> ll = new LLGeneralizedQueue<>(),
                                 ar = new ArrayGeneralizedQueue<>();
        while (true) {
            StdOut.print("Enter op (i <e> - insert <e>, d <i> - delete <i>th: ");
            switch (StdIn.readString()) {
            case "i":
                String e = StdIn.readString();
                ll.insert(e);
                ar.insert(e);
                break;
            case "d":
                int i = StdIn.readInt();
                StdOut.printf("LL: %s; Arr: %s\n", ll.delete(i), ar.delete(i));
                break;
            }
            StdOut.printf("LL: %s\nAr: %s\n", ll, ar);
        }
    }
}
