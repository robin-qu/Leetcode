// DFS
class Solution {
    private static final int[] dx = new int[]{0, 1, -1, 0};
    private static final int[] dy = new int[]{1, 0, 0, -1};
    
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 ||
            board[0] == null || board[0].length == 0 || word == null) {
            return false;
        }
        
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(i, j, board, word, 0, visited)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean dfs(int x, int y, char[][] board, String word, int idx, boolean[][] visited) {
        if (idx == word.length() - 1 && board[x][y] == word.charAt(idx)) {
            return true;
        }
        
        if (board[x][y] != word.charAt(idx)) {
            return false;
        }
        
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (inBound(nx, ny, board) && !visited[nx][ny]) {
                if (dfs(nx, ny, board, word, idx + 1, visited)) {
                    return true;
                }
            }
        }
        visited[x][y] = false;
        
        return false;
    }
    
    private boolean inBound(int i, int j, char[][] board) {
        int m = board.length;
        int n = board[0].length;
        return i >= 0 && i < m && j >= 0 && j < n;
    }
} 