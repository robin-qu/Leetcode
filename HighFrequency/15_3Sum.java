class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            twoSum(i, nums, -nums[i], res);
        }
        
        return res;
    }
    
    private void twoSum(int idx, int[] nums, int target, List<List<Integer>> res) {
        int left = idx + 1;
        int right = nums.length - 1;
        
        while (left < right) {
            while (left < right && nums[left] + nums[right] < target) {
                left++;
            }
            
            while (left < right && nums[left] + nums[right] > target) {
                right--;
            }
            
            if (left < right && nums[left] + nums[right] == target) {
                List<Integer> list = new ArrayList<>();
                list.add(-target);
                list.add(nums[left]);
                list.add(nums[right]);
                res.add(list);
                
                left++;
                while (left < right && nums[left] == nums[left - 1]) {
                    left++;
                }
                
                right--;
                while (left < right && nums[right] == nums[right + 1]) {
                    right--;
                }
            }
        }
    }
}