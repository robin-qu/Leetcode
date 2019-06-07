class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }
        
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i])) {
                continue;
            }
            
            int curr = 1;
            int down = nums[i] - 1;
            while (set.contains(down)) {
                curr++;
                set.remove(down);
                down--;
            }
            
            int up = nums[i] + 1;
            while (set.contains(up)) {
                curr++;
                set.remove(up);
                up++;
            }
            
            res = Math.max(res, curr);
        }
        
        return res;
    }
}