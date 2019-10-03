// bfs O(n)time O(n)space
class Solution {
    public boolean isBipartite(int[][] graph) {
        if (graph == null || graph.length == 0) {
            return false;
        }
        
        int n = graph.length;
        int[] status = new int[n];
        
        for (int i = 0; i < n; i++) {
            if (status[i] != 0) {
                continue;
            }
            Queue<Integer> queue = new LinkedList<>();            
            queue.offer(i);
            status[i] = 1;        
            
            while (!queue.isEmpty()) {
                int curr = queue.poll();

                for (int neighbor : graph[curr]) {
                    if (status[neighbor] == 0) {
                        queue.offer(neighbor);
                        status[neighbor] = -1 * status[curr];
                    } else if (status[neighbor] == status[curr]) {
                        return false;
                    }
                }
            }
        }
            
        return true;
    }
}