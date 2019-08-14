// // Sort O(mnlogmn)time O(mn)space
// class Solution {
//     public int minTotalDistance(int[][] grid) {
//         if (grid == null || grid.length == 0 ||
//             grid[0] == null || grid[0].length == 0) {
//             return 0;
//         }
        
//         int m = grid.length;
//         int n = grid[0].length;
//         List<Integer> row = new ArrayList<>();
//         List<Integer> col = new ArrayList<>();
        
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (grid[i][j] == 1) {
//                     row.add(i);
//                     col.add(j);
//                 }
//             }
//         }
        
//         return getMin(row) + getMin(col);
//     }
    
//     private int getMin(List<Integer> list) {
//         Collections.sort(list);
        
//         int res = 0;
        
//         int left = 0;
//         int right = list.size() - 1;
//         while (left < right) {
//             res += list.get(right) - list.get(left);
//             left++;
//             right--;
//         }
        
//         return res;
//     }
// }


// Two Pointers O(mn)time O(mn)space
class Solution {
    public int minTotalDistance(int[][] grid) {
        if (grid == null || grid.length == 0 ||
            grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int[] row = new int[m];
        int[] col = new int[n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    row[i]++;
                    col[j]++;
                }
            }
        }
        
        return getMin(row) + getMin(col);
    }
    
    private int getMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int leftSum = 0;
        int rightSum = 0;
        int res = 0;
        
        while (left < right) {
            if (leftSum < rightSum) {
                res += leftSum;
                leftSum += nums[++left];
            } else {
                res += rightSum;
                rightSum += nums[--right];
            }
        }
        
        return res;
    }
}