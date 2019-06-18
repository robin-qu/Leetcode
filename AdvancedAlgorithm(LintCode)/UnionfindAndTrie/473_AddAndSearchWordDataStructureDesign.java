
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