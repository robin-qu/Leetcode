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
     * @param nodes: a array of Directed graph node
     * @return: a connected set of a directed graph
     */
    private Map<DirectedGraphNode, DirectedGraphNode> unionfind;
    
    // weakly connected component in directed graph is the same as
    // connected component in undirected graph
    public List<List<Integer>> connectedSet2(ArrayList<DirectedGraphNode> nodes) {
        if (nodes == null || nodes.size() == 0) {
            return new ArrayList<>();
        }
        
        // initialization
        this.unionfind = new HashMap<>();
        
        for (DirectedGraphNode node : nodes) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                connect(node, neighbor);
            }
        }
        
        Map<DirectedGraphNode, List<Integer>> component = new HashMap<>();
        for (DirectedGraphNode node : nodes) {
            DirectedGraphNode root = find(node);
            if (!component.containsKey(root)) {
                component.put(root, new ArrayList<>());
            }
            component.get(root).add(node.label);
        }
        
        List<List<Integer>> res = new ArrayList<>();
        for (DirectedGraphNode node : component.keySet()) {
            Collections.sort(component.get(node));
            res.add(component.get(node));
        }
        
        return res;
    }
    
    private DirectedGraphNode find(DirectedGraphNode a) {
        DirectedGraphNode root = a;
        while (unionfind.containsKey(root)) {
            root = unionfind.get(root);
        }
        
        DirectedGraphNode i = a;
        while (i != root) {
            DirectedGraphNode parent = unionfind.get(i);
            unionfind.put(i, root);
            i = parent;
        }
        
        return root;
    }
    
    private void connect(DirectedGraphNode a, DirectedGraphNode b) {
        DirectedGraphNode rootA = find(a);
        DirectedGraphNode rootB = find(b);
        if (rootA != rootB) {
            unionfind.put(rootA, rootB);
        }
    }
}