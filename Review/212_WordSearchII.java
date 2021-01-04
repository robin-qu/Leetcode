class Solution {
    private static final int[] dx = new int[] {0, 1, -1, 0};
    private static final int[] dy = new int[] {1, 0, 0, -1};
    private int m;
    private int n;

    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0 || words == null || words.length == 0) {
            return new ArrayList<>();
        } 

        TrieNode root = buildTrie(words);
        m = board.length;
        n = board[0].length;
        List<String> res = new ArrayList<>();

        for (int i = 0 ; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i, j, board, root, new boolean[m][n], res);
            }
        }

        return res;
    }

    private void dfs(int x, int y, char[][] board, TrieNode root, boolean[][] visited, List<String> res) {
        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || root.sons[board[x][y] - 'a'] == null) {
            return;
        }
        char c = board[x][y];
        visited[x][y] = true;
        if (root.sons[c - 'a'].word != null) {
            res.add(root.sons[c - 'a'].word);
            root.sons[c - 'a'].word = null;
        }

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            dfs(nx, ny, board, root.sons[c - 'a'], visited, res);
        }
        visited[x][y] = false;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                if (curr.sons[c - 'a'] == null) {
                    curr.sons[c - 'a'] = new TrieNode();
                }
                curr = curr.sons[c - 'a'];
            }
            curr.word = word;
        }
        return root;
    }

    class TrieNode {
        TrieNode[] sons = new TrieNode[26];
        String word = null;
    }
}