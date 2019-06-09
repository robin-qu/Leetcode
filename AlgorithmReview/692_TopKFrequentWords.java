class Solution {
    class Pair implements Comparable<Pair> {
        public String word;
        public int count;
        
        public Pair(String word, int count) {
            this.word = word;
            this.count = count;
        }
        
        @Override
        public int compareTo(Pair other) {
            if (this.count != other.count) {
                return this.count - other.count;
            } else {
                return other.word.compareTo(this.word);
            }
        }
    }

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            if (!counts.containsKey(word)) {
                counts.put(word, 1);
            } else {
                counts.put(word, counts.get(word) + 1);
            }
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        
        for (String word : counts.keySet()) {
            pq.offer(new Pair(word, counts.get(word)));
            if (pq.size() > k) {
                pq.poll();
            }
        }
        
        List<String> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(0, pq.poll().word);
        }
        
        return res;
    }
}