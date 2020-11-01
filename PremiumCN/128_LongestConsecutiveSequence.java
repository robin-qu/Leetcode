class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        int res = 0;
        for (int num : set) {
            if (set.contains(num - 1)) {
                continue;
            }
            
            int count = 1;
            int curr = num;
            while (set.contains(curr + 1)) {
                count++;
                curr++;
            }
            
            res = Math.max(res, count);
        }
        
        return res;
    }
}