// // O(81)time O(27)space initial version
// class Solution {
//     public boolean isValidSudoku(char[][] board) {
//         Map<Integer, Set<Character>> row = new HashMap<>();
//         Map<Integer, Set<Character>> col = new HashMap<>();
//         Map<Integer, Set<Character>> block = new HashMap<>();
        
//         for (int i = 0; i < 9; i++) {
//             row.put(i, new HashSet<>());
//             col.put(i, new HashSet<>());
//             block.put(i, new HashSet<>());
//         }
        
//         for (int i = 0; i < 9; i++) {
//             for (int j = 0; j < 9; j++) {
//                 char val = board[i][j];
//                 if (val == '.') {
//                     continue;
//                 }
                
//                 if (row.get(i).contains(val)) {
//                     return false;
//                 } else {
//                     row.get(i).add(val);
//                 }
                
//                 if (col.get(j).contains(val)) {
//                     return false;
//                 } else {
//                     col.get(j).add(val);
//                 }
                
//                 if (block.get(i / 3 * 3 + j / 3).contains(val)) {
//                     return false;
//                 } else {
//                     block.get(i / 3 * 3 + j / 3).add(val);
//                 }
//             }
//         }
        
//         return true;
//     }
// }

// O(81)time O(27)space
class Solution {
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Set<Character>> row = new HashMap<>();
        Map<Integer, Set<Character>> col = new HashMap<>();
        Map<Integer, Set<Character>> block = new HashMap<>();
        
        for (int i = 0; i < 9; i++) {
            row.put(i, new HashSet<>());
            col.put(i, new HashSet<>());
            block.put(i, new HashSet<>());
        }
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char val = board[i][j];
                if (val == '.') {
                    continue;
                }
                
                if (!row.get(i).add(val) ||
                    !col.get(j).add(val) ||
                    !block.get(i / 3 * 3 + j / 3).add(val)) {
                    return false;
                }
            }
        }
        
        return true;
    }
}