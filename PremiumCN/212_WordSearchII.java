class Solution {
    private static final int[] dx = new int[] {0, 1, -1, 0};
    private static final int[] dy = new int[] {1, 0, 0, -1};
    private int m;
    private int n;
    private TrieNode root;

    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return new ArrayList<>();
        }
        m = board.length;
        n = board[0].length;
        root = buildTrie(words);
        List<String> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean[][] visited = new boolean[m][n];
                dfs(i, j, root.sons[board[i][j] - 'a'], board, visited, res);
            }
        }

        return res;
    }

    private void dfs(int x, int y, TrieNode curr, char[][] board, boolean[][] visited, List<String> res) {
        if (curr == null) {
            return;
        }

        if (curr.word != null) {
            res.add(curr.word);
            curr.word = null;
        }

        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (inBound(nx, ny) && !visited[nx][ny]) {
                dfs(nx, ny, curr.sons[board[nx][ny] - 'a'], board, visited, res);
            }
        }
        visited[x][y] = false;
    }

    private boolean inBound(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
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
        public TrieNode[] sons;
        public String word;
        public TrieNode() {
            this.sons = new TrieNode[26];
            this.word = null;
        }
    }
}