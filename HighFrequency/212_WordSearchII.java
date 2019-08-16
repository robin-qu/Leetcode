class Solution {
    private static final int[] dx = new int[]{1, 0, 0, -1};
    private static final int[] dy = new int[]{0, 1, -1, 0};
    
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
    
    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0 ||
            board[0] == null || board[0].length == 0 ||
            words == null || words.length == 0) {
            return new ArrayList<>();
        } 
        
        TrieNode root = buildTree(words);
        List<String> res = new ArrayList<>();
        
        int m = board.length;
        int n = board[0].length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = board[i][j];
                board[i][j] = '#';
                dfs(i, j, board, root.children[c - 'a'], res);
                board[i][j] = c;
            }
        }
        
        return res;
    }
    
    private void dfs(int x, int y, char[][] board, TrieNode node, List<String> res) {
        if (node == null) {
            return;
        }
        
        if (node.isWord) {
            res.add(node.word);
            node.isWord = false; //setting the node isNotWord when the word has been added to the result list
        }
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (inBound(nx, ny, board) && board[nx][ny] != '#' && node.children[board[nx][ny] - 'a'] != null) {
                char c = board[nx][ny];
                board[nx][ny] = '#';
                dfs(nx, ny, board, node.children[c - 'a'], res);
                board[nx][ny] = c;
            }
        }
    }
    
    private boolean inBound(int x, int y, char[][] board) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }
    
    private TrieNode buildTree(String[] words) {
        TrieNode root = new TrieNode();
        
        for (String word : words) {
            TrieNode curr = root;
            int len = word.length();
            for (int i = 0; i < len; i++) {
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