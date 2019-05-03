class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        Arrays.sort(nums);
        int closeSum = 0;
        int minDiff = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            
            while (left < right) {
                int sum = nums[left] + nums[right] + nums[i];
                int diff = Math.abs(sum - target);
                if (diff < minDiff) {
                    closeSum = sum;
                    minDiff = diff;
                }
                if (nums[left] + nums[right] < target - nums[i]) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return closeSum;
    }
}