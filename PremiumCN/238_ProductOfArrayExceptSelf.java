class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int n = nums.length;

        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int prod = 1;
        for (int i = n - 2; i >= 0; i--) {
            prod *= nums[i + 1];
            res[i] *= prod;
        }

        return res;
    }
}