public class Solution {
    /*
     * @param A: An integer array.
     * @return: nothing
     */
    public void rerange(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }
        
        partition(A);
        interleaving(A);
    }
    
    private void partition(int[] A) {
        int left = 0;
        int right = A.length - 1;
        
        while (left < right) {
            while (left < right && A[left] > 0) {
                left++;
            }
            
            while (left < right && A[right] <= 0) {
                right--;
            }
            
            if (left < right) {
                swap(A, left, right);
                left++;
                right--;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private void interleaving(int[] A) {
        int left = 0;
        int right = A.length - 1;
        if (A.length % 2 == 1) {
            if (A[A.length / 2] > 0) {
                left++;
            } else {
                right--;
            }
        }
        
        while (left < right) {
            swap(A, left, right);
            left += 2;
            right -= 2;
        }
    }
} 