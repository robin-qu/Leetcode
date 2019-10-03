// // trie + dfs O(n(2^len))time O(maxLen)space
// class Solution {
//     class TrieNode {
//         public TrieNode[] children;
//         public boolean isWord;
        
//         public TrieNode() {
//             this.children = new TrieNode[26];
//             this.isWord = false;
//         }
//     }
    
//     public List<String> findAllConcatenatedWordsInADict(String[] words) {
//         if (words == null || words.length == 0) {
//             return new ArrayList<>();
//         }
        
//         TrieNode root = buildTrie(words);
//         List<String> res = new ArrayList<>();
        
//         for (String word : words) {
//             if (word.length() == 0) {
//                 continue;
//             }
            
//             if (dfs(0, word, root, 0)) {
//                 res.add(word);
//             }
//         }
        
//         return res;
//     }
    
//     private boolean dfs(int idx, String word, TrieNode root, int count) {
//         TrieNode curr = root;
        
//         for (int i = idx; i < word.length(); i++) {
//             char c = word.charAt(i);
            
//             if (curr.children[c - 'a'] == null) {
//                 return false;
//             }
            
//             curr = curr.children[c - 'a'];
//             if (curr.isWord) {
//                 if (i == word.length() - 1) {
//                     return count > 0;
//                 }
                
//                 if (dfs(i + 1, word, root, count + 1)) {
//                     return true;
//                 }
//             }
//         }
        
//         return false;
//     }

//     private TrieNode buildTrie(String[] words) {
//         TrieNode root = new TrieNode();
        
//         for (String word : words) {
//             if (word.length() == 0) {
//                 continue;
//             }
            
//             TrieNode curr = root;
//             for (char c : word.toCharArray()) {
//                 if (curr.children[c - 'a'] == null) {
//                     curr.children[c - 'a'] = new TrieNode();
//                 }
                
//                 curr = curr.children[c - 'a'];
//             }
            
//             curr.isWord = true;
//         }
        
//         return root;
//     }
// }



// dp + substring O(n(len^2))time O(n)space
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        if (words == null || words.length == 0) {
            return new ArrayList<>();
        }
        
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.length() - b.length();
            }
        });
        
                    
        Set<String> seen = new HashSet<>();
        List<String> res = new ArrayList<>();
        
        for (String word : words) {   
            if (word.length() == 0) {
                continue;
            }
            
            if (dfs(word, seen)) {
                res.add(word);
            }
            
            seen.add(word);
        }
        
        return res;
    }
    
    private boolean dfs(String word, Set<String> seen) {
        int n = word.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && seen.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[n];
    }
}