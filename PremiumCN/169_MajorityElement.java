class Solution {
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;

        int res = nums[0];
        int count = 1;
        for (int i = 1; i < n; i++) {
            if (count == 0) {
                res = nums[i];
                count = 1;
            } else if (nums[i] == res) {
                count++;
            } else {
                count--;
            }
        }

        return res;
    }
}