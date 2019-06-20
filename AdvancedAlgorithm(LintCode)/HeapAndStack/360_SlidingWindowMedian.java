// public class Solution {
//     /**
//      * @param nums: A list of integers
//      * @param k: An integer
//      * @return: The median of the element inside the window at each moving
//      */
//     public List<Integer> medianSlidingWindow(int[] nums, int k) {
//         if (nums == null || nums.length == 0 || k > nums.length) {
//             return new ArrayList<>();
//         }
        
//         PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
//         PriorityQueue<Integer> right = new PriorityQueue<>();
//         List<Integer> res = new ArrayList<>();
        
//         for (int i = 0; i < k; i++) {
//             if (left.size() == 0 || nums[i] <= left.peek()) {
//                 left.offer(nums[i]);
//             } else {
//                 right.offer(nums[i]);
//             }
            
//             if (left.size() > right.size() + 1) {
//                 right.offer(left.poll());
//             }
            
//             if (right.size() > left.size()) {
//                 left.offer(right.poll());
//             }
//         }
//         res.add(left.peek());
        
//         for (int i = k; i < nums.length; i++) {
//             if (left.size() == 0 || nums[i] <= left.peek()) {
//                 left.offer(nums[i]);
//             } else {
//                 right.offer(nums[i]);
//             }
            
//             if (!left.remove(nums[i - k])) {
//                 right.remove(nums[i - k]);
//             }
            
//             while (left.size() > right.size() + 1) {
//                 right.offer(left.poll());
//             }
            
//             while (right.size() > left.size()) {
//                 left.offer(right.poll());
//             }
            
//             res.add(left.peek());
//         }
        
//         return res;
//     }
// }

// public class Solution {
//     /**
//      * @param nums: A list of integers
//      * @param k: An integer
//      * @return: The median of the element inside the window at each moving
//      */
//     public List<Integer> medianSlidingWindow(int[] nums, int k) {
//         if (nums == null || nums.length == 0 || k > nums.length) {
//             return new ArrayList<>();
//         }
        
//         PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
//         PriorityQueue<Integer> right = new PriorityQueue<>();
//         List<Integer> res = new ArrayList<>();
        
//         for (int i = 0; i < nums.length; i++) {
//             if (left.size() == 0 || nums[i] <= left.peek()) {
//                 left.offer(nums[i]);
//             } else {
//                 right.offer(nums[i]);
//             }
            
//             if (left.size() > right.size() + 1) {
//                 right.offer(left.poll());
//             }
            
//             if (right.size() > left.size()) {
//                 left.offer(right.poll());
//             }
            
//             // moving window
//             if (i >= k) {
//                 if (!left.remove(nums[i - k])) {
//                     right.remove(nums[i - k]);
//                 } // or put it in another way:
//                 // if (nums[i - k] > left.peek()) {
//                 //     right.remove(nums[i - k]);
//                 // } else {
//                 //     left.remove(nums[i - k]);
//                 // }
                
                
//                 while (left.size() > right.size() + 1) {
//                     right.offer(left.poll());
//                 }
                
//                 while (right.size() > left.size()) {
//                     left.offer(right.poll());
//                 }
//             }
            
//             if (i >= k - 1) {
//                 res.add(left.peek());
//             }
//         }
        
//         return res;
//     }
// }


// put indices into the heaps
public class Solution {
    /**
     * @param nums: A list of integers
     * @param k: An integer
     * @return: The median of the element inside the window at each moving
     */
    private int[] nums;
    private PriorityQueue<Integer> left;
    private PriorityQueue<Integer> right;
    
    class leftComparator implements Comparator<Integer> {
        public int compare(Integer a, Integer b) {
            return nums[b] - nums[a];
        }
    }
    
    class rightComparator implements Comparator<Integer> {
        public int compare(Integer a, Integer b) {
            return nums[a] - nums[b];
        }
    }
    
    public List<Integer> medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length) {
            return new ArrayList<>();
        }
        
        this.nums = nums;
        this.left = new PriorityQueue<>(new leftComparator());
        this.right = new PriorityQueue<>(new rightComparator());
        List<Integer> res = new ArrayList<>();
        
        for (int i = 0; i < nums.length; i++) {
            if (left.size() == 0 || nums[i] <= nums[left.peek()]) {
                left.offer(i);
            } else {
                right.offer(i);
            }
            
            if (left.size() > right.size() + 1) {
                right.offer(left.poll());
            }
            
            if (right.size() > left.size()) {
                left.offer(right.poll());
            }
            
            // moving window
            if (i >= k) {
                left.remove(i - k);
                right.remove(i - k);
                
                while (left.size() > right.size() + 1) {
                    right.offer(left.poll());
                }
                
                while (right.size() > left.size()) {
                    left.offer(right.poll());
                }
            }
            
            if (i >= k - 1) {
                res.add(nums[left.peek()]);
            }
        }
        
        return res;
    }
}