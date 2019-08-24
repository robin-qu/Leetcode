// O(n)time O(1)space
class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int maxPrev = nums[0];  // the largest product ended with nums[i]
        int minPrev = nums[0];  // the smallest product ended with nums[i]
        int max = nums[0];
        
        for (int i = 1; i < n; i++) {
            int curr = nums[i];
            
            if (curr < 0) { // if negative, the result should be reversed
                int temp = maxPrev;
                maxPrev = minPrev;
                minPrev = temp;
            }
            
            maxPrev = Math.max(curr, maxPrev * curr);
            minPrev = Math.min(curr, minPrev * curr);
            
            max = Math.max(max, maxPrev);
        }
        
        return max;
    }
} 