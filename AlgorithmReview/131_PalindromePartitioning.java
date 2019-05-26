// class Solution {
//     public List<List<String>> partition(String s) {
//         List<List<String>> res = new ArrayList<>();
//         List<String> partition = new ArrayList<>();
        
//         helper(s, 0, partition, res);
        
//         return res;
//     }
    
//     private void helper(String s, int start, 
//                         List<String> partition, 
//                         List<List<String>> res) {
//         if (start > s.length()) {
//             return;
//         }
        
//         if (start == s.length()) {
//             res.add(new ArrayList<>(partition));
//         }
        
//         for (int i = start; i < s.length(); i++) {
//             if (!isPalindrome(s.substring(start, i + 1))) {
//                 continue;
//             }
//             partition.add(s.substring(start, i + 1));
//             helper(s, i + 1, partition, res);
//             partition.remove(partition.size() - 1);
//         }
//     }
    
//     private boolean isPalindrome(String s) {
//         int left = 0;
//         int right = s.length() - 1;
        
//         while (left < right) {
//             if (s.charAt(left) != s.charAt(right)) {
//                 return false;
//             }
//             left++;
//             right--;
//         }
        
//         return true;
//     }
// }

class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> partition = new ArrayList<>();
        
        boolean[][] isPalindrome = getIsPalindrome(s);
        
        helper(s, 0, isPalindrome, partition, res);
        
        return res;
    }
    
    private void helper(String s, int start, 
                        boolean[][] isPalindrome,
                        List<String> partition, 
                        List<List<String>> res) {
        if (start > s.length()) {
            return;
        }
        
        if (start == s.length()) {
            res.add(new ArrayList<>(partition));
        }
        
        for (int i = start; i < s.length(); i++) {
            if (!isPalindrome[start][i]) {
                continue;
            }
            partition.add(s.substring(start, i + 1));
            helper(s, i + 1, isPalindrome, partition, res);
            partition.remove(partition.size() - 1);
        }
    }
    
    private boolean[][] getIsPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        
        for (int i = 1; i < n; i++) {
            dp[i - 1][i] = s.charAt(i - 1) == s.charAt(i);
        }
        
        for (int i = n - 3; i >= 0; i--) {     // from back!!!!!!!
            for (int j = i + 2; j < n; j++) {
                dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
            }
        }
        
        return dp;
    }
}