// // O(nN)time O(nN)space  (TLE)
// class Solution {
//     public int[] prisonAfterNDays(int[] cells, int N) {
//         if (cells == null || cells.length == 0) {
//             return cells;
//         }
        
//         int n = cells.length;
        
//         for (int i = 0; i < N; i++) {
//             change(cells);
//         }
        
//         return cells;
//     }
    
//     private void change(int[] cells) {
//         int[] res = new int[cells.length];
//         for (int i = 1; i < cells.length - 1; i++) {
//             res[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
//         }
        
//         for (int i = 0; i < cells.length; i++) {
//             cells[i] = res[i];
//         }
//     }
// }



// O(nN)time O(nN)space find cucle
class Solution {
    public int[] prisonAfterNDays(int[] cells, int N) {
        if (cells == null || cells.length == 0) {
            return cells;
        }
        
        Set<String> seen = new HashSet<>();
        
        int count = 0;
        boolean hasCycle = false;
        for (int i = 0; i < N; i++) {
            int[] nextCells = next(cells);
            if (seen.contains(Arrays.toString(nextCells))) {
                hasCycle = true;
                break;
            }
            
            seen.add(Arrays.toString(nextCells));
            count++;
            cells = nextCells;
        }
        
        if (!hasCycle) {
            return cells;
        }
        
        for (int i = 0; i < N % count; i++) {
            cells = next(cells);
        }
        
        return cells;
    }
    
    private int[] next(int[] cells) {
        int[] res = new int[cells.length];
        for (int i = 1; i < cells.length - 1; i++) {
            res[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
        }
        
        return res;
    }
}