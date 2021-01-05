class Solution {
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;

        int res = 0;
        int count = 0;
        for (int num : nums) {
            if (count == 0) {
                count++;
                res = num;
            } else if (num == res) {
                count++;
            } else {
                count--;
            }
        }

        return res;
    }
}