class Solution {
    public void solveSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0] == null || board[0].length != 9) {
            return;
        }

        Set[] rows = new HashSet[9];
        Set[] cols = new HashSet[9];
        Set[] blocks = new HashSet[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            blocks[i] = new HashSet<>();
        }

        for (int i = 0; i < 81; i++) {
            int x = i / 9;
            int y = i % 9;
            if (board[x][y] == '.') {
                continue;
            }
            rows[x].add(board[x][y]);
            cols[y].add(board[x][y]);
            blocks[x / 3 * 3 + y / 3].add(board[x][y]);
        }

        dfs(0, board, rows, cols, blocks);
    }

    private boolean dfs(int idx, char[][] board, Set[] rows, Set[] cols, Set[] blocks) {
        if (idx == 81) {
            return true;
        }

        int x = idx / 9;
        int y = idx % 9;

        if (board[x][y] != '.') {
            return dfs(idx + 1, board, rows, cols, blocks);
        }

        for (char c = '1'; c <= '9'; c++) {
            if (!canPlace(c, x, y, rows, cols, blocks)) {
                continue;
            }
            board[x][y] = c;
            rows[x].add(c);
            cols[y].add(c);
            blocks[x / 3 * 3 + y / 3].add(c);
            if (dfs(idx + 1, board, rows, cols, blocks)) {
                return true;
            } else {
                board[x][y] = '.';
                rows[x].remove(c);
                cols[y].remove(c);
                blocks[x / 3 * 3 + y / 3].remove(c);
            }
        }

        return false;
    }

    private boolean canPlace(char c, int x, int y, Set[] rows, Set[] cols, Set[] blocks) {
        return !rows[x].contains(c) && !cols[y].contains(c) && !blocks[x / 3 * 3 + y / 3].contains(c);
    }
}