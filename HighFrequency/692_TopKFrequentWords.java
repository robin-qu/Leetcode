// heap  O(nlogk)time O(n)space
class Solution {
    class Word implements Comparable<Word> {
        public String word;
        public int freq;
        
        public Word(String word, int freq) {
            this.word = word;
            this.freq = freq;
        }
        
        @Override
        public int compareTo(Word other) {
            if (this.freq != other.freq) {
                return this.freq - other.freq;
            }
            
            return other.word.compareTo(this.word);
        }
    }
    
    public List<String> topKFrequent(String[] words, int k) {
        if (words == null || words.length == 0 || k < 1) {
            return new ArrayList<>();
        }
        
        Map<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            if (!counts.containsKey(word)) {
                counts.put(word, 1);
            } else {
                counts.put(word, counts.get(word) + 1);
            }
        }
        
        PriorityQueue<Word> pq = new PriorityQueue<>();
        for (String key : counts.keySet()) {
            pq.offer(new Word(key, counts.get(key)));
            if (pq.size() > k) {
                pq.poll();
            }
        }
        
        List<String> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(0, pq.poll().word);
        }
        
        return res;
    }
}