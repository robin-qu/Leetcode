// voting algorithm O(n)time O(1)space
class Solution {
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int num = nums[0];
        int count = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] == num) {
                count++;
            } else {
                if (count > 0) {
                    count--;
                } else {
                    num = nums[i];
                    count = 1;
                }
            }
        }
        
        return num;
    }
}