// bfs O(n^2)time O(n^2)space
class Solution {
    public int snakesAndLadders(int[][] board) {
        if (board == null || board[0] == null) {
            return 0;
        }
        
        int n = board.length;
        
        Queue<Integer> queue = new LinkedList<>();  // 1-based
        Set<Integer> visited = new HashSet<>();
        queue.offer(1);
        visited.add(1);
        int count = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();  // 1-based
                
                if (curr == n * n) {
                    return count;
                }
                
                for (int j = 1; j <= 6 && curr + j <= n * n; j++) {
                    int next = curr + j;
                    int val = getVal(next - 1, board);
                    if (val > 0) {
                        next = val;
                    }
                    
                    if (!visited.contains(next)) {
                        queue.offer(next);
                        visited.add(next);
                    }
                }
            }
            
            count++;
        }
        
        return -1;
    }
    
    // i is 0-based
    private int getVal(int i, int[][] board) {
        int n = board.length;
        int x = n - i / n - 1;
        int y = (n - 1 - x) % 2 == 0 ? i % n : n - 1 - i % n;
        
        return board[x][y];
    }
}