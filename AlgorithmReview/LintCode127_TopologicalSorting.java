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
        if (graph == null || graph.size() == 0) {
            return res;
        }
        
        Map<DirectedGraphNode, Integer> income = getIncome(graph);
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for (DirectedGraphNode node : graph) {
            if (income.get(node) == 0) {
                queue.offer(node);
            }
        }
        
        while (!queue.isEmpty()) {
            DirectedGraphNode curr = queue.poll();
            res.add(curr);
            
            for (DirectedGraphNode neighbor : curr.neighbors) {
                income.put(neighbor, income.get(neighbor) - 1);
                if (income.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        return res;
    }
    
    private Map<DirectedGraphNode, Integer> getIncome(ArrayList<DirectedGraphNode> graph) {
        Map<DirectedGraphNode, Integer> income = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            income.put(node, 0);
        }
        
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                income.put(neighbor, income.get(neighbor) + 1);
            }
        }
        
        return income;
    }
}