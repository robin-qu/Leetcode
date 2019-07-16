// // brute force O(n^2)
// public class Solution {
//     /**
//      * @param A: An integer array
//      * @param start: An integer
//      * @param end: An integer
//      * @return: the number of possible answer
//      */
//     public int subarraySumII(int[] A, int start, int end) {
//         if (A == null || A.length == 0) {
//             return 0;
//         }
        
//         int n = A.length;
        
//         int[] sum = new int[n + 1];  // prefixsum
//         for (int i = 0; i < n; i++) {
//             sum[i + 1] = sum[i] + A[i];
//         }
        
//         int res = 0;
//         int left = 0;
//         int right = 1;
//         for (right = 1; right <= n; right++) {
//             for (left = 0; left < right; left++) {
//                 if (sum[right] - sum[left] >= start &&
//                     sum[right] - sum[left] <= end) {
//                     res++;
//                 }
//             }
//         }
        
//         return res;
//     }
// }


// two pointers O(n)
public class Solution {
    /**
     * @param A: An integer array
     * @param start: An integer
     * @param end: An integer
     * @return: the number of possible answer
     */
    public int subarraySumII(int[] A, int start, int end) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int n = A.length;
        
        int[] sum = new int[n + 1];  // prefixsum
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + A[i];
        }
        
        int res = 0;
        int left = 0;  // boundary of sum <= end(first valid position)
        int right = 0;  // boundary of sum >= start(one after the last valid position)
        //     left  ...   right ... i
        for (int i = 1; i <= n; i++) {
            while (left < i && sum[i] - sum[left] > end) {
                left++;
            }
            while (right < i && sum[i] - sum[right] >= start) {
                right++;
            }
            res += right - left;
        }
        
        return res;
    }
}