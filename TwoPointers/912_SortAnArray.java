// // Quick Sort
// class Solution {
//     public int[] sortArray(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return nums;
//         }
        
//         quickSort(nums, 0, nums.length - 1);
//         return nums;
//     }
    
//     private void quickSort(int[] nums, int left, int right) {
//         if (left >= right) {
//             return;
//         }
        
//         int l = left;
//         int r = right;
//         int pivot = nums[left];
        
//         while (l <= r) {
//             while (l <= r && nums[l] <= pivot) {
//                 l++;
//             }
//             while (l <= r && nums[r] > pivot) {
//                 r--;
//             }
            
//             if (l <= r) {
//                 swap(nums, l, r);
//                 l++;
//                 r--;
//             }
//         } // post: r l  or  r x l
        
//         if (r + 1 < nums.length && nums[r + 1] < pivot) {
//             swap(nums, left, r + 1);
//             quickSort(nums, left, r);
//             quickSort(nums, l, right);
//         } else {
//             swap(nums, left, r);
//             quickSort(nums, left, r - 1);
//             quickSort(nums, l, right);
//         }
//     }
    
//     private void swap(int[] nums, int i, int j) {
//         int temp = nums[i];
//         nums[i] = nums[j];
//         nums[j] = temp;
//     }
// }

// Quick Sort
class Solution {
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }
    
    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        
        int l = left;
        int r = right;
        int pivot = nums[left];
        
        while (l < r) {
            while (l < r && nums[l] <= pivot) {
                l++;
            }
            while (l < r && nums[r] > pivot) {
                r--;
            }
            
            if (l < r) {
                swap(nums, l, r);
                l++;
                r--;
            }
        } // post: r(l)  or  r l
        
        if (r == l) {
            if (nums[r] <= pivot) {
                swap(nums, left, r);
                quickSort(nums, left, r - 1);
                quickSort(nums, r + 1, right);
            } else {
                swap(nums, left, r - 1);
                quickSort(nums, left, r - 2);
                quickSort(nums, r, right);
            }
        } else {
            swap(nums, left, r);
            quickSort(nums, left, r - 1);
            quickSort(nums, l, right);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}