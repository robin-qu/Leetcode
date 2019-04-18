// public class Solution {
//     /**
//      * @param org: a permutation of the integers from 1 to n
//      * @param seqs: a list of sequences
//      * @return: true if it can be reconstructed only one or false
//      */
//     public boolean sequenceReconstruction(int[] org, int[][] seqs) {
//         if (seqs == null || seqs.length == 0) {
//             return false;
//         }
        
//         Map<Integer, Integer> indegree = getIndegree(seqs);
//         Map<Integer, List<Integer>> graph = buildGraph(seqs);
                
//         Queue<Integer> queue = new LinkedList<>();
//         List<Integer> order = new ArrayList<>();
        
//         for (int key : indegree.keySet()) {
//             if (indegree.get(key) == 0) {
//                 queue.add(key);
//             } 
//         }
        
//         if (queue.size() > 1) {
//             return false;
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
            
//             if (queue.size() > 1) {
//                 return false;
//             }
//         }
        
//         if (order.size() != org.length) {
//             return false;
//         }
        
//         for (int i = 0; i < org.length; i++) {
//             if (org[i] != order.get(i)) {
//                 return false;
//             }
//         }
        
//         return true;
//     }
    
    
//     private Map<Integer, Integer> getIndegree(int[][] seqs) {
//         Map<Integer, Integer> indegree = new HashMap<>();
        
//         for (int[] seq : seqs) {
//             if (seq.length == 0) {
//                 continue;
//             }
//             if (!indegree.containsKey(seq[0])) {
//                 indegree.put(seq[0], 0);
//             }
//             for (int i = 1; i < seq.length; i++) {
//                 if (!indegree.containsKey(seq[i])) {
//                     indegree.put(seq[i], 0);
//                 }
//                 indegree.put(seq[i], indegree.get(seq[i]) + 1);
//             }
//         }
//         return indegree;
//     }
    
//     private Map<Integer, List<Integer>> buildGraph(int[][] seqs) {
//         Map<Integer, List<Integer>> graph = new HashMap<>();
        
//         for (int[] seq : seqs) {
//             if (seq.length == 0) {
//                 continue;
//             }
//             for (int i = 0; i < seq.length - 1; i++) {
//                 if (!graph.containsKey(seq[i])) {
//                     graph.put(seq[i], new ArrayList<>());
//                 }
//                 graph.get(seq[i]).add(seq[i + 1]);
//             }
//             if (!graph.containsKey(seq[seq.length - 1])) {
//                 graph.put(seq[seq.length - 1], new ArrayList<>());
//             }
//         }
//         return graph;
//     }
// }

public class Solution {
    /**
     * @param org: a permutation of the integers from 1 to n
     * @param seqs: a list of sequences
     * @return: true if it can be reconstructed only one or false
     */
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        if (seqs == null || seqs.length == 0) {
            return false;
        }
        
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        for (int[] seq : seqs) {
            for (int i = 0; i < seq.length; i++) {
                graph.putIfAbsent(seq[i], new ArrayList<Integer>());
                indegree.putIfAbsent(seq[i], 0);
                if (i > 0) {
                    graph.get(seq[i - 1]).add(seq[i]);
                    indegree.put(seq[i], indegree.get(seq[i]) + 1);
                }
            }
        }
        
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> order = new ArrayList<>();
        
        for (int key : indegree.keySet()) {
            if (indegree.get(key) == 0) {
                queue.add(key);
            } 
        }
        
        if (queue.size() > 1) {
            return false;
        }
        
        while (!queue.isEmpty()) {
            int curr = queue.remove();
            order.add(curr);
            
            for (int neighbor : graph.get(curr)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
            
            if (queue.size() > 1) {
                return false;
            }
        }
        
        if (order.size() != org.length) {
            return false;
        }
        
        for (int i = 0; i < org.length; i++) {
            if (org[i] != order.get(i)) {
                return false;
            }
        }
        
        return true;
    }
}