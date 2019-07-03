// class Solution {
//     /**
//      * @param stones: a list of stones' positions in sorted ascending order
//      * @return: true if the frog is able to cross the river or false
//      */
//     public boolean canCross(int[] stones) {
//         if (stones == null || stones.length == 0) {
//             return false;
//         }
        
//         int n = stones.length;
//         Map<Integer, Set<Integer>> map = new HashMap<>();
        
//         for (int i = 0; i < n; i++) {
//             map.put(stones[i], new HashSet<>());
//         }
//         map.get(0).add(0);
        
//         for (int i = 0; i < n; i++) {
//             for (int step : map.get(stones[i])) {
//                 for (int j = step - 1; j <= step + 1; j++) {
//                     if (j == 0) {
//                         continue;  // avoid hashmap concurrent modification
//                     }
//                     if (map.containsKey(stones[i] + j)) {
//                         map.get(stones[i] + j).add(j);
//                     }
//                 }
//             }
//         }
        
//         return !map.get(stones[n - 1]).isEmpty();
//     }
// }

// DFS
class Solution {
    /**
     * @param stones: a list of stones' positions in sorted ascending order
     * @return: true if the frog is able to cross the river or false
     */
    public boolean canCross(int[] stones) {
        if (stones == null || stones.length == 0) {
            return false;
        }
        
        if (stones.length == 1) {
            return true;
        }
        
        if (stones[1] != 1) {
            return false;
        }
        
        int n = stones.length;
        Set<Integer> stoneSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (i > 3 && stones[i] > stones[i - 1] * 2) {
                return false;
            }
            stoneSet.add(stones[i]);
        }
        
        return dfs(stones[n - 1], stoneSet, 1, 1);
    }
    
    private boolean dfs(int last, Set<Integer> stoneSet, int pos, int step) {
        if (pos == last) {
            return true;
        }

/////////////////////////////////////////////////////////////////////////
//////////////// WHY WILL THIS LOOP CAUSE TLE !!!!!!!!! /////////////////       
/////////////////////////////////////////////////////////////////////////
//         for (int i = step - 1; i <= step + 1; i++) {
//             if (i <= 0) {
//                 continue;
//             }
//             if (stoneSet.contains(pos + i)) {
//                 if (dfs(last, stoneSet, pos + i, i)) {
//                     return true;
//                 }
//             }
//         }
/////////////////////////////////////////////////////////////////////////
        
        if (stoneSet.contains(pos + step + 1)) {
            if (dfs(last, stoneSet, pos + step + 1, step + 1)) {
                return true;
            }
        }
        
        if (stoneSet.contains(pos + step)) {
            if (dfs(last, stoneSet, pos + step, step)) {
                return true;
            }
        }
        
        if (step > 1 && stoneSet.contains(pos + step - 1)) {
            if (dfs(last, stoneSet, pos + step - 1, step - 1)) {
                return true;
            }
        }

        return false;
    }
}