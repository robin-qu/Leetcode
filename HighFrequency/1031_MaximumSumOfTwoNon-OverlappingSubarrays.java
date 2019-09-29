// // O(n^2)time O(1)space
// class Solution {
//     public int maxSumTwoNoOverlap(int[] A, int L, int M) {
//         if (A == null || A.length == 0) {
//             return 0;
//         }
        
//         int n = A.length;
//         int sum = maxSum(A, L, 0, L - 1);
//         int max = sum + maxSum(A, M, L, n - 1);
        
//         for (int i = 1; i + L - 1 < n; i++) {
//             sum -= A[i - 1];
//             sum += A[i + L - 1];
            
//             max = Math.max(max, sum + Math.max(maxSum(A, M, 0, i - 1), maxSum(A, M, i + L, n - 1)));
//         }
        
//         return max;
//     }
    
//     private int maxSum(int[] A, int M, int left, int right) {
//         if (right - left + 1 < M) {
//             return 0;
//         }
        
//         int sum = 0;
//         for (int i = left; i < left + M; i++) {
//             sum += A[i];
//         }
//         int max = sum;
        
//         for (int i = left + 1; i + M - 1 <= right; i++) {
//             sum -= A[i - 1];
//             sum += A[i + M - 1];
//             max = Math.max(max, sum);
//         }
        
//         return max;
//     }
// }


// O(n)time O(1)space
class Solution {
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int n = A.length;
        
        for (int i = 1; i < n; i++) {
            A[i] += A[i - 1];
        }
        
        int res = A[L + M - 1];
        int maxL = A[L - 1];  // max sum of contiguous L elements before M
        int maxM = A[M - 1];  // max sum of contiguous M elements before L
        for (int i = L + M; i < n; i++) {
            maxL = Math.max(maxL, A[i - M] - A[i - M - L]);
            maxM = Math.max(maxM, A[i - L] - A[i - M - L]);
            
            res = Math.max(res, Math.max(A[i] - A[i - M] + maxL, A[i] - A[i - L] + maxM));
        }
        
        return res;
    }
}