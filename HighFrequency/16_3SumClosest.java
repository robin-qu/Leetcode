// Two pointers O(n^2)time O(1)space
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        
        int n = nums.length;
        Arrays.sort(nums);
        
        int minDiff = Integer.MAX_VALUE;
        int min = 0;
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;  // skip duplicates
            }
            
            int left = i + 1;
            int right = n - 1;
            
            while (left < right) {
                int twoSum = nums[left] + nums[right];
                if (twoSum < target - nums[i]) {
                    left++;
                } else if (twoSum > target - nums[i]) {
                    right--;
                } else {
                    return target;
                }
                
                int diff = Math.abs(target - nums[i] - twoSum);
                if (diff < minDiff) {
                    minDiff = diff;
                    min = nums[i] + twoSum;
                }
            }
        }
        
        return min;
    }
}