class Trie {
    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        this.root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (curr.sons[c - 'a'] == null) {
                curr.sons[c - 'a'] = new TrieNode();
            }
            curr = curr.sons[c - 'a'];
        }
        curr.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (curr.sons[c - 'a'] == null) {
                return false;
            }
            curr = curr.sons[c - 'a'];
        }
        return curr.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (char c : prefix.toCharArray()) {
            if (curr.sons[c - 'a'] == null) {
                return false;
            }
            curr = curr.sons[c - 'a'];
        }
        return true;
    }

    class TrieNode {
        TrieNode[] sons;
        boolean isWord;

        public TrieNode() {
            this.sons = new TrieNode[26];
            this.isWord = false;
        }
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */