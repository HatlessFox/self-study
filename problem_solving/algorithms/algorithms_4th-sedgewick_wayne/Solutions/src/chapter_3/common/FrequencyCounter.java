package chapter_3.common;

public class FrequencyCounter {

    private ST<String, Integer> occupancies;
    private int word_min_len = 0;
    
    public FrequencyCounter(ST<String, Integer> st, int word_len_limit) {
        occupancies = st;
        word_min_len = word_len_limit;
    }
    
    public void addWord(String word) {
        if (word.length() < word_min_len) { return; }
        if (occupancies.contains(word)) {
            occupancies.put(word, occupancies.get(word) + 1);
        } else {
            occupancies.put(word, 1);
        }
    }
    
    public String mostFrequentWord() {
        String max_w = "";
        occupancies.put("", 0);
        for (String w : occupancies.keys()) {
            if (occupancies.get(w) <= occupancies.get(max_w)) { continue; }
            max_w = w;
        }
        return max_w;
    }
}
