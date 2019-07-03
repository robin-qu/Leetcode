// // DP
// class Solution {
//     /*
//      * @param envelopes: a number of envelopes with widths and heights
//      * @return: the maximum number of envelopes
//      */
//     class EnvelopeComparator implements Comparator<int[]> {
//         @Override
//         public int compare(int[] a, int[] b) {
//             if (a[0] != b[0]) {
//                 return a[0] - b[0];
//             }
            
//             return a[1] - b[1];
//         }
//     }
    
//     public int maxEnvelopes(int[][] envelopes) {
//         if (envelopes == null || envelopes.length == 0 ||
//             envelopes[0] == null || envelopes[0].length == 0) {
//             return 0;
//         }
        
//         Arrays.sort(envelopes, new EnvelopeComparator());
//         int n = envelopes.length;
//         int[] dp = new int[n];
//         Arrays.fill(dp, 1);
        
//         for (int i = 1; i < n; i++) {
//             for (int j = i - 1; j >= 0; j--) {
//                 if (canFit(envelopes[i], envelopes[j])) {
//                     dp[i] = Math.max(dp[i], 1 + dp[j]);
//                 }
//             }
//         }
        
//         int max = dp[0];
//         for (int i = 1; i < n; i++) {
//             max = Math.max(max, dp[i]);
//         }
        
//         return max;
//     }
    
//     private boolean canFit(int[] outer, int[] inner) {
//         return outer[0] > inner[0] && outer[1] > inner[1];
//     }
// }


// DP + binary search (similar as longest increasing subsquence)
class Solution {
    /*
     * @param envelopes: a number of envelopes with widths and heights
     * @return: the maximum number of envelopes
     */
    class EnvelopeComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] a, int[] b) {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            
            return b[1] - a[1];  // descending order in the second dimension
        }
    }
    
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 ||
            envelopes[0] == null || envelopes[0].length == 0) {
            return 0;
        }
        
        Arrays.sort(envelopes, new EnvelopeComparator());
        int n = envelopes.length;
        int[] dp = new int[n + 1];
        dp[0] = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        int res = 1;
        
        // find the longest increasing subsequence in the second dimension
        for (int i = 0; i < n; i++) {
            int idx = binarySearch(dp, envelopes[i][1]);
            res = Math.max(res, idx);
            dp[idx] = envelopes[i][1];
        }
        
        return res;
    }
    
    private int binarySearch(int[] dp, int val) {
        int left = 0;
        int right = dp.length - 1;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (dp[mid] >= val) {
                right = mid;
            } else {
                left = mid;
            }
        }
        
        return dp[left] >= val ? left : right;
    }
}