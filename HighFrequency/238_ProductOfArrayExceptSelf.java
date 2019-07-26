// // O(n)time O(n)space
// class Solution {
//     public int[] productExceptSelf(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return nums;
//         }
        
//         int n = nums.length;
        
//         int[] left = new int[n];  // the product of i numbers from left
//         int[] right = new int[n];  // the product of i numbers from right
//         int[] res = new int[n];
        
//         left[0] = nums[0];
//         for (int i = 1; i < n; i++) {
//             left[i] = left[i - 1] * nums[i];
//         }
        
//         right[n - 1] = nums[n - 1];
//         for (int i = n - 2; i >= 0; i--) {
//             right[i] = right[i + 1] * nums[i];
//         }
        
//         for (int i = 0; i < n; i++) {
//             int leftProduct = i - 1 >= 0 ? left[i - 1] : 1;
//             int rightProduct = i + 1 < n ? right[i + 1] : 1;
//             res[i] = leftProduct * rightProduct;
//         }
        
//         return res;
//     }
// }


// O(n)time O(1)space
class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        
        int n = nums.length;
        int left = 1;  // left is the left product
        int[] res = new int[n];  // res initially stores the right product
        
        res[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            res[i] = res[i + 1] * nums[i];
        }
        
        for (int i = 0; i < n; i++) {
            res[i] = i + 1 < n ? left * res[i + 1] : left;
            left *= nums[i];
        }
        
        return res;
    }
}