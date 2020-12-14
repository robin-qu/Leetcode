class Solution {
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return false;
        }

        Set<Character>[] rows = new HashSet[9];
        Set<Character>[] cols = new HashSet[9];
        Set<Character>[] blocks = new HashSet[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            blocks[i] = new HashSet<>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                if (!rows[i].add(board[i][j])) {
                    return false;
                }
                if (!cols[j].add(board[i][j])) {
                    return false;
                }
                if (!blocks[i / 3 * 3 + j / 3].add(board[i][j])) {
                    return false;
                }
            }
        }

        return true;
    }
}