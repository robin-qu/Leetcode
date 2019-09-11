// dp O(n)time O(1)space
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int first = 0;
        int second = nums[0];
        
        for (int i = 1; i < n; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        
        return second;
    }
}