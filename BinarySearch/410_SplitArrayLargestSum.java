// Binary search
class Solution {
    public int splitArray(int[] nums, int m) {
        int max = 0;
        int sum = 0;
        for (int num : nums) {
            max = Math.max(num, max);
            sum += num;
        }
        
        int low = max;
        int high = sum;
        int mid;
        
        while (low + 1 < high) {
            mid = low + (high - low) / 2;
            int split = getSplit(nums, mid);
            if (split == m) {
                high = mid;
            } else if (split < m) {
                high = mid;
            } else {
                low = mid;
            }
        }
        
        if (getSplit(nums, low) <= m) {
            return low;
        }
        return high;
    }
    
    // Given the largest sum of the subarray, finds 
    // the number of splits
    private int getSplit(int[] nums, int maxSum) {
        int res = 1;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] <= maxSum) {
                sum += nums[i];
            } else {
                res++;
                sum = nums[i];
            }
        }
        
        return res;
    }
}