public class Solution {
    /**
     * @param A: an integer array
     * @param k: An integer
     * @return: an integer
     */
    public int postOffice(int[] A, int k) {
        if (A == null || A.length == 0 || k >= A.length) {
            return 0;
        }
        
        int n = A.length;
        Arrays.sort(A);  // the medium of a sorted array is the point that mimimizes the sum of distance of all the points to that point
        
        int[][] dist = getDist(A);  // dist[i][j] is the minimum distance sum of building one post office from house i to house j
        
        int[][] dp = new int[n + 1][k + 1];  // dp[i][j] is the minimum distance sum of building j post office from house 1 to house i
        
        // initialization (only one post office)
        for (int i = 0; i <= n; i++) {
            dp[i][1] = dist[1][i];
        }
        
        for (int p = 2; p <= k; p++) {
            for (int i = p; i <= n; i++) {  // at least p houses
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < i; j++) {  // j is partitioning point
                    min = Math.min(min, dp[j][p - 1] + dist[j + 1][i]);
                }
                dp[i][p] = min;
            }
        }
        
        return dp[n][k];
    }
    
    
    private int[][] getDist(int[] A) {
        int n = A.length;
        int[][] dist = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int mid = i + (j - i) / 2;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += Math.abs(A[k] - A[mid]);
                }
                dist[i + 1][j + 1] = sum;
            }
        }
        
        return dist;
    }
}