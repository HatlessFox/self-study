package part_1.chapter_3;

import java.util.Random;

import edu.princeton.cs.algs4.StdOut;

/* Task 1.3.35: Random queue.
 * 
 * A random queue stores a collection of items and supports the following API: <skipped, page 168>.
 * Write a class RandomQueue that implements this API.
 * _Hint_: Use an array representation (with resizing). To remove an item, swap one at a random 
 * position (indexed 0 through N-1) with the one at the last position (index N-1). Then delete and 
 * return the last object, as in ResizingArrayStack. Write a client that deals bridge hands
 * (13 cards each) using RandomQueue<Card>.
 */
public class Task_35 {
    
    public static class RandomQueue<T> {
        
        public static class ResizableArray<T>{
            @SuppressWarnings("unchecked")
            private T[] holder = (T[]) new Object[1];
            private int sz = 0;
            
            private void resize(int new_sz) {
                @SuppressWarnings("unchecked")
                T[] new_holder = (T[])new Object[new_sz];
                System.arraycopy(holder, 0, new_holder, 0, sz);
                holder = new_holder;
            }
            
            private void append(T value) {
                if (sz == holder.length) {
                    resize(2 * sz);
                }
                holder[sz++] = value;
            }
            
            public void removeLast() {
                if (sz == holder.length / 4) {
                    resize(2 * sz);
                }
                holder[--sz] = null;
            }
            
            public void setElement(int i, T value) {
                if (sz == i) {
                    if (value == null) {
                        removeLast();
                    } else {
                        append(value);
                    }
                } 
                holder[i] = value;
            }
            
            public T getElement(int i) {
                return holder[i];
            }
            
            public int size() { return sz; }
        }
        
        public ResizableArray<T> ra = new ResizableArray<T>();
        public Random rnd = new Random();
        
        public boolean isEmpty() { return ra.size() == 0; }
        
        public void enqueue(T item) {
            ra.append(item);
        }
        
        public T dequeue() {
            int victim_i = rnd.nextInt(ra.size());
            T victim = ra.getElement(victim_i);
            ra.setElement(victim_i, ra.getElement(ra.size() - 1));
            ra.removeLast();
            return victim;
        }
        
        public T sample() { return ra.getElement(rnd.nextInt(ra.size())); }
    }
    
    public static class Card {
        public static final char SUITS[] = {'\u2660', '\u2665', '\u2666', '\u2663'};
        public static final char VALUES[] =
            {'2', '3', '4', '5', '6', '7', '8', '9', 'a', 'J', 'Q', 'K', 'A'};
        private char suit;
        private char value;
        
        public Card(char s, char v) {
            suit = s;
            value = v;
        }
        
        @Override
        public String toString() {
            return suit + "" + value;
        }
        
        public static RandomQueue<Card> generateDeck() {
            RandomQueue<Card> deck = new RandomQueue<>();
            for (char s : Card.SUITS) {
                for (char v : Card.VALUES) {
                    deck.enqueue(new Card(s, v));
                }
            }
            return deck;
        }
    }
    
    public static void main(String[] args) {
        RandomQueue<Card> deck = Card.generateDeck();
        for (int pl = 1; pl <= 4; pl++) {
            StdOut.printf("%d: ", pl);
            for (int card = 0; card < 13; card++) {
                StdOut.printf("%s ", deck.dequeue());
            }
            StdOut.println();
        }
    }
}
