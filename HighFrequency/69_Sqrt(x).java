// binary search O(logn)time O(1)space
class Solution {
    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        
        int right = x / 2;
        int left = 0;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (mid < x / mid) {
                left = mid;
            } else if (mid > x / mid) {
                right = mid;
            } else {
                return mid;
            }
        }
        
        if (right <= x / right) {
            return right;
        }
        
        return left;
    }
}