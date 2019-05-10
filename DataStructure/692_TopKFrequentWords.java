class Solution {    
    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length == 0) {
            return res;
        }
        
        Map<String, Integer> map = new HashMap<>();
        
        for (String word : words) {
            if (!map.containsKey(word)) {
                map.put(word, 1);
            } else {
                map.put(word, map.get(word) + 1);
            }
        }
        
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<Map.Entry<String, Integer>>(new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                return entry1.getValue() == entry2.getValue() ? entry1.getKey().compareTo(entry2.getKey()) : entry2.getValue() - entry1.getValue();
            }
        });
        
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.add(entry);
        }
        
        while (k > 0) {
            res.add(pq.poll().getKey());
            k--;
        }
        
        return res;
    }
}

// class Solution {
//     class Pair implements Comparable<Pair> {
//         public String word;
//         public int count;
        
//         public Pair(String word, int count) {
//             this.word = word;
//             this.count = count;
//         }
        
//         @Override
//         public int compareTo(Pair other) {
//             if (this.count != other.count) {
//                 return other.count - this.count;
//             } else {
//                 return this.word.compareTo(other.word);
//             }
//         }
//     }
    
//     public List<String> topKFrequent(String[] words, int k) {
//         List<String> res = new ArrayList<>();
//         if (words == null || words.length == 0) {
//             return res;
//         }
        
//         Map<String, Integer> map = new HashMap<>();
        
//         for (String word : words) {
//             if (!map.containsKey(word)) {
//                 map.put(word, 1);
//             } else {
//                 map.put(word, map.get(word) + 1);
//             }
//         }
        
//         PriorityQueue<Pair> pq = new PriorityQueue<>();
        
//         for (String key : map.keySet()) {
//             pq.add(new Pair(key, map.get(key)));
//         }
        
//         while (k > 0) {
//             res.add(pq.poll().word);
//             k--;
//         }
        
//         return res;
//     }
// }