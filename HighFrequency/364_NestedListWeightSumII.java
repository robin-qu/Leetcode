/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

// // BFS O(n)time O(n)space
// class Solution {
//     public int depthSumInverse(List<NestedInteger> nestedList) {
//         if (nestedList == null || nestedList.size() == 0) {
//             return 0;
//         }
        
//         int depth = 0;
//         Queue<NestedInteger> queue = new LinkedList<>();
//         for (int i = nestedList.size() - 1; i >= 0; i--) {
//             queue.offer(nestedList.get(i));
//         }
        
//         while (!queue.isEmpty()) {
//             depth++;
//             int size = queue.size();
            
//             for (int i = 0; i < size; i++) {
//                 NestedInteger curr = queue.poll();
//                 if (!curr.isInteger()) {
//                     List<NestedInteger> list = curr.getList();
//                     for (int j = 0; j < list.size(); j++) {
//                         queue.offer(list.get(j));
//                     }
//                 }
//             }
//         }
        
//         int sum = 0;
//         int level = 0;
//         for (int i = nestedList.size() - 1; i >= 0; i--) {
//             queue.offer(nestedList.get(i));
//         }
        
//         while (!queue.isEmpty()) {
//             level++;
//             int size = queue.size();
            
//             for (int i = 0; i < size; i++) {
//                 NestedInteger curr = queue.poll();
//                 if (!curr.isInteger()) {
//                     List<NestedInteger> list = curr.getList();
//                     for (int j = 0; j < list.size(); j++) {
//                         queue.offer(list.get(j));
//                     }
//                 } else {
//                     sum += (depth - level + 1) * curr.getInteger();
//                 }
//             }
//         }
        
//         return sum;
//     }
// }


// BFS O(n)time O(n)space
class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }
        
        int sum = 0;
        int localSum = 0;
        while (!nestedList.isEmpty()) {
            List<NestedInteger> newList = new ArrayList<>();
            for (NestedInteger ni : nestedList) {
                if (ni.isInteger()) {
                    localSum += ni.getInteger();
                } else {
                    newList.addAll(ni.getList());
                }
            }
            
            nestedList = newList;
            sum += localSum;
        }
        
        return sum;
    }
}