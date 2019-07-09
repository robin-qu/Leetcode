// // DFS
// public class Solution {
//     /**
//      * @param s1: A string
//      * @param s2: Another string
//      * @return: whether s2 is a scrambled string of s1
//      */
//     public boolean isScramble(String s1, String s2) {
//         if (s1 == null || s2 == null || s1.length() != s2.length()) {
//             return false;
//         }
        
//         if (!isValid(s1, s2)) {
//             return false;
//         }
        
//         // base case
//         if (s1.equals(s2)) {
//             return true;
//         }
        
//         // Enumerate all the partition position
//         int n = s1.length();
//         for (int i = 1; i < n; i++) {
//             String s1l = s1.substring(0, i);  // len: i
//             String s1r = s1.substring(i, n);  // len: n - i
            
//             String s2l1 = s2.substring(0, i);  // len: i
//             String s2r1 = s2.substring(i, n);  // len: n - i
            
//             // swapped
//             String s2l2 = s2.substring(0, n - i);  // len: n - i
//             String s2r2 = s2.substring(n - i, n);  // len: i
            
//             if ((isScramble(s1l, s2l1) && isScramble(s1r, s2r1)) || (isScramble(s1l, s2r2) && isScramble(s1r, s2l2))) {
//                 return true;
//             }
//         }
        
//         return false;
//     }
    
//     // check if the two strings have the same amount of letters
//     private boolean isValid(String s1, String s2) {
//         char[] ss1 = s1.toCharArray();
//         char[] ss2 = s2.toCharArray();
//         Arrays.sort(ss1);
//         Arrays.sort(ss2);
        
//         for (int i = 0; i < ss1.length; i++) {
//             if (ss1[i] != ss2[i]) {
//                 return false;
//             }
//         }
        
//         return true;
//     }
// } 


// // Memorization search
// public class Solution {
//     /**
//      * @param s1: A string
//      * @param s2: Another string
//      * @return: whether s2 is a scrambled string of s1
//      */
//     public boolean isScramble(String s1, String s2) {
//         if (s1 == null || s2 == null || s1.length() != s2.length()) {
//             return false;
//         }
        
//         int n = s1.length();
//         int[][][] memo = new int[n][n][n];
//         return search(s1, 0, s2, 0, n, memo);
//     }
    
//     private boolean search(String s1, int i1, String s2, int i2, int len, int[][][] memo) {
//         if (memo[i1][i2][len - 1] != 0) {
//             return memo[i1][i2][len - 1] == 1 ? true : false;
//         }
        
//         String ss1 = s1.substring(i1, i1 + len);
//         String ss2 = s2.substring(i2, i2 + len);
        
//         if (!isValid(ss1, ss2)) {
//             memo[i1][i2][len - 1] = -1;
//             return false;
//         }
        
//         // base case
//         if (ss1.equals(ss2)) {
//             memo[i1][i2][len - 1] = 1;
//             return true;
//         }
        
//         // Enumerate all the partition position
//         for (int i = 1; i < len; i++) {
//             if (search(s1, i1, s2, i2, i, memo) && search(s1, i1 + i, s2, i2 + i, len - i, memo)) {
//                 memo[i1][i2][i - 1] = 1;
//                 return true;
//             } else {
//                 memo[i1][i2][i - 1] = -1;
//             }
            
//             // swapped
//             if (search(s1, i1, s2, i2 + len - i, i, memo) && search(s1, i1 + i, s2, i2, len - i, memo)) {
//                 memo[i1][i2][i - 1] = 1;
//                 return true;
//             } else {
//                 memo[i1][i2][i - 1] = -1;
//             }
//         }
        
//         memo[i1][i2][len - 1] = -1;
//         return false;
//     }
    
//     // check if the two strings have the same amount of letters
//     private boolean isValid(String s1, String s2) {
//         char[] ss1 = s1.toCharArray();
//         char[] ss2 = s2.toCharArray();
//         Arrays.sort(ss1);
//         Arrays.sort(ss2);
        
//         for (int i = 0; i < ss1.length; i++) {
//             if (ss1[i] != ss2[i]) {
//                 return false;
//             }
//         }
        
//         return true;
//     }
// } 


// DP O(n^4)
public class Solution {
    /**
     * @param s1: A string
     * @param s2: Another string
     * @return: whether s2 is a scrambled string of s1
     */
    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        
        int n = s1.length();
        boolean[][][] dp = new boolean[n][n][n + 1];
        
        // Initialize base case
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }
        
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                for (int j = 0; j < n - len + 1; j++) {
                    for (int k = 1; k < len; k++) {
                        if ((dp[i][j][k] && dp[i + k][j + k][len - k]) || (dp[i][j + len - k][k] && dp[i + k][j][len - k])) {
                            dp[i][j][len] = true;
                            break;
                        }
                    }
                }
            }
        }
        
        return dp[0][0][n];
    }
}