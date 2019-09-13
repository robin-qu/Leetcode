// Two pointers O(n^3)time O(1)space
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4) {
            return new ArrayList<>();
        }
        
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        
        for (int i = 0; i < n - 3; i++) {  // 4 sum
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;  //  deduplicate
            }
            
            for(int j = i + 1; j < n - 2; j++) {  // 3 sum
                if (j > i + 1 && nums[j - 1] == nums[j]) {
                    continue;  //  deduplicate
                }
                
                // 2 sum
                int left = j + 1;
                int right = n - 1;
                int newT = target - nums[i] - nums[j];
                
                while (left < right) {
                    if (nums[left] + nums[right] < newT) {
                        left++;
                    } else if (nums[left] + nums[right] > newT) {
                        right--;
                    } else {
                        List<Integer> sol = new ArrayList<>();
                        sol.add(nums[i]);
                        sol.add(nums[j]);
                        sol.add(nums[left]);
                        sol.add(nums[right]);
                        res.add(sol);
                        
                        left++;
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;  //  deduplicate
                        }
                        right--;
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;  //  deduplicate
                        } 
                    }
                }
            }
        }
        
        return res;
    }
}