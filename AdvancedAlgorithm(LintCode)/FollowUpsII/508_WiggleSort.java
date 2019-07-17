// initial version O(nlogn)time O(n)space
public class Solution {
    /*
     * @param nums: A list of integers
     * @return: nothing
     */
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        int n = nums.length;
        quickSort(0, n - 1, nums);
        
        for (int i = 1; i < n - 1; i += 2) {
            swap(i, i + 1, nums);
        }
    }
    
    private void quickSort(int start, int end, int[] nums) {
        if (start >= end) {
            return;
        }
        
        int left = start;
        int right = end;
        int pivot = nums[start + (end - start) / 2];
        
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            
            if (left <= right) {
                swap(left, right, nums);
                left++;
                right--;
            }
        }
        
        quickSort(start, right, nums);
        quickSort(left, end, nums);
    }
    
    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}


// // initial version O(nlogn)time O(n)space
// public class Solution {
//     /*
//      * @param nums: A list of integers
//      * @return: nothing
//      */
//     public void wiggleSort(int[] nums) {
//         if (nums == null || nums.length <= 1) {
//             return;
//         }
        
//         int n = nums.length;
        
//         for (int i = 1; i < n; i++) {
//             if (i % 2 == 1 && nums[i] < nums[i - 1] || i % 2 == 0 && nums[i] > nums[i - 1]) {
//                 swap(i, i - 1, nums);
//             }
//         }
//     }
    
//     private void swap(int i, int j, int[] nums) {
//         int temp = nums[i];
//         nums[i] = nums[j];
//         nums[j] = temp;
//     }
// }