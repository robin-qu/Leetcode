/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */

public class Solution {
    /*
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> res = new ArrayList<>();
        
        if (graph == null) {
            return res;
        }
        
        Map<DirectedGraphNode, Integer> indegree = getIndegree(graph);
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        
        for (DirectedGraphNode node : graph) {
            if (indegree.get(node) == 0) {
                queue.add(node);
            }
        }
        
        while (!queue.isEmpty()) {
            DirectedGraphNode curr = queue.remove();
            res.add(curr);
            
            for (DirectedGraphNode neighbor : curr.neighbors) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }
        
        return res;
    }
    
    private Map<DirectedGraphNode, Integer> getIndegree(List<DirectedGraphNode> graph) {
        Map<DirectedGraphNode, Integer> indegree = new HashMap<>();
        
        for (DirectedGraphNode node : graph) {
            indegree.putIfAbsent(node, 0);
            
            for (DirectedGraphNode neighbor : node.neighbors) {
                indegree.putIfAbsent(neighbor, 0);
                indegree.put(neighbor, indegree.get(neighbor) + 1);
            }
        }
        
        return indegree;
    }
}