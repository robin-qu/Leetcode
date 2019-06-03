// public class Solution {
//     /**
//      * @param k: An integer
//      * @param nums: An integer array
//      * @return: kth smallest element
//      */
//     public int kthSmallest(int k, int[] nums) {
//         if (nums == null || nums.length == 0 || k > nums.length) {
//             return -1;
//         }
        
//         return quickSelect(k, nums, 0, nums.length - 1);
//     }
    
//     private int quickSelect(int k, int[] nums, int left, int right) {
//         if (left == right) {
//             return nums[left];
//         }
        
//         int pivot = nums[left];
//         int smaller = left;
//         int bigger = right;
//         while (smaller < bigger) {
//             while (smaller < bigger && nums[smaller] <= pivot) {
//                 smaller++;
//             }
            
//             while (smaller < bigger && nums[bigger] > pivot) {
//                 bigger--;
//             }
            
//             if (smaller < bigger) {
//                 swap(nums, smaller, bigger);
//                 smaller++;
//                 bigger--;
//             }
//         }
        
//         int pivotIdx;
//         if (nums[smaller] > pivot) {
//             swap(nums, left, smaller - 1);
//             pivotIdx = smaller - 1;
//         } else {
//             swap(nums, left, smaller);
//             pivotIdx = smaller;
//         }
        
//         if (pivotIdx < k - 1) {
//             return quickSelect(k, nums, pivotIdx + 1, right);
//         } else if (pivotIdx > k - 1) {
//             return quickSelect(k, nums, left, pivotIdx - 1);
//         } else {
//             return nums[pivotIdx];
//         }
//     }
    
//     private void swap(int[] nums, int i, int j) {
//         int temp = nums[i];
//         nums[i] = nums[j];
//         nums[j] = temp;
//     }
// }

// more concise
public class Solution {
    /**
     * @param k: An integer
     * @param nums: An integer array
     * @return: kth smallest element
     */
    public int kthSmallest(int k, int[] nums) {
        if (nums == null || nums.length == 0 || k > nums.length) {
            return -1;
        }
        
        return quickSelect(k, nums, 0, nums.length - 1);
    }
    
    private int quickSelect(int k, int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        
        int pivot = nums[left + (right - left) / 2];
        int smaller = left;
        int bigger = right;
        while (smaller <= bigger) {
            while (smaller <= bigger && nums[smaller] < pivot) {
                smaller++;
            }
            
            while (smaller <= bigger && nums[bigger] > pivot) {
                bigger--;
            }
            
            if (smaller <= bigger) {
                swap(nums, smaller, bigger);
                smaller++;
                bigger--;
            }
        }
        
        if (k - 1 <= bigger && bigger >= left) {
            return quickSelect(k, nums, left, bigger);
        } else if (k - 1 >= left && smaller <= right) {
            return quickSelect(k, nums, smaller, right);
        } else {
            return nums[bigger];
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}