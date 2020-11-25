class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;

        int[] inc = new int[n];
        Arrays.fill(inc, Integer.MAX_VALUE);

        int res = 0;
        for (int num : nums) {
            int idx = binarySearch(num, inc);
            inc[idx] = num;
            res = Math.max(res, idx + 1);
        }

        return res;
    }

    private int binarySearch(int num, int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (num <= nums[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }

        if (nums[left] >= num) {
            return left;
        }

        return right;
    }
}