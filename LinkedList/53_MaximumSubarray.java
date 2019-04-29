// // Devide and Conquer
// class Solution {
//     public int maxSubArray(int[] nums) {
//         if (nums.length == 0) return 0;
//         return divide(nums, 0, nums.length - 1);
//     }
    
//     private int divide(int[] nums, int left, int right) {
//         if (left == right) return nums[left];
        
//         int mid = (left + right) / 2;
//         int leftMax = divide(nums, left, mid);
//         int rightMax = divide(nums, mid + 1, right);
        
//         int leftPointer = mid;
//         int leftSum = nums[leftPointer];
//         int leftMiddleMax = leftSum;
//         while (leftPointer > left) {
//             leftPointer--;
//             leftSum += nums[leftPointer];
//             leftMiddleMax = Math.max(leftSum, leftMiddleMax);
//         }
        
//         int rightPointer = mid + 1;
//         int rightSum = nums[rightPointer];
//         int rightMiddleMax = rightSum;
//         while (rightPointer < right) {
//             rightPointer++;
//             rightSum += nums[rightPointer];
//             rightMiddleMax = Math.max(rightSum, rightMiddleMax);
//         }

        
//         return Math.max(leftMax, Math.max((leftMiddleMax + rightMiddleMax), rightMax));
//     }
// }


// Prefix Sum
class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int minPrefixSum = 0;
        int prefixSum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            max = Math.max(max, prefixSum - minPrefixSum);
            minPrefixSum = Math.min(prefixSum, minPrefixSum);
        }
        return max;
    }
}

// //DP (Greedy)
// class Solution {
//     public int maxSubArray(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
        
//         int sum = 0;
//         int max = Integer.MIN_VALUE;
//         for (int i = 0; i < nums.length; i++) {
//             sum += nums[i];
//             max = Math.max(sum, max);
//             sum = Math.max(0, sum);
            
// //             sum = Math.max(nums[i] + sum, nums[i]);
// //             max = max > sum ? max : sum;
//         }
//         return max;
//     }
// }