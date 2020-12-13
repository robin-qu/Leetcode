// class Solution {
//     public void rotate(int[] nums, int k) {
//         if (nums == null || nums.length <= 1) {
//             return;
//         }

//         int n = nums.length;
//         for (int i = 0; i < k % n; i++) {
//             rotateByOne(nums);
//         }
//     }

//     private void rotateByOne(int[] nums) {
//         int temp = nums[nums.length - 1];
//         for (int i = nums.length - 1; i > 0; i--) {
//             nums[i] = nums[i - 1];
//         }
//         nums[0] = temp;
//     }
// }



class Solution {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int n = nums.length;
        k = k % n;
        reverse(0, n - 1, nums);
        reverse(0, k - 1, nums);
        reverse(k, n - 1, nums);
    }

    private void reverse(int i, int j, int[] nums) {
        int left = i;
        int right = j;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
