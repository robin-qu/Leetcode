// // DP O(m*n^2)time O(mn)space
// class Solution {
//     public int splitArray(int[] nums, int m) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
        
//         int n = nums.length;
//         long[] sum = new long[n + 1];  // prefix sum
//         for (int i = 0; i < n; i++) {
//             sum[i + 1] = sum[i] + nums[i];
//         }
        
//         long[][] dp = new long[n + 1][m];  // dp[i][j] represents minimal largest sum for split the first i elements into j + 1 parts
        
//         for (int i = 1; i <= n; i++) {
//             for (int j = 0; j < m; j++) {
//                 if (j == 0) {  // don't split, just the sum
//                     dp[i][j] = dp[i - 1][j] + nums[i - 1];
//                     continue;
//                 }
                
//                 if (i <  j + 1) {  // cannot split
//                     dp[i][j] = 0;
//                     continue;
//                 }
//                 // loop over all the possible split position, pick the smallest one
//                 dp[i][j] = Math.max(dp[i - 1][j - 1], nums[i - 1]);
//                 for (int k = 2; k <= i; k++) {
//                     dp[i][j] = Math.min(Math.max(dp[i - k][j - 1], sum[i] - sum[i - k]), dp[i][j]);
//                 }
//             }
//         }
        
//         return (int) dp[n][m - 1];
//     }
// }


// Binary Search(Copy books!!!)
class Solution {
    /**
     * @param pages: an array of integers
     * @param k: An integer
     * @return: an integer
     */
    public int splitArray(int[] pages, int k) {
        if (pages == null || pages.length == 0) {
            return 0;
        }
        
        int left = 0;
        int right = 0;
        for (int page : pages) {
            left = Math.max(left, page);
            right += page;
        }
        int mid;
        
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            
            if (getK(pages, mid) > k) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        return getK(pages, left) <= k ? left : right;
    }
    
    private int getK(int[] pages, int time) {
        int res = 1;
        int count = 0;
        for (int page : pages) {
            if (count + page > time) {
                res++;
                count = 0;
            }
            count += page;
        }
        
        return res;
    }
}