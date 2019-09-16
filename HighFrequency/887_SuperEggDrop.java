// // dp brute force O(KN^2)time O(KN)space
// class Solution {
//     public int superEggDrop(int K, int N) {
//         if (K <= 0 || N <= 0) {
//             return 0;
//         }
        
//         if (K == 1) {
//             return N;
//         }
        
//         int[][] dp = new int[K + 1][N + 1];  // dp[i][j] represents the minimum number of moves when there are i eggs and j floors
        
//         for (int i = 1; i <= N; i++) {
//             dp[1][i] = i;
//         }
        
//         for (int i = 1; i <= K; i++) {
//             dp[i][1] = 1;
//         }
        
//         for (int i = 2; i <= K; i++) {
//             for (int j = 2; j <= N; j++) {
//                 int min = N;
//                 for (int k = 1; k <= j; k++) {
//                     min = Math.min(min, 1 + Math.max(dp[i - 1][k - 1], dp[i][j - k]));
//                 }
            
//                 dp[i][j] = min;
//             }
//         }
        
//         return dp[K][N];
//     }
// }


// dp with binary search O(KNlogN)time O(KN)space
class Solution {
    public int superEggDrop(int K, int N) {
        int[][] dp = new int[K + 1][N + 1];
        return helper(K, N, dp);
    }
    
    private int helper(int K, int N, int[][] dp) {
        if (K <= 0 || N <= 0) {
            return 0;
        }
        
        if (K == 1) {
            return N;
        }
        
        if (dp[K][N] > 0) {
            return dp[K][N];
        }
        
        int low = 1;
        int high = N;
        int res = N;
        
        while (low < high) {
            int mid = low + (high - low) / 2;
            int left = helper(K - 1, mid - 1, dp);
            int right = helper(K, N - mid, dp);
            
            res = Math.min(res, Math.max(left, right) + 1);
            
            if (left > right) {
                high = mid;
            } else if (left < right) {
                low = mid + 1;
            } else {
                break;
            }
        }
        
        dp[K][N] = res;
        return dp[K][N];
    }
}