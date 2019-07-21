public class Solution {
    /**
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] A) {
        if (A == null || A.length < 3) {
            return 0;
        }
        
        int left = 0;
        int right = A.length - 1;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (A[mid] < A[mid + 1]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        return A[left] > A[right] ? left : right;
    }
}