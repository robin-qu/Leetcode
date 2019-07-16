// // brute force O(n^2)
// public class Solution {
//     /*
//      * @param A: An integer array
//      * @return: A list of integers includes the index of the first number and the index of the last number
//      */
//     public List<Integer> continuousSubarraySum(int[] A) {
//         if (A == null || A.length == 0) {
//             return new ArrayList<>();
//         }
        
//         int n = A.length;
//         List<Integer> res = new ArrayList<>();
//         res.add(0);
//         res.add(0);
        
//         int[] sum = new int[n + 1];
//         for (int i = 0; i < n; i++) {
//             sum[i + 1] = sum[i] + A[i];
//         }
        
//         int max = Integer.MIN_VALUE;
//         for (int i = 1; i <= n; i++) {
//             for (int j = 0; j < i; j++) {
//                 if (sum[i] - sum[j] > max) {
//                     max = sum[i] - sum[j];
//                     res.set(0, j);
//                     res.set(1, i - 1);
//                 }
//             }
//         }
        
//         return res;
//     }
// }


// brute force O(n^2)
public class Solution {
    /*
     * @param A: An integer array
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> continuousSubarraySum(int[] A) {
        if (A == null || A.length == 0) {
            return new ArrayList<>();
        }
        
        int n = A.length;
        List<Integer> res = new ArrayList<>();
        res.add(0);
        res.add(0);
        
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + A[i];
        }
        
        int max = Integer.MIN_VALUE;
        int minIdx = 0;  // the position of the minimum prefixsum encountered so far
        for (int i = 1; i <= n; i++) {
            if (sum[i] - sum[minIdx] > max) {
                max = sum[i] - sum[minIdx];
                res.set(0, minIdx);
                res.set(1, i - 1);
            }
            if (sum[i] < sum[minIdx]) {
                minIdx = i;
            }
        }
        
        return res;
    }
}