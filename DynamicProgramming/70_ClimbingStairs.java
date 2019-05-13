// class Solution {
//     public int climbStairs(int n) {
//         return climbStair(n, 0, 0);
        
//     }
    
//     private int climbStair(int n, int level, int count) {
//         if (level == n) count++;
//         else {
//             for (int i = 1; i <= 2; i++) {
//                 if (level + i <= n) {
//                     level += i;
//                     count = climbStair(n, level, count);
//                     level -= i;
//                 }
//             }
//         }
//         return count;
//     }
// }

// // brute approach
// class Solution {
//     public int climbStairs(int n) {
//         return climbStair(n, 0);
//     }
    
//     private int climbStair(int n, int level) {
//         if (level > n) return 0;
//         if (level == n) return 1;
//         return climbStair(n, level + 1) + climbStair(n, level + 2);
//     }
// }

// class Solution {
//     public int climbStairs(int n) {
//         int[] memo = new int[n + 1];
//         return climbStair(n, 0, memo);
//     }
    
//     private int climbStair(int n, int level, int[] memo) {
//         if (level > n) return 0;
//         if (level == n) return 1;
//         if (memo[level] > 0) return memo[level];
//         memo[level] = climbStair(n, level + 1, memo) + climbStair(n, level + 2, memo);
//         return memo[level];
//     }
// }


// class Solution {
//     public int climbStairs(int n) {
//         if (n <= 0) {
//             return 0;
//         }
        
//         if (n == 1) {
//             return 1;
//         }
        
//         if (n == 2) {
//             return 2;
//         }
        
//         int[] dp = new int[n + 1];
//         dp[1] = 1;
//         dp[2] = 2;
//         for (int i = 3; i <= n; i++) {
//             dp[i] = dp[i - 1] + dp[i - 2];
//         }
        
//         return dp[n];
//     }
// }


// Optimized
class Solution {
    public int climbStairs(int n) {
        if (n <= 0) {
            return 0;
        }
        
        if (n == 1) {
            return 1;
        }
        
        int curr = 2;
        int prev = 1;
        
        for (int i = 3; i <= n; i++) {
            int temp = curr + prev;
            prev = curr;
            curr = temp;
        }
        
        return curr;
    }
}
