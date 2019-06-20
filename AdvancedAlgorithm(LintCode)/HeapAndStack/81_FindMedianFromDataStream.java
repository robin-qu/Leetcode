public class Solution {
    /**
     * @param nums: A list of integers
     * @return: the median of numbers
     */
    public int[] medianII(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        
        int[] res = new int[nums.length];
        
        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> right = new PriorityQueue<>();
        left.offer(nums[0]);
        res[0] = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            if (left.peek() >= nums[i]) {
                left.offer(nums[i]);
            } else {
                right.offer(nums[i]);
            }
            
            if (left.size() > right.size() + 1) {
                right.offer(left.poll());
            }
            
            if (right.size() > left.size()) {
                left.offer(right.poll());
            }
            
            res[i] = left.peek();
        }
        
        return res;
    }
}