public class Solution {
    /**
     * @param words: a set of stirngs
     * @param target: a target string
     * @param k: An integer
     * @return: output all the strings that meet the requirements
     */
    
    class TrieNode {
        public TrieNode[] children;
        public boolean isWord;
        public String word;
        
        public TrieNode() {
            this.children = new TrieNode[26];
            this.isWord = false;
            this.word = null;
        }
    }
    
    public List<String> kDistance(String[] words, String target, int k) {
        if (words == null || words.length == 0 || target == null) {
            return new ArrayList<>();
        }
        
        TrieNode root = buildTrie(words);
        
        int n = target.length();
        int[] dp = new int[n + 1];  // edit distance of empty string and target
        for (int i = 0; i <= n; i++) {
            dp[i] = i;
        }
        List<String> res = new ArrayList<>();
        
        dfs(root, target, k, res, dp);
        
        return res;
    }
    
    private void dfs(TrieNode root, String target, int k, List<String> res, int[] dp) {
        int n = target.length();
        if (dp[n] <= k && root.isWord) {
            res.add(root.word);
        }
        
        int[] newDP = new int[n + 1];
        newDP[0] = dp[0] + 1;
        
        for (int i = 0; i < 26; i++) {
            if (root.children[i] == null) {
                continue;
            }
            
            for (int j = 1; j <= n; j++) {
                if (i == target.charAt(j - 1) - 'a') {
                    newDP[j] = dp[j - 1];
                } else {
                    newDP[j] = 1 + Math.min(Math.min(newDP[j - 1], dp[j - 1]), dp[j]);
                    //  add   replace   delete
                }
            }
            
            dfs(root.children[i], target, k, res, newDP);
        }
    }
    
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        
        for (String word : words) {
            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (curr.children[c - 'a'] == null) {
                    curr.children[c - 'a'] = new TrieNode();
                }
                curr = curr.children[c - 'a'];
            }
            curr.isWord = true;
            curr.word = word;
        }
        
        return root;
    }
}