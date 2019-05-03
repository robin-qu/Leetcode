class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 2; i++) {
            // Remove duplicates
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            twoSum(nums, i + 1, 0 - nums[i], res);
        }
        
        return res;
    }
    
    private void twoSum(int[] nums, 
                        int idx, 
                        int target, 
                        List<List<Integer>> res) {
        int left = idx;
        int right = nums.length - 1;
        
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                left++;
            } else if (nums[left] + nums[right] > target) {
                right--;
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(-target);
                list.add(nums[left]);
                list.add(nums[right]);
                res.add(list);
                
                left++;
                right--;
                // Remove duplicates
                while (left < right && nums[left] == nums[left - 1]) {
                    left++;
                }
                // Remove duplicates
                while (left < right && nums[right] == nums[right + 1]) {
                    right--;
                }
            }
        }
    }
}