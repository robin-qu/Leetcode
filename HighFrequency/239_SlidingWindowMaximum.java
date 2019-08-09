// Deque O(n)time O(n)space
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        
        int n = nums.length;
        int[] res = new int[n - k + 1];
        Deque<Integer> dq = new LinkedList<>();  // monotonic decreasing deque
        
        // initiate the deque with the first k elements
        for (int i = 0; i < k; i++) {
            // potential new max value, pop out the values than cannot be max any more
            while (!dq.isEmpty() && nums[i] > dq.peekLast()) {
                dq.pollLast();
            }
            dq.offerLast(nums[i]);
        }
        res[0] = dq.peekFirst();  // the head of the deque is the max
        
        
        for (int i = k; i < n; i++) {
            if (nums[i - k] == dq.peekFirst()) {
                dq.pollFirst();  // remove the element out of the window from deque
            }
            
            while (!dq.isEmpty() && nums[i] > dq.peekLast()) {
                dq.pollLast();
            }
            dq.offerLast(nums[i]);
            
            res[i - k + 1] = dq.peekFirst();
        }
        
        return res;
    }
}