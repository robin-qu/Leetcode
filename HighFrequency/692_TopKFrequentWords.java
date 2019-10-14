// // heap  O(nlogk)time O(n)space
// class Solution {
//     class Word implements Comparable<Word> {
//         public String word;
//         public int freq;
        
//         public Word(String word, int freq) {
//             this.word = word;
//             this.freq = freq;
//         }
        
//         @Override
//         public int compareTo(Word other) {
//             if (this.freq != other.freq) {
//                 return this.freq - other.freq;
//             }
            
//             return other.word.compareTo(this.word);
//         }
//     }

//     public List<String> topKFrequent(String[] words, int k) {
//         if (words == null || words.length == 0 || k < 1) {
//             return new ArrayList<>();
//         }
        
//         Map<String, Integer> counts = new HashMap<>();
//         for (String word : words) {
//             if (!counts.containsKey(word)) {
//                 counts.put(word, 1);
//             } else {
//                 counts.put(word, counts.get(word) + 1);
//             }
//         }
        
//         PriorityQueue<Word> pq = new PriorityQueue<>();
//         for (String key : counts.keySet()) {
//             pq.offer(new Word(key, counts.get(key)));
//             if (pq.size() > k) {
//                 pq.poll();
//             }
//         }
        
//         List<String> res = new ArrayList<>();
//         while (!pq.isEmpty()) {
//             res.add(0, pq.poll().word);
//         }
        
//         return res;
//     }
// }


// // heap  O(nlogk)time O(n)space
// class Solution {
//     public List<String> topKFrequent(String[] words, int k) {
//         if (words == null || words.length == 0 || k < 1) {
//             return new ArrayList<>();
//         }
        
//         int n = words.length;
//         Map<String, Integer> count = new HashMap<>(); // word -> frequency
        
//         for (String word : words) {
//             count.put(word, count.getOrDefault(word, 0) + 1);
//         }
        
//         PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
//             @Override
//             public int compare(String a, String b) {
//                 if (count.get(a) != count.get(b)) {
//                     return count.get(a) - count.get(b);
//                 }
                
//                 return b.compareTo(a);
//             }
//         });
        
//         for (String word : count.keySet()) {
//             pq.offer(word);
            
//             if (pq.size() > k) {
//                 pq.poll();
//             }
//         }
        
//         List<String> res = new ArrayList<>();
        
//         while (!pq.isEmpty()) {
//             res.add(0, pq.poll());
//         }
        
//         return res;
//     }
// }


class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        if (words == null || words.length == 0) {
            return new ArrayList<>();
        }
        
        Map<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }
        
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                if (a.getValue() != b.getValue()) {
                    return a.getValue() - b.getValue();
                }
                
                return b.getKey().compareTo(a.getKey());
            }
        });
        
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        
        List<String> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(pq.poll().getKey());
        }
        
        Collections.reverse(res);
        
        return res;
    }
}