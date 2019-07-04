// // O(n) space
// public class Solution {
//     /**
//      * @param n: An integer
//      * @return: A boolean which equals to true if the first player will win
//      */
//     public boolean firstWillWin(int n) {
//         if (n <= 0) {
//             return false;
//         }
        
//         if (n <= 2) {
//             return true;
//         }
        
//         boolean[] dp = new boolean[n + 1];
//         dp[1] = true;
//         dp[2] = true;
        
//         for (int i = 3; i <= n; i++) {
//             if (!dp[i - 1] || !dp[i - 2]) {
//                 dp[i] = true;
//             }
//         }
        
//         return dp[n];
//     }
// }


// O(1) space
public class Solution {
    /**
     * @param n: An integer
     * @return: A boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int n) {
        if (n <= 0) {
            return false;
        }
        
        if (n <= 2) {
            return true;
        }
        
        boolean first = true;
        boolean second = true;
        
        for (int i = 3; i <= n; i++) {
            if (!first || !second) {
                first = second;
                second = true;
            } else {
                first = second;
                second = false;
            }
        }
        
        return second;
    }
}