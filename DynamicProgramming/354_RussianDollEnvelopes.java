// // Pure DP
// class Solution {
//     public int maxEnvelopes(int[][] envelopes) {
//         if (envelopes == null || envelopes.length == 0) {
//             return 0;
//         }
        
//         int n = envelopes.length;
//         int[] dp = new int[n];
//         Arrays.fill(dp, 1);
//         Arrays.sort(envelopes, new Comparator<int[]>() {
//             @Override
//             public int compare(int[] A1, int[] A2) {
//                 if (A1[0] != A2[0]) {
//                     return A1[0] - A2[0];
//                 } else {
//                     return A1[1] - A2[1];
//                 }
//             }
//         });
        
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < i; j++) {
//                 if (fit(envelopes, j, i)) {
//                     dp[i] = Math.max(dp[i], dp[j] + 1);
//                 }
//             }
//         }
        
//         int res = Integer.MIN_VALUE;
//         for (int i = 0; i < n; i++) {
//             res = Math.max(res, dp[i]);
//         }
//         return res;
//     }
    
//     // Return whether i can fit into j
//     private boolean fit(int[][] A, int i, int j) {
//         return A[i][0] < A[j][0] && A[i][1] < A[j][1];
//     }
// }


// DP + BinarySearch
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        
        int n = envelopes.length;
        Arrays.sort(envelopes, new EnvelopeComparator());
        
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        for (int i = 0 ; i < n; i++) {
            int idx = Arrays.binarySearch(dp, envelopes[i][1]);
            if (idx < 0) {
                idx = -idx - 1;
            }
            dp[idx] = envelopes[i][1];
        }
        
        for (int i = n - 1; i >= 0; i--) {
            if (dp[i] != Integer.MAX_VALUE) {
                return i + 1;
            }
        }
        return 0;
    }
    
    // Return whether i can fit into j
    private boolean fit(int[][] A, int i, int j) {
        return A[i][0] < A[j][0] && A[i][1] < A[j][1];
    }
    
    class EnvelopeComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] A1, int[] A2) {
            if (A1[0] != A2[0]) {
                return A1[0] - A2[0];
            } else {
                return A2[1] - A1[1];
            }
        }
    }
}