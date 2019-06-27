public class Solution {
    /**
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int left = 1;
        int right = A.length - 2;
        int mid;
        
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            
            if (A[mid] > A[mid + 1]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        
        return A[left] < A[right] ? right : left;
    }
}