/**
 * Definition for Undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */


public class Solution {
    /*
     * @param nodes: a array of Undirected graph node
     * @return: a connected set of a Undirected graph
     */
    private Map<UndirectedGraphNode, UndirectedGraphNode> unionfind;
    
    public List<List<Integer>> connectedSet(List<UndirectedGraphNode> nodes) {
        if (nodes == null || nodes.size() == 0) {
            return new ArrayList<>();
        }
        
        // initialization
        this.unionfind = new HashMap<>();
        
        for (UndirectedGraphNode node : nodes) {
            for (UndirectedGraphNode neighbor : node.neighbors) {
                connect(node, neighbor);
            }
        }
        
        Map<UndirectedGraphNode, List<Integer>> component = new HashMap<>();
        for (UndirectedGraphNode node : nodes) {
            UndirectedGraphNode root = find(node);
            if (!component.containsKey(root)) {
                component.put(root, new ArrayList<>());
            }
            component.get(root).add(node.label);
        }
        
        List<List<Integer>> res = new ArrayList<>();
        for (UndirectedGraphNode node : component.keySet()) {
            Collections.sort(component.get(node));
            res.add(component.get(node));
        }
        
        return res;
    }
    
    private UndirectedGraphNode find(UndirectedGraphNode a) {
        UndirectedGraphNode root = a;
        while (unionfind.containsKey(root)) {
            root = unionfind.get(root);
        }
        
        UndirectedGraphNode i = a;
        while (i != root) {
            UndirectedGraphNode parent = unionfind.get(i);
            unionfind.put(i, root);
            i = parent;
        }
        
        return root;
    }
    
    private void connect(UndirectedGraphNode a, UndirectedGraphNode b) {
        UndirectedGraphNode rootA = find(a);
        UndirectedGraphNode rootB = find(b);
        if (rootA != rootB) {
            unionfind.put(rootA, rootB);
        }
    }
}