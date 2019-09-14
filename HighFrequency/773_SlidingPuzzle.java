// bfs O(6*6!)time(indexOf(6) * 6!permutation vertices)
//     O(6*6!)space(new String(6) * 6!permutation vertices)
class Solution {
    public int slidingPuzzle(int[][] board) {
        if (board == null || board.length == 0 ||
            board[0] == null || board[0].length == 0) {
            return 0;
        }
        
        String end = "123450";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j]);
            }
        }
        String start = sb.toString();
        if (start.equals(end)) {
            return 0;
        }
        
        int[][] dirs = new int[][]{{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};  // the indices that the curr index can be swapped with
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);
        int dist = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            dist++;
            
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                int j = curr.indexOf('0');
                
                for (int k : dirs[j]) {
                    String neighbor = getNeighbor(j, k, curr);
                    if (neighbor.equals(end)) {
                        return dist;
                    } else if (!visited.contains(neighbor)) {
                        queue.offer(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
        }
        
        return -1;
    }
    
    private String getNeighbor(int i, int j, String s) {
        char[] ss = s.toCharArray();
        char temp = ss[i];
        ss[i] = ss[j];
        ss[j] = temp;
        
        return String.valueOf(ss);
    }
}