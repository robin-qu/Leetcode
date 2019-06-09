public class Solution {
    /**
     * @param nums: A list of integers
     * @return: the median of numbers
     */
    
    public int[] medianII(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        
        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> right = new PriorityQueue<>();
        int[] res = new int[nums.length];
        
        for (int i = 0; i < nums.length; i++) {
            left.offer(nums[i]);
            
            if (i % 2 == 0) {
                if (right.size() == 0) {
                    res[i] = left.peek();
                } else if (left.peek() > right.peek()) {
                    right.offer(left.poll());
                    left.offer(right.poll());
                }
            } else {
                right.offer(left.poll());
            }
            
            res[i] = left.peek();
        }
        
        return res;
    }
}