// // Brute Force Trie (TLE)
// class Solution {
//     class TrieNode {
//         public TrieNode[] children;
//         public boolean isWord;
        
//         public TrieNode() {
//             this.children = new TrieNode[26];
//             this.isWord = false;
//         }
//     }
    
//     public boolean wordBreak(String s, List<String> wordDict) {
//         if (s == null || wordDict == null) {
//             return false;
//         }
        
//         TrieNode root = buildTrie(wordDict);
        
//         char[] ss = s.toCharArray();
//         TrieNode curr = root;
//         return dfs(0, ss, root, root);
//     }
    
//     private boolean dfs(int i, char[] ss, TrieNode curr, TrieNode root) {
//         if (i == ss.length - 1) {
//             if (curr.children[ss[i] - 'a'] == null) {
//                 return false;
//             }
//             return curr.children[ss[i] - 'a'].isWord;
//         }
        
//         boolean res = false;
//         if (curr.children[ss[i] - 'a'] != null) {
//             if (curr.children[ss[i] - 'a'].isWord) {
//                 res = res || dfs(i + 1, ss, root, root);
//             }
//             res = res || dfs(i + 1, ss, curr.children[ss[i] - 'a'], root);
//         }
        
//         return res;
//     }
    
//     private TrieNode buildTrie(List<String> wordDict) {
//         TrieNode root = new TrieNode();
        
//         for (String word : wordDict) {
//             TrieNode curr = root;
//             char[] ww = word.toCharArray();
//             for (int i = 0; i < ww.length; i++) {
//                 if (curr.children[ww[i] - 'a'] == null) {
//                     curr.children[ww[i] - 'a'] = new TrieNode();
//                 }
//                 curr = curr.children[ww[i] - 'a'];
//             }
            
//             curr.isWord = true;
//         }
        
//         return root;
//     }
// }


// // DP O(n^2)time O(n)space
// class Solution {
//     public boolean wordBreak(String s, List<String> wordDict) {
//         if (s == null || wordDict == null) {
//             return false;
//         }
        
//         int n = s.length();
//         boolean[] dp = new boolean[n + 1];  // dp[i] represents whether the first i characters is one or several words in the wordDict
//         dp[0] = true;
//         Set<String> set = new HashSet<>();
//         for (String word : wordDict) {
//             set.add(word);
//         }
        
//         for (int i = 1; i <= n; i++) {
//             for (int j = i - 1; j >= 0; j--) {
//                 if (dp[j] && set.contains(s.substring(j, i))) {
//                     dp[i] = true;
//                     break;
//                 }
//             }
//         }
        
//         return dp[n];
//     }
// }


// DP + trie O(n^2)time O(maxlen)space
class Solution {
    class TrieNode {
        public TrieNode[] children;
        public boolean isWord;
        
        public TrieNode() {
            this.children = new TrieNode[26];
            this.isWord = false;
        }
    }
    
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict == null) {
            return false;
        }
        
        int n = s.length();
        boolean[] dp = new boolean[n + 1];  // dp[i] represents whether the first i characters is one or several words in the wordDict
        dp[n] = true;
        TrieNode root = buildTrie(wordDict);
        
        for (int i = n - 1; i >= 0; i--) {
            TrieNode curr = root;
            for (int j = i; j < n && curr != null; j++) {
                curr = curr.children[s.charAt(j) - 'a'];
                if (curr != null && curr.isWord && dp[j + 1]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[0];
    }
    
    private TrieNode buildTrie(List<String> wordDict) {
        TrieNode root = new TrieNode();
        
        for (String word : wordDict) {
            TrieNode curr = root;
            char[] ww = word.toCharArray();
            for (int i = 0; i < ww.length; i++) {
                if (curr.children[ww[i] - 'a'] == null) {
                    curr.children[ww[i] - 'a'] = new TrieNode();
                }
                curr = curr.children[ww[i] - 'a'];
            }
            
            curr.isWord = true;
        }
        
        return root;
    }
}