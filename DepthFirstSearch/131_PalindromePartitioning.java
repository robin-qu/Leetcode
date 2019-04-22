// class Solution {
//     public List<List<String>> partition(String s) {
//         List<List<String>> res = new ArrayList<>();
        
//         if (s == null) {
//             return res;
//         }
        
//         if (s.length() == 0) {
//             res.add(new ArrayList<>());
//             return res;
//         }
        
//         List<String> partition = new ArrayList<>();
//         helper(s, 0, partition, res);
        
//         return res;
//     }
    
//     private void helper(String s,
//                         int idx,
//                         List<String> partition,
//                         List<List<String>> res) {
//         if (idx >= s.length()) {
//             res.add(new ArrayList<String>(partition));
//         }
        
//         for (int i = idx; i < s.length(); i++) {
//             if (isPalindrome(s.substring(idx, i + 1))) {
//                 partition.add(s.substring(idx, i + 1));
//                 helper(s, i + 1, partition, res);
//                 partition.remove(partition.size() - 1);
//             }
//         }
//     }
    
//     private boolean isPalindrome(String s) {
//         int start = 0;
//         int end = s.length() - 1;
//         while (start <= end) {
//             if (s.charAt(start) != s.charAt(end)) {
//                 return false;
//             }
//             start++;
//             end--;
//         }
        
//         return true;
//     }
// }

// DP
public class Solution {
    /**
     * @param s: A string
     * @return: A list of lists of string
     */
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        
        if (s == null) {
            return res;
        }
        
        if (s.length() == 0) {
            res.add(new ArrayList<>());
            return res;
        }
        
        boolean[][] isPalindrome = getIsPalindrome(s);
        List<Integer> partition = new ArrayList<>();
        helper(s, isPalindrome, 0, partition, res);
        
        return res;
    }
    
    private boolean[][] getIsPalindrome(String s) {
        int len = s.length();
        boolean[][] isPalindrome = new boolean[len][len];
        
        for (int i = 0; i < len; i++) {
            isPalindrome[i][i] = true;
        }
        
        for (int i = 0; i < len - 1; i++) {
            isPalindrome[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
        }
        
        for (int i = len - 3; i >= 0; i--) {
            for (int j = i + 2; j < len; j ++) {
                isPalindrome[i][j] = isPalindrome[i + 1][j - 1] && 
                                     s.charAt(i) == s.charAt(j);
            }
        }
        
        return isPalindrome;
    }
    
    private void helper(String s,
                        boolean[][] isPalindrome,
                        int idx,
                        List<Integer> partition,
                        List<List<String>> res) {
        if (idx >= s.length()) {
            addResult(s, partition, res);
        }
        
        for (int i = idx; i < s.length(); i++) {
            if (isPalindrome[idx][i]) {
                partition.add(i);
                helper(s, isPalindrome, i + 1, partition, res);
                partition.remove(partition.size() - 1);
            }
        }
    }
    
    private void addResult(String s, 
                           List<Integer> partition, 
                           List<List<String>> res) {
        List<String> list = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < partition.size(); i++) {
            list.add(s.substring(start, partition.get(i) + 1));
            start = partition.get(i) + 1;
        }
        res.add(list);
    }
}