public class Solution {
    /*
     * @param words: a set of words without duplicates
     * @return: all word squares
     */
    
    class TrieNode {
        public TrieNode sons[];
        public boolean isWord;
        public String word;
        
        public TrieNode(TrieNode[] sons, boolean isWord, String word) {
            this.sons = sons;
            this.isWord = isWord;
            this.word = word;
        }
    }
    
    private TrieNode root = new TrieNode(new TrieNode[26], false, null);
    
    public List<List<String>> wordSquares(String[] words) {
        if (words == null || words.length == 0) {
            return new ArrayList<>();
        }
        
        buildTrie(words);
        List<List<String>> res = new ArrayList<>();
        List<String> sol = new ArrayList<>();
        
        int len = words[0].length();
        
        dfs(words, len, sol, res);
        
        return res;
    }
    
    private void dfs(String[] words, 
                     int len,
                     List<String> sol, 
                     List<List<String>> res) {
        if (sol.size() == len) {
            res.add(new ArrayList<>(sol));
            return;
        }          
        
        StringBuffer sb = new StringBuffer();
        for (String s : sol) {
            sb.append(s.charAt(sol.size()));
        }
        List<String> candidates = search(sb.toString());
        
        for (int i = 0; i < words.length; i++) {
            if (!candidates.contains(words[i])) {
                continue;
            }
            
            sol.add(words[i]);
            
            dfs(words, len, sol, res);
            
            sol.remove(sol.size() - 1);
        }
    }
    
    private void buildTrie(String[] words) {
        int len = words[0].length();
        for (String word : words) {
            TrieNode curr = root;
            for (int i = 0; i < len; i++) {
                char c = word.charAt(i);
                if (curr.sons[c - 'a'] == null) {
                    curr.sons[c - 'a'] = new TrieNode(new TrieNode[26], false, null);
                }
                curr = curr.sons[c - 'a'];
            }
            curr.isWord = true;
            curr.word = word;
        }
    }
    
    private List<String> search(String target) {
        TrieNode curr = root;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            if (curr.sons[c - 'a'] == null) {
                return new ArrayList<>();
            }
            
            curr = curr.sons[c - 'a'];
        }
        
        getWords(curr, res);
        return res;
    }
    
    private void getWords(TrieNode curr, List<String> list) {
        if (curr == null) {
            return;
        }
        
        if (curr.isWord) {
            list.add(curr.word);
            return;
        }
        
        for (TrieNode son : curr.sons) {
            getWords(son, list);
        }
    }
}