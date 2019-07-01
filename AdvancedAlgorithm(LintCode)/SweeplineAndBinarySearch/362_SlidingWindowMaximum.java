// // O(n)
// public class Solution {
//     /**
//      * @param nums: A list of integers.
//      * @param k: An integer
//      * @return: The maximum number inside the window at each moving.
//      */
//     public List<Integer> maxSlidingWindow(int[] nums, int k) {
//         if (nums == null || nums.length == 0 || k <= 0) {
//             return new ArrayList<>();
//         }
        
//         Deque<Integer> window = new LinkedList<>();
//         List<Integer> res = new ArrayList<>();
        
//         for (int i = 0; i < k; i++) {
//             while (!window.isEmpty() && window.peekLast() < nums[i]) {
//                 window.pollLast();
//             }
            
//             window.offerLast(nums[i]);
//         }
        
//         res.add(window.peekFirst());
        
//         for (int i = k; i < nums.length; i++) {
//             if (nums[i - k] == window.peekFirst()) {
//                 window.pollFirst();
//             }
            
//             while (!window.isEmpty() && window.peekLast() < nums[i]) {
//                 window.pollLast();
//             }
            
//             window.offerLast(nums[i]);
//             res.add(window.peekFirst());
//         }
        
//         return res;
//     }
// }


// O(nlogk) - treeset
public class Solution {
    /**
     * @param nums: A list of integers.
     * @param k: An integer
     * @return: The maximum number inside the window at each moving.
     */
    class Pair implements Comparable<Pair> {
        public int val;
        public int idx;
        
        public Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
        
        @Override
        public int compareTo(Pair other) {
            if (this.val != other.val) {
                return other.val - this.val;
            }
            
            return other.idx - this.idx;
        }
    }
    
    public List<Integer> maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new ArrayList<>();
        }
        
        List<Integer> res = new ArrayList<>();
        TreeSet<Pair> set = new TreeSet<>();
        
        for (int i = 0; i < k; i++) {
            set.add(new Pair(nums[i], i));
        }
        
        res.add(set.first().val);
        
        for (int i = k; i < nums.length; i++) {
            set.remove(new Pair(nums[i - k], i - k));
            set.add(new Pair(nums[i], i));
            res.add(set.first().val);
        }
        
        return res;
    }
}