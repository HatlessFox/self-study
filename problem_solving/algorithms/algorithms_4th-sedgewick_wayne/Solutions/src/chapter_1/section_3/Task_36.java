package chapter_1.section_3;

import java.util.Iterator;
import java.util.Random;

import chapter_1.section_3.Task_35.Card;
import chapter_1.section_3.Task_35.RandomQueue;
import chapter_1.section_3.Task_35.RandomQueue.ResizableArray;
import edu.princeton.cs.algs4.StdOut;

/*
 * Task 1.3.36: Random Iterator.
 * 
 * Write an iterator for RandomQueue<Item> from the previous exercise (1.3.35) that returns
 * the items in random order.
 */
public class Task_36 {

    public static class IterableRandomQueue<T> implements Iterable<T> {
        private RandomQueue<T> queue;
        public IterableRandomQueue(RandomQueue<T> q) { queue = q;}
        @Override
        public Iterator<T> iterator() { return new RandomQueueIterator<>(queue); }
    }
    
    public static class RandomQueueIterator<T> implements Iterator<T> {
        private Random rnd = new Random();
        private ResizableArray<T> holder;
        private int i;
        
        public RandomQueueIterator(RandomQueue<T> q) {
            holder = q.ra;
        }
        
        @Override
        public boolean hasNext() { return i != holder.size(); }

        @Override
        public T next() {
            int j = i + rnd.nextInt(holder.size() - i);
            holder.setElement(i, holder.getElement(j));
            return holder.getElement(i++);
        }
    }
    
    private static void printDeck(IterableRandomQueue<Card> deck) {
        for (Card c : deck) {
            StdOut.printf("%s ", c);
        }
        StdOut.println();
    }
    
    public static void main(String[] args) {
        IterableRandomQueue<Card> deck = new IterableRandomQueue<>(Card.generateDeck());
        printDeck(deck);
        printDeck(deck);
    }
}
