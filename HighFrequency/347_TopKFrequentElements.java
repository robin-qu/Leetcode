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



// // Bucketsort O(n)time O(n)space
// class Solution {    
//     public List<Integer> topKFrequent(int[] nums, int k) {
//         if (nums == null || nums.length == 0) {
//             return new ArrayList<>();
//         }
        
//         Map<Integer, Integer> count = new HashMap<>();
//         for (int num : nums) {
//             if (!count.containsKey(num)) {
//                 count.put(num, 1);
//             } else {
//                 count.put(num, count.get(num) + 1);
//             }
//         }
        
//         int n = nums.length;
//         List<Integer>[] bucket = new List[n + 1];  // frequency varies from 0 to n
        
//         for (int key : count.keySet()) {
//             int freq = count.get(key);
            
//             if (bucket[freq] == null) {
//                 bucket[freq] = new ArrayList<>();
//             }
//             bucket[freq].add(key);
//         }
        
//         List<Integer> res = new ArrayList<>();
//         for (int i = n; i >= 0; i--) {
//             List<Integer> list = bucket[i];
//             if (list == null) {
//                 continue;
//             }
            
//             if (res.size() + list.size() <= k) {
//                 res.addAll(list);
//             } else {
//                 for (int j = 0; j < Math.min(list.size(), k - res.size()); j++) {
//                     res.add(list.get(j));
//                 }
//             }
//         }
        
//         return res;
//     }
// }



// // Bucketsort O(n)time O(n)space
// class Solution {    
//     public List<Integer> topKFrequent(int[] nums, int k) {
//         if (nums == null || nums.length == 0) {
//             return new ArrayList<>();
//         }
        
//         int n = nums.length;
//         List<Integer>[] bucket = new List[n + 1];
//         Map<Integer, Integer> count = new HashMap<>();  // num -> frequence
        
//         for (int num : nums) {
//             count.put(num, count.getOrDefault(num, 0) + 1);
//         }
        
//         for (int num : count.keySet()) {
//             int idx = count.get(num);
//             if (bucket[idx] == null) {
//                 bucket[idx] = new ArrayList<>();
//             }
            
//             bucket[idx].add(num);
//         }
        
//         List<Integer> res = new ArrayList<>();
//         for (int i = n; i >= 0 && res.size() < k; i--) {
//             if (bucket[i] != null) {
//                 res.addAll(bucket[i]);
//             }
//         }
        
//         return res;
//     }
// }



class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }
        
        List<Integer>[] bucket = new List[nums.length + 1];
        for (int key : counts.keySet()) {
            if (bucket[counts.get(key)] == null) {
                bucket[counts.get(key)] = new ArrayList<>();
            }
            
            bucket[counts.get(key)].add(key);
        }
        
        List<Integer> res = new ArrayList<>();
        for (int i = nums.length; i >= 0; i--) {
            if (bucket[i] == null) {
                continue;
            } else if (bucket[i].size() + res.size() <= k) {
                res.addAll(bucket[i]);
            } else {
                int j = 0;
                while (res.size() < k) {
                    res.add(bucket[i].get(j++));
                }
            }
        }
        
        return res;
    }
}