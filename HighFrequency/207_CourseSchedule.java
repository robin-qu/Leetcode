// Topological sort O(n)time O(n)space
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) {
            return false;
        }
        
        int[] incoming = new int[numCourses];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }
        
        for (int[] prerequisite : prerequisites) {
            incoming[prerequisite[0]]++;
            graph.get(prerequisite[1]).add(prerequisite[0]);
        }
        
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0 ; i < numCourses; i++) {
            if (incoming[i] == 0) {
                queue.offer(i);
            }
        }
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            count++;
            
            for (int neighbor : graph.get(curr)) {
                incoming[neighbor]--;
                if (incoming[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        return count == numCourses;
    }
}