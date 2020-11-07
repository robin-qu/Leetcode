class Solution {
    private static final int[] dx = {0, 1, -1, 0};
    private static final int[] dy = {1, 0, 0, -1};
                                        
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return false;
        }
        
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(i, j, 0, word, board, visited)) {
                    return true;
                }
            }
        }
        
        return false;
    }
                                        
    private boolean dfs(int x, int y, int idx, String word, char[][] board, boolean[][] visited) {
        if (word.charAt(idx) != board[x][y]) {
            return false;
        }
        
        if (idx == word.length() - 1) {
            return true;
        }
        
        boolean res = false;
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!inBound(nx, ny, board) || visited[nx][ny]) {
                continue;
            }
            if (dfs(nx, ny, idx + 1, word, board, visited)) {
                res = true;
                break;
            }
        }
        visited[x][y] = false;
        return res;
    }
                                        
    private boolean inBound(int x, int y, char[][] board) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }
}