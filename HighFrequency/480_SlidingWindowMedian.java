// double pqs O(nk)time O(k)space
class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new double[0];
        }
        
        int n = nums.length;
        
        double[] res = new double[n - k + 1];
        
        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> right = new PriorityQueue<>();
        
        for (int i = 0; i < k; i++) {
            left.offer(nums[i]);
            if (left.size() > (k + 1) / 2) {
                right.offer(left.poll());
            }
        }
        res[0] = (k % 2 == 0 ? (left.peek() / 2.0 + right.peek() / 2.0) : left.peek());
        
        for (int i = 0; i < n - k; i++) {
            if (!left.remove(nums[i])) {
                right.remove(nums[i]);
            }
            
            left.offer(nums[k + i]);
            if (left.size() > right.size() + 1) {
                right.offer(left.poll());
            } else {
                right.offer(left.poll());
                left.offer(right.poll());
            }
            
            res[i + 1] = (k % 2 == 0 ? (left.peek() / 2.0 + right.peek() / 2.0) : left.peek());
        }
        
        return res;
    }
}