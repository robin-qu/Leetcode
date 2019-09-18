// // dfs + trie O(m^n)time O(n)space (TLE)
// class Solution {
//     private class TrieNode {
//         public TrieNode[] children;
//         public boolean isWord;
        
//         public TrieNode() {
//             children = new TrieNode[256];
//             isWord = false;
//         }
//     }
    
//     public List<String> wordBreak(String s, List<String> wordDict) {
//         if (s == null || wordDict == null || wordDict.size() == 0) {
//             return new ArrayList<>();
//         }
        
//         TrieNode root = buildTree(wordDict);
        
//         List<String> res = new ArrayList<>();
        
//         dfs(0, s, root, root, new StringBuilder(), res);
        
//         return res;
//     }
    
//     private void dfs(int idx, String s, TrieNode curr, TrieNode root, StringBuilder sb, List<String> res) {
//         if (idx == s.length()) {
//             if (curr.isWord) {
//                 res.add(sb.toString());
//             }
//             return;
//         }
        
//         char c = s.charAt(idx);
        
//         if (curr.children[c] == null) {
//             return;
//         }
        
//         sb.append(c);
//         curr = curr.children[c];
        
//         if (curr.isWord) {
//             sb.append(' ');
//             dfs(idx + 1, s, root, root, sb, res);
//             sb.deleteCharAt(sb.length() - 1);
//         }
        
//         dfs(idx + 1, s, curr, root, sb, res);
        
//         sb.deleteCharAt(sb.length() - 1);
//     }
    
//     private TrieNode buildTree(List<String> wordDict) {
//         TrieNode root = new TrieNode();
        
//         for (String word : wordDict) {
//             TrieNode curr = root;
//             for (char c : word.toCharArray()) {
//                 if (curr.children[c] == null) {
//                     curr.children[c] = new TrieNode();
//                 }
                
//                 curr = curr.children[c];
//             }
            
//             curr.isWord = true;
//         }
        
//         return root;
//     }
// }


// dfs with memo O(m^n)time O(n)space
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict == null || wordDict.size() == 0) {
            return new ArrayList<>();
        }
        
        Set<String> set = new HashSet<>();
        List<String>[] memo = new List[s.length()];
        for (String word : wordDict) {
            set.add(word);
        }
        
        return dfs(0, s, set, memo);
    }
    
    private List<String> dfs(int idx, String s, Set<String> set, List<String>[] memo) {
        List<String> res = new ArrayList<>();
        
        if (idx == s.length()) {
            res.add("");
            return res;
        }
        
        if (memo[idx] != null) {
            return memo[idx];
        }
        
        for (int i = idx + 1; i <= s.length(); i++) {
            if (set.contains(s.substring(idx, i))) {
                List<String> list = dfs(i, s, set, memo);
                for (String words : list) {
                    res.add((s.substring(idx, i) + " " + words).trim());
                }
            }
        }
        
        memo[idx] = res;
        return res;
    }
}



// // dp (TLE)
// class Solution {
//     public List<String> wordBreak(String s, List<String> wordDict) {
//         if (s == null || wordDict == null || wordDict.size() == 0) {
//             return new ArrayList<>();
//         }
        
//         Set<String> words = new HashSet<>();
//         for (String word : wordDict) {
//             words.add(word);
//         }
        
//         int n = s.length();
//         List<String>[] dp = new List[n + 1];
//         dp[0] = new ArrayList<>();
//         dp[0].add("");
        
//         for (int i = 1; i <= n; i++) {
//             dp[i] = new ArrayList<>();
//             for (int j = i - 1; j >= 0; j--) {
//                 String curr = s.substring(j, i);
//                 if (words.contains(curr)) {
//                     if (dp[j].size() > 0) {
//                         for (String ss : dp[j]) {
//                             dp[i].add((ss + " " + curr).trim());
//                         }
//                     }
//                 }
//             }
//         }
        
//         return dp[n];
        
//     }
// }