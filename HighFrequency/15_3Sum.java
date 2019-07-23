// Two pointers O(n^2)time O(1)space
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> comb = new ArrayList<>();
        
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;  // get rid of duplicates
            }
            
            if (nums[i] > 0) {
                break;  // since nums is sorted, we cannnot find three positive numbers whose sum is equal to zero
            }
            
            twoSum(nums, i + 1, -nums[i], res);
        }
        
        return res;
    }
    
    private void twoSum(int[] nums, int idx, int target, List<List<Integer>> res) {
        int left = idx;
        int right = nums.length - 1;
        
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                left++;
            } else if (nums[left] + nums[right] > target) {
                right--;
            } else {
                List<Integer> comb = new ArrayList<>();
                comb.add(-target);
                comb.add(nums[left]);
                comb.add(nums[right]);
                res.add(comb);
                left++;
                right--;
                
                while (left < right && nums[left] == nums[left - 1]) {
                    left++;  // remove duplicates
                }
                while (left < right && nums[right] == nums[right + 1]) {
                    right--;  // remove duplicates
                }
            }
        }
    }
}