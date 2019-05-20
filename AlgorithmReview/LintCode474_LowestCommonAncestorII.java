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
        if (root == null) {
            return null;
        }
        
        List<ParentTreeNode> pathA = new ArrayList<>();
        ParentTreeNode currA = A;
        while (currA != null) {
            pathA.add(0, currA);
            currA = currA.parent;
        }
        
        List<ParentTreeNode> pathB = new ArrayList<>();
        ParentTreeNode currB = B;
        while (currB != null) {
            pathB.add(0, currB);
            currB = currB.parent;
        }
        
        ParentTreeNode res = pathA.get(0);
        int idx = 0;
        while (idx < Math.min(pathA.size(), pathB.size()) && pathA.get(idx) == pathB.get(idx)) {
            res = pathA.get(idx);
            idx++;
        }
        
        return res;
    }
    
}