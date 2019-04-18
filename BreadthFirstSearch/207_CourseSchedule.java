// // Adjacency list
// class Solution {
//     public boolean canFinish(int numCourses, int[][] prerequisites) {
//         List<Integer> order = new ArrayList<>();        
//         Map<Integer, Set<Integer>> graph = buildGraph(prerequisites);
//         Map<Integer, Integer> indegree = getIndegree(graph);
        
//         Queue<Integer> queue = new LinkedList<>();
//         for (int course : indegree.keySet()) {
//             if (indegree.get(course) == 0) {
//                 queue.add(course);
//             }
//         }
        
//         while (!queue.isEmpty()) {
//             int curr = queue.remove();
//             order.add(curr);
            
//             for (int neighbor : graph.get(curr)) {
//                 indegree.put(neighbor, indegree.get(neighbor) - 1);
//                 if (indegree.get(neighbor) == 0) {
//                     queue.add(neighbor);
//                 }
//             }
//         }
        
//         return order.size() == graph.size();
//     }
    
//     private Map<Integer, Set<Integer>> buildGraph(int[][] prerequisites) {
//         Map<Integer, Set<Integer>> res = new HashMap<>();
        
//         for (int[] pair : prerequisites) {
//             int prev = pair[1];
//             int next = pair[0];
//             if (!res.containsKey(prev)) {
//                 Set<Integer> neighbors = new HashSet<>();
//                 res.put(prev, neighbors);
//             }
//             if (!res.containsKey(next)) {
//                 Set<Integer> neighbors = new HashSet<>();
//                 res.put(next, neighbors);
//             }
//             res.get(prev).add(next);
//         }
        
//         return res;
//     }
    
//     private Map<Integer, Integer> getIndegree(Map<Integer, Set<Integer>> graph) {
//         Map<Integer, Integer> res = new HashMap<>();
        
//         for (int node : graph.keySet()) {
//             if (!res.containsKey(node)) {
//                 res.put(node, 0);
//             }
            
//             for (int neighbor : graph.get(node)) {
//                 if (!res.containsKey(neighbor)) {
//                     res.put(neighbor, 1);
//                 } else {
//                     res.put(neighbor, res.get(neighbor) + 1);
//                 }
//             }
//         }
        
//         return res;
//     }
// }


// // Adjacency matrix
// class Solution {
//     public boolean canFinish(int numCourses, int[][] prerequisites) {
//         int[][] graph = new int[numCourses][numCourses];
//         int[] indegree = new int[numCourses];
        
//         for (int[] pair : prerequisites) {
//             int prev = pair[1];
//             int next = pair[0];
//             if (graph[prev][next] == 0) {
//                 indegree[next]++;
//             }
//             graph[prev][next] = 1;
//         }
        
//         Queue<Integer> queue = new LinkedList<>();
        
//         for (int i = 0; i < numCourses; i++) {
//             if (indegree[i] == 0) {
//                 queue.add(i);
//             }
//         }
        
//         int count = 0;
        
//         while (!queue.isEmpty()) {
//             int curr = queue.remove();
//             count++;
            
//             for (int i = 0; i < numCourses; i++) {
//                 if (graph[curr][i] != 0) {
//                     indegree[i]--;
//                     if (indegree[i] == 0) {
//                         queue.add(i);
//                     }
//                 }
//             }
//         }
        
//         return count == numCourses;
//     }
// }


class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        
        for (int[] pair : prerequisites) {
            indegree[pair[0]]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        
        int count = 0;
        
        while (!queue.isEmpty()) {
            int curr = queue.remove();
            count++;
            
            for (int[] pair : prerequisites) {
                if (pair[1] == curr) {
                    indegree[pair[0]]--;
                    if (indegree[pair[0]] == 0) {
                        queue.add(pair[0]);
                    }
                }
            }
        }
        
        return count == numCourses;
    }
}