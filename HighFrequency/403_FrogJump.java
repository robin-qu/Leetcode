// // dp O(n^2)time O(n^2)space  backward
// class Solution {
//     public boolean canCross(int[] stones) {
//         if (stones == null || stones.length == 0) {
//             return false;
//         }
        
//         int n = stones.length;
//         Set<Integer>[] dp = new Set[n];  // dp[i] represents the last jumps on the stone, empty if cannot jump on
//         for (int i = 0; i < n; i++) {
//             dp[i] = new HashSet<>();
//         }
        
//         if (stones[1] != 1) {
//             return false;
//         }
//         dp[1].add(1);
        
//         for (int i = 2; i < n; i++) {
//             for (int j = i - 1; j > 0; j--) {
//                 int jump = stones[i] - stones[j];
                
//                 for (int k = -1; k <= 1; k++) {
//                     if (dp[j].contains(jump + k)) {
//                         dp[i].add(jump);
//                     }
//                 }
//             }
//         }
        
//         return dp[n - 1].size() != 0;
//     }
// }


// dp O(n^2)time O(n^2)space  forward
class Solution {
    public boolean canCross(int[] stones) {
        if (stones == null || stones.length == 0) {
            return false;
        }
        
        int n = stones.length;
        Map<Integer, Set<Integer>> dp = new HashMap();
        for (int i = 0; i < n; i++) {
            dp.put(stones[i], new HashSet<>());
        }
        
        dp.get(0).add(0);
        
        for (int i = 0; i < n; i++) {
            int stone = stones[i];
            for (int jump : dp.get(stone)) {
                for (int step = jump - 1; step <= jump + 1; step++) {
                    if (step > 0 && dp.containsKey(stone + step)) {
                        dp.get(stone + step).add(step);
                    }
                }
            }
        }
        
        return dp.get(stones[n - 1]).size() != 0;
    }
}