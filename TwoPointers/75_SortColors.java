// // One pass
// class Solution {
//     public void sortColors(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return;
//         }
        
//         int left = 0;
//         int right = nums.length - 1;
//         int curr = 0;
        
//         while (curr <= right) {
//             if (nums[curr] == 0) {
//                 swap(nums, left, curr);
//                 left++;
//                 curr++;
//             } else if (nums[curr] == 1) {
//                 curr++;
//             } else {
//                 swap(nums, right, curr);
//                 right--;
//             }
//         }
//     }
    
//     private void swap(int[] nums, int i, int j) {
//         int temp = nums[i];
//         nums[i] = nums[j];
//         nums[j] = temp;
//     }
// }

// Two pass
class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        
        int oneStart = partition(nums, 0, nums.length - 1, 0);
        partition(nums, oneStart, nums.length - 1, 1);
    }
    
    private int partition(int[] nums, int left, int right, int val) {
        int l = left;
        int r = right;
        while (l < r) {
            while (l < r && nums[l] == val) {
                l++;
            }
            while (l < r && nums[r] > val) {
                r--;
            }
            if (l < r) {
                swap(nums, l, r);
                l++;
                r--;
            }
        } // post: l(r)  r l
        
        if (nums[r] == val) {
            return r + 1;
        }
        return r;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}