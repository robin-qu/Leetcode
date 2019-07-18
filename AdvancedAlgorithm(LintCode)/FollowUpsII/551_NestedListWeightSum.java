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
 
 
// // DFS recursive
// public class Solution {
//     public int depthSum(List<NestedInteger> nestedList) {
//         if (nestedList == null || nestedList.size() == 0) {
//             return 0;
//         }
        
//         return getSum(nestedList, 1);
//     }
    
//     private int getSum(List<NestedInteger> nestedList, int level) {
//         int sum = 0;
        
//         for (NestedInteger n : nestedList) {
//             if (n.isInteger()) {
//                 sum += n.getInteger() * level;
//             } else {
//                 sum += getSum(n.getList(), level + 1);
//             }
//         }
        
//         return sum;
//     }
// } 
 

// // DFS non-recursive
// public class Solution {
//     public int depthSum(List<NestedInteger> nestedList) {
//         if (nestedList == null || nestedList.size() == 0) {
//             return 0;
//         }
        
//         Stack<NestedInteger> stack = new Stack<>();
//         Stack<Integer> levels = new Stack<>();
//         int res = 0;
        
//         for (int i = nestedList.size() - 1; i >= 0; i--) {
//             stack.push(nestedList.get(i));
//             levels.push(1);
//         }
        
//         while (!stack.isEmpty()) {
//             NestedInteger curr = stack.pop();
//             int level = levels.pop();
            
//             if (curr.isInteger()) {
//                 res += level * curr.getInteger();
//             } else {
//                 List<NestedInteger> list = curr.getList();
//                 for (int i = list.size() - 1; i >= 0; i--) {
//                     stack.push(list.get(i));
//                     levels.push(level + 1);
//                 }
//             }
//         }
        
//         return res;
//     }
// }


 
// BFS non-recursive
public class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }
        
        Queue<NestedInteger> queue = new LinkedList<>();
        int res = 0;
        int level = 0;
        
        for (NestedInteger n : nestedList) {
            queue.offer(n);
        }
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            
            for (int i = 0; i < size; i++) {
                NestedInteger curr = queue.poll();
                
                if (curr.isInteger()) {
                    res += curr.getInteger() * level;
                } else {
                    List<NestedInteger> list = curr.getList();
                    
                    for (NestedInteger n : list) {
                        queue.offer(n);
                    }
                }
            }
        }
        
        return res;
    }
} 
 
