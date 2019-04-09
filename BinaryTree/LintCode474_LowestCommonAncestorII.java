/**Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.
 *
 * The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.
 *
 * The node has an extra attribute parent which point to the father of itself. The root's parent is null.
 */

/**
 * Definition of ParentTreeNode:
 * 
 * class ParentTreeNode {
 *     public ParentTreeNode parent, left, right;
 * }
 */


public class Solution {
    /*
     * @param root: The root of the tree
     * @param A: node in the tree
     * @param B: node in the tree
     * @return: The lowest common ancestor of A and B
     */
    public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root, ParentTreeNode A, ParentTreeNode B) {
        List<ParentTreeNode> pathA = new LinkedList<>();
        List<ParentTreeNode> pathB = new LinkedList<>();
        
        ParentTreeNode currA = A;
        while (currA != null) {
            pathA.add(0, currA);
            currA = currA.parent;
        }
        
        ParentTreeNode currB = B;
        while (currB != null) {
            pathB.add(0, currB);
            currB = currB.parent;
        }
        
        ParentTreeNode res = root;
        for (int i = 0; i < Math.min(pathA.size(), pathB.size()); i++) {
            if (pathA.get(i) == pathB.get(i)) {
                res = pathA.get(i);
            } else {
                break;
            }
        }
        
        return res;
    }
}