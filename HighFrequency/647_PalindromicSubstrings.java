// // two pointers O(n^2)time O(1)space
// class Solution {
//     public int countSubstrings(String s) {
//         if (s == null || s.length() == 0) {
//             return 0;
//         }
        
//         int n = s.length();
//         char[] ss = s.toCharArray();
        
//         int res = 0;
//         for (int i = 0; i < n; i++) {
//             res += expand(i, i, ss);
//             if (i > 0) {
//                 res += expand(i - 1, i, ss);
//             }
//         }
        
//         return res;
//     }
    
//     private int expand(int i1, int i2, char[] ss) {
//         int left = i1;
//         int right = i2;
//         int res = 0;
        
//         while (left >= 0 && right < ss.length && ss[left] == ss[right]) {
//             res++;
//             left--;
//             right++;
//         }
        
//         return res;
//     }
// }


// dp O(n^2)time O(n^2)space
class Solution {
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int n = s.length();
        char[] ss = s.toCharArray();
        boolean dp[][] = new boolean[n][n];
        
        int res = 0;
        
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                if (i == j) {
                    dp[i][j] = true;
                } else if (i + 1 == j) {
                    dp[i][j] = (ss[i] == ss[j]);
                } else {
                    dp[i][j] = (ss[i] == ss[j] && dp[i + 1][j - 1]);
                }
                
                if (dp[i][j]) {
                    res++;
                }
            }
        }
        
        return res;
    }
}