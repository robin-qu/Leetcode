// class Solution {
//     public int[] findOrder(int numCourses, int[][] prerequisites) {
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
        
//         if (order.size() != graph.size()) {
//             return new int[0];
//         }
        
//         for (int i = 0; i < numCourses; i++) {
//             if (!order.contains(i)) {
//                 order.add(i);
//             }
//         }        
        
//         int[] res = new int[numCourses];
//         for (int i = 0; i < numCourses; i++) {
//             res[i] = order.get(i);
//         }
//         return res;
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

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        int[] order = new int[numCourses];
        
        for (int[] pair : prerequisites) {
            indegree[pair[0]]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        
        int index = 0;
        while (!queue.isEmpty()) {
            int curr = queue.remove();
            order[index++] = curr;
            
            for (int[] pair : prerequisites) {
                if (pair[1] == curr) {
                    indegree[pair[0]]--;
                    if (indegree[pair[0]] == 0) {
                        queue.add(pair[0]);
                    }
                }
            }
        }
        
        return index == numCourses ? order : new int[0];
    }
}