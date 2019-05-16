/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        if (n < 0) {
            return -1;
        }
        
        int left = 0;
        int right = n;
        int mid;
        
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        
        return isBadVersion(left) ? left : right;
    }
}