public class Trie {
    class TrieNode {
        public TrieNode[] sons;
        public boolean isWord;
        public String word;
        
        public TrieNode(TrieNode[] sons, boolean isWord, String word) {
            this.sons = sons;
            this.isWord = isWord;
            this.word = word;
        }
    }
    
    private TrieNode root;
    
    public Trie() {
        this.root = new TrieNode(new TrieNode[26], false, null);
    }

    /*
     * @param word: a word
     * @return: nothing
     */
    public void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            if (curr.sons[word.charAt(i) - 'a'] == null) {
                curr.sons[word.charAt(i) - 'a'] = new TrieNode(new TrieNode[26], false, null);
            }
            curr = curr.sons[word.charAt(i) - 'a'];
        }
        curr.isWord = true;
        curr.word = word;
    }

    /*
     * @param word: A string
     * @return: if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode curr = root;
        
        for (int i = 0; i < word.length(); i++) {
            if (curr.sons[word.charAt(i) - 'a'] == null) {
                return false;
            }
            curr = curr.sons[word.charAt(i) - 'a'];
        }
        
        return curr.isWord;
    }

    /*
     * @param prefix: A string
     * @return: if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        
        for (int i = 0; i < prefix.length(); i++) {
            if (curr.sons[prefix.charAt(i) - 'a'] == null) {
                return false;
            }
            curr = curr.sons[prefix.charAt(i) - 'a'];
        }
        
        return true;
    }
}