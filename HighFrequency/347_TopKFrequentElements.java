// // Heap O(nlogk)time O(n)space
// class Solution {
//     class Pair implements Comparable<Pair> {
//         public int num;
//         public int count;
        
//         public Pair(int num, int count) {
//             this.num = num;
//             this.count = count;
//         }
        
//         @Override
//         public int compareTo(Pair other) {
//             return this.count - other.count;
//         }
//     }
    
//     public List<Integer> topKFrequent(int[] nums, int k) {
//         if (nums == null || nums.length == 0) {
//             return new ArrayList<>();
//         }
        
//         List<Integer> res = new ArrayList<>();
//         Map<Integer, Integer> count = new HashMap<>();
        
//         for (int num : nums) {
//             if (!count.containsKey(num)) {
//                 count.put(num, 1);
//             } else {
//                 count.put(num, count.get(num) + 1);
//             }
//         }
        
//         PriorityQueue<Pair> pq = new PriorityQueue<>();
//         for (int key : count.keySet()) {
//             pq.offer(new Pair(key, count.get(key)));
//             if (pq.size() > k) {
//                 pq.poll();
//             }
//         }
        
//         while (!pq.isEmpty()) {
//             res.add(0, pq.poll().num);
//         }
        
//         return res;
//     }
// }



// Bucketsort O(n)time O(n)space
class Solution {    
    public List<Integer> topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            if (!count.containsKey(num)) {
                count.put(num, 1);
            } else {
                count.put(num, count.get(num) + 1);
            }
        }
        
        int n = nums.length;
        List<Integer>[] bucket = new List[n + 1];  // frequency varies from 0 to n
        
        for (int key : count.keySet()) {
            int freq = count.get(key);
            
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(key);
        }
        
        List<Integer> res = new ArrayList<>();
        for (int i = n; i >= 0; i--) {
            List<Integer> list = bucket[i];
            if (list == null) {
                continue;
            }
            
            if (res.size() + list.size() <= k) {
                res.addAll(list);
            } else {
                for (int j = 0; j < Math.min(list.size(), k - res.size()); j++) {
                    res.add(list.get(j));
                }
            }
        }
        
        return res;
    }
}
