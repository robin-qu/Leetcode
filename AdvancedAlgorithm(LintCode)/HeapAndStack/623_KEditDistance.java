class TrieNode {
    public TrieNode[] children;
    public boolean isWord;
    public String word;
    
    public TrieNode() {
        this.children = new TrieNode[26];
        this.isWord = false;
        this.word = null;
    }
    
    public static void insert(TrieNode root, String word) {
        for (int i  = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            
            if (root.children[c - 'a'] == null) {
                root.children[c - 'a'] = new TrieNode();
            }
            
            root = root.children[c - 'a'];
        }
        
        root.isWord = true;
        root.word = word;
    }
}

public class Solution {
    /**
     * @param words: a set of stirngs
     * @param target: a target string
     * @param k: An integer
     * @return: output all the strings that meet the requirements
     */
    public List<String> kDistance(String[] words, String target, int k) {
        if (words == null || words.length == 0 || target == null) {
            return new ArrayList<>();
        }
        
        List<String> res = new ArrayList<>();
        
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode.insert(root, word);
        }
        
        int n = target.length();
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = i;
        }
        
        dfs(root, target, k, res, dp);
        
        return res;
    }
    
    private void dfs(TrieNode node, String target, int k,
                     List<String> res, int[] dp) {
        int n = target.length();
        if (node.isWord && dp[n] <= k) {
            res.add(node.word);
        }
        
        // new status
        int[] nextDp = new int[n + 1];
        
        for (int i = 0; i < 26; i++) {
            if (node.children[i] == null) {
                continue;
            }
            
            nextDp[0] = dp[0] + 1;
            
            for (int j = 0; j < n; j++) {
                if (target.charAt(j) == i + 'a') {
                    nextDp[j + 1] = dp[j];
                } else {
                    nextDp[j + 1] = 1 + Math.min(dp[j], 
                                                // replace
                                            Math.min(nextDp[j],
                                                     // insert
                                                    dp[j + 1]));
                                                    // delete
                }
            }
            
            dfs(node.children[i], target, k, res, nextDp);
        }
    }
}