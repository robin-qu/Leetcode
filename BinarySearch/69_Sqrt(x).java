class Solution {
    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        
        int low = 1;
        int high = x / 2 + 1;
        int mid;
        
        while (low + 1 < high) {
            mid = low + (high - low) / 2;
            if (x / mid == mid) {
                return mid;
            } else if (x / mid < mid) {
                high = mid;
            } else {
                low = mid;
            }
        }
        
        if (x / high > high) {
            return high;
        }
        return low;
    }
}