class Solution {
    public int peakIndexInMountainArray(int[] A) {
        int low = 0;
        int high = A.length - 1;
        
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            
            if (A[mid] < A[mid + 1]) {
                low = mid;
            } else {
                high = mid;
            }
        }
            
        if (A[low] < A[low + 1]) {
            return high;
        }
        return low;
    }
}