class Solution {
    private static final int[] dx = new int[] {1, 0, 0, -1};
    private static final int[] dy = new int[] {0, 1, -1, 0};
                                        
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        Map<Integer, Integer> memo = new HashMap<>();
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dfs(i, j, matrix, memo));
            }
        }
        
        return res;
    }
                                        
    private int dfs(int x, int y, int[][] matrix, Map<Integer, Integer> memo) {
        int key = x * matrix[0].length + y;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
        int res = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (inBound(nx, ny, matrix) && matrix[nx][ny] > matrix[x][y]) {
                res = Math.max(res, 1 + dfs(nx, ny, matrix, memo));
            }
        }
        
        memo.put(key, res);
        return res;
    }
                                        
    private boolean inBound(int x, int y, int[][] matrix) {
    return x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length;
    }
}