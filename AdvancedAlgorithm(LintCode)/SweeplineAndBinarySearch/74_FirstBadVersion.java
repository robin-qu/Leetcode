/**
 * public class SVNRepo {
 *     public static boolean isBadVersion(int k);
 * }
 * you can use SVNRepo.isBadVersion(k) to judge whether 
 * the kth code version is bad or not.
*/
public class Solution {
    /**
     * @param n: An integer
     * @return: An integer which is the first bad version.
     */
    public int findFirstBadVersion(int n) {
        int left = 1;
        int right = n;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (SVNRepo.isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        
        return SVNRepo.isBadVersion(left) ? left : right;
    }
}