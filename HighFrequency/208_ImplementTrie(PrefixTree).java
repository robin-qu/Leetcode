// Trie O(len)time O(len)space
class Trie {
    private class TrieNode {
        public TrieNode[] children;
        public boolean isWord;
        
        public TrieNode() {
            this.children = new TrieNode[26];
            this.isWord = false;
        }
    }
    
    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
        }
        
        curr.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode tail = goDown(word);
        
        if (tail == null || tail.isWord == false) {
            return false;
        }
        
        return true;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return goDown(prefix) == null ? false : true;
    }
    
    private TrieNode goDown(String query) {
        TrieNode curr = root;
        for (char c : query.toCharArray()) {
            if (curr.children[c - 'a'] == null) {
                return null;
            }
            curr = curr.children[c - 'a'];
        }
        
        return curr;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */