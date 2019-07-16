public class Solution {
    /*
     * @param A: An integer array
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> continuousSubarraySumII(int[] A) {
        if (A == null || A.length == 0) {
            return new ArrayList<>();
        }
        
        int n = A.length;
        
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + A[i];
        }
        
        List<Integer> res = new ArrayList<>();
        res.add(0);
        res.add(0);
        
        int max = Integer.MIN_VALUE;
        
        // find maximum within the ordinary array
        int minIdx = 0;
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
        
        // find the maximum across the ordinary array, i.e. find the minimum within the ordinary array
        int maxIdx = 1;  // deal with specail case (choose the entire array)
        for (int i = 1; i <= n; i++) {
            if (sum[n] - (sum[i] - sum[maxIdx]) > max) {
                max = sum[n] - (sum[i] - sum[maxIdx]);
                res.set(0, i);
                res.set(1, maxIdx - 1);
            }
            if (sum[i] > sum[maxIdx]) {
                maxIdx = i;
            }
        }
        
        return res;
    }
}