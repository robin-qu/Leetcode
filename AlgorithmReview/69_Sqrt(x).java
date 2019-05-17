class Solution {
    public int mySqrt(int x) {
        if (x < 0) {
            return -1;
        }
        
        if (x == 0 || x == 1) {
            return x;
        }
        
        int left = 1;
        int right = x;
        int mid;
        
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            
            if (x / mid > mid) {
                left = mid;
            } else if (x / mid < mid) {
                right = mid;
            } else {
                return mid;
            }
        }
        
        if (x / right >= right) {
            return right;
        }
        
        return left;
    }
}