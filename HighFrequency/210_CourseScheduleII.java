// bfs O(n)time O(n)space
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) {
            return new int[0];
        }
        
        Map<Integer, Integer> income = new HashMap<>();
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            income.put(i, 0);
            graph.put(i, new HashSet<>());
        }
        
        for (int[] p : prerequisites) {
            income.put(p[0], income.get(p[0]) + 1);
            graph.get(p[1]).add(p[0]);
        }
        
        List<Integer> list = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (income.get(i) == 0) {
                queue.offer(i);
            }
        }
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            list.add(curr);
            
            for (int neighbor : graph.get(curr)) {
                income.put(neighbor, income.get(neighbor) - 1);
                if (income.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        int n = list.size();
        if (n < numCourses) {
            return new int[0];
        }
        
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = list.get(i);
        }
        
        return res;
    }
}