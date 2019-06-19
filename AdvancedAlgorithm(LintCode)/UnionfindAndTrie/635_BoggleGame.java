public class Solution {
    /*
     * @param board: a list of lists of character
     * @param words: a list of string
     * @return: an integer
     */
    class TrieNode {
        public TrieNode[] sons;
        public String word;
        
        public TrieNode(TrieNode[] sons, String word) {
            this.sons = sons;
            this.word = word;
        }
    }
    
    private TrieNode root = new TrieNode(new TrieNode[26], null);
    private int[] dx = new int[]{0, 1, -1, 0};
    private int[] dy = new int[]{1, 0, 0, -1};
    private int max = 0;
    
    private void buildTrie(String[] words) {
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            TrieNode curr = root;
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                if (curr.sons[c - 'a'] == null) {
                    curr.sons[c - 'a'] = new TrieNode(new TrieNode[26], null);
                }
                curr = curr.sons[c - 'a'];
            }
            curr.word = word;
        }
    }
    
    public int boggleGame(char[][] board, String[] words) {
        if (board == null || board.length == 0 ||
            board[0] == null || board[0].length == 0 ||
            words == null || words.length == 0) {
            return 0;
        }
        
        int m = board.length;
        int n = board[0].length;
        
        buildTrie(words);
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i, j, board, root, new ArrayList<>());
            }
        }
        
        return max;
    }
    
    private void dfs(int i, int j, char[][] board, TrieNode curr, List<String> list) {
        char c = board[i][j];
        
        if (c == '*' || curr.sons[c - 'a'] == null) {
            return;
        }
        
        curr = curr.sons[c - 'a'];
        
        board[i][j] = '*';
        
        if (curr.word != null) {
            list.add(curr.word);
            max = Math.max(max, list.size());
            curr = root;
            for (int ii = 0; ii < board.length; ii++){
                for (int jj = 0; jj < board[0].length; jj++){
                    dfs(ii, jj, board, root, list);
                }
            }
            list.remove(list.size() - 1);
            return;
        }
        
        for (int k = 0; k < 4; k++) {
            int newX = i + dx[k];
            int newY = j + dy[k];
            if (inBound(newX, newY, board)) {
                dfs(newX, newY, board, curr, list);
            }
        }
        
        board[i][j] = c;
    }
    
    private boolean inBound(int i, int j, char[][] board) {
        return i >= 0 && j >= 0 && i < board.length && j < board[0].length;
    }
}