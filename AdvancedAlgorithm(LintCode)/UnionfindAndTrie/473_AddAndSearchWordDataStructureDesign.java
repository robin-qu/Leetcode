public class WordDictionary {
    /*
     * @param word: Adds a word into the data structure.
     * @return: nothing
     */
    class TrieNode {
        public TrieNode[] sons;
        public boolean isWord;
        
        public TrieNode(TrieNode[] sons, boolean isWord) {
            this.sons = sons;
            this.isWord = isWord;
        }
    }
    
    private TrieNode root = new TrieNode(new TrieNode[26], false);
    
    public void addWord(String word) {
        TrieNode curr = root;
        
        for (int i = 0; i < word.length(); i++) {
            if (curr.sons[word.charAt(i) - 'a'] == null) {
                curr.sons[word.charAt(i) - 'a'] = new TrieNode(new TrieNode[26], false);
            }
            curr = curr.sons[word.charAt(i) - 'a'];
        }
        curr.isWord = true;
    }

    /*
     * @param word: A word could contain the dot character '.' to represent any one letter.
     * @return: if the word is in the data structure.
     */
    public boolean search(String word) {
        return find(word, 0, root);
    }
    
    public boolean find(String word, int start, TrieNode curr) {
        if (start == word.length()) {
            return curr.isWord;
        }
        
        if (word.charAt(start) == '.') {
            for (int i = 0; i < 26; i++) {
                if (curr.sons[i] != null) {
                    if (find(word, start + 1, curr.sons[i])) {
                        return true;
                    }
                }
            }
            return false;
        } else {
            if (curr.sons[word.charAt(start) - 'a'] == null) {
                return false;
            }
            
            return find(word, start + 1, curr.sons[word.charAt(start) - 'a']);
        }
    }
} 