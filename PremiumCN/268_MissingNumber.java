class Solution {
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == n) {
                continue;
            }
            while (nums[i] != i && nums[i] != n) {
                swap(i, nums[i], nums);
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                return i;
            }
        }

        return n;
    }

    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}


// class Solution {
//     public int missingNumber(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }

//         int n = nums.length;
//         int sum = (0 + n) * (n + 1) / 2;
//         for (int num : nums) {
//             sum -= num;
//         }
//         return sum;
//     }
// }