/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer,
 *     // rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds,
 *     // if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds,
 *     // if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
 
// // Non-recursive
// public class Solution {

//     // @param nestedList a list of NestedInteger
//     // @return a list of integer
//     public List<Integer> flatten(List<NestedInteger> nestedList) {
//         if (nestedList == null) {
//             return new ArrayList<>();
//         }
        
//         List<Integer> res = new ArrayList<>();
//         Stack<NestedInteger> stack = new Stack<>();
//         for (int i = nestedList.size() - 1; i >= 0; i--) {
//             stack.push(nestedList.get(i));
//         }
        
//         while (!stack.isEmpty()) {
//             NestedInteger curr = stack.pop();
//             if (curr.isInteger()) {
//                 res.add(curr.getInteger());
//             } else {
//                 List<NestedInteger> list = curr.getList();
//                 for (int i = list.size() - 1; i >= 0; i--) {
//                     stack.push(list.get(i));
//                 }
//             }
//         }
        
//         return res;
//     }
// }


// Recursive
public class Solution {
    // @param nestedList a list of NestedInteger
    // @return a list of integer
    public List<Integer> flatten(List<NestedInteger> nestedList) {
        if (nestedList == null) {
            return new ArrayList<>();
        }
        
        List<Integer> res = new ArrayList<>();
        flat(res, nestedList);
        return res;
    }
    
    private void flat(List<Integer> res, List<NestedInteger> nestedList) {
        for (int i = 0; i < nestedList.size(); i++) {
            NestedInteger curr = nestedList.get(i);
            if (curr.isInteger()) {
                res.add(curr.getInteger());
            } else {
                flat(res, curr.getList());
            }
        }
    }
}