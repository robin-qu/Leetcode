// // O(n) space
// public class Solution {
//     /**
//      * @param values: a vector of integers
//      * @return: a boolean which equals to true if the first player will win
//      */
//     public boolean firstWillWin(int[] values) {
//         if (values == null || values.length == 0) {
//             return false;
//         }
        
//         int n = values.length;
//         int[] dp = new int[n + 1];  // the difference of the sums of the two users   X - Y
//         dp[n] = 0;
//         dp[n - 1] = values[n - 1];
        
//         for (int i = n - 2; i >= 0; i--) {
//             dp[i] = Math.max(values[i] - dp[i + 1], values[i] + values[i + 1] - dp[i + 2]);
//         }
        
//         return dp[0] >= 0;
//     }
// }


// O(1) space
public class Solution {
    /**
     * @param values: a vector of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        if (values == null || values.length == 0) {
            return false;
        }
        
        int n = values.length;
        int second = 0;
        int first = values[n - 1];
        
        for (int i = n - 2; i >= 0; i--) {
            int temp = first;
            first = Math.max(values[i] - first, values[i] + values[i + 1] - second);
            second = temp;
        }
        
        return first >= 0;
    }
}