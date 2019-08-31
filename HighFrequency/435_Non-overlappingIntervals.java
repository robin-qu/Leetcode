// // dp O(n^2)time O(n)space  initial version
// class Solution {
//     public int eraseOverlapIntervals(int[][] intervals) {
//         if (intervals == null || intervals.length == 0 ||
//             intervals[0] == null || intervals[0].length == 0) {
//             return 0;
//         }
        
//         Arrays.sort(intervals, new Comparator<int[]>() {
//             @Override
//             public int compare(int[] a, int[] b) {
//                 return a[0] - b[0];
//             }
//         });
        
//         int n = intervals.length;
//         int[] dp = new int[n];  // dp[i] represents the minimum number of intervals to remove at i-th interval
        
//         for (int i = 1; i < n; i++) {  
//             // keep the current interval, find the last nonoverlapping interval
//             int idx = i - 1;
//             int count = 0;
//             while (idx >= 0 && intervals[idx][1] > intervals[i][0]) {
//                 idx--;
//                 count++;
//             }            
//             int min = count + (idx < 0 ? 0 : dp[idx]);
                
//             for (int j = i - 1; j >= 0; j--) {
//                 if (intervals[j][1] > intervals[i][0]) {  // overlap
//                     min = Math.min(min, dp[j] + 1 + (i - j - 1));  // remove current interv
//                 } else {
//                     min = Math.min(min, dp[j] + (i - j - 1));
//                 }
//             }
            
//             dp[i] = min;
//         }
        
//         return dp[n - 1];
//     }
// }


// // dp O(n^2)time O(n)space
// class Solution {
//     public int eraseOverlapIntervals(int[][] intervals) {
//         if (intervals == null || intervals.length == 0 ||
//             intervals[0] == null || intervals[0].length == 0) {
//             return 0;
//         }
        
//         Arrays.sort(intervals, new Comparator<int[]>() {
//             @Override
//             public int compare(int[] a, int[] b) {
//                 return a[0] - b[0];
//             }
//         });
        
//         int n = intervals.length;
//         int[] dp = new int[n];  // dp[i] represents the maximum number of intervals that can be included at i-th interval
//         dp[0] = 1;
        
//         for (int i = 1; i < n; i++) {
//             int max = 1;
            
//             for (int j = i - 1; j >= 0; j--) {
//                 if (intervals[j][1] <= intervals[i][0]) {  // non-overlap
//                     max = Math.max(max, dp[j] + 1);
//                 }
//             }
            
//             dp[i] = max;
//         }
        
//         return n - dp[n - 1];
//     }
// }


// // greedy O(nlogn)time O(1)space  (sort by start)
// // always delete the interval with bigger endtime if there is a overlapping
// class Solution {
//     public int eraseOverlapIntervals(int[][] intervals) {
//         if (intervals == null || intervals.length == 0 ||
//             intervals[0] == null || intervals[0].length == 0) {
//             return 0;
//         }
        
//         Arrays.sort(intervals, new Comparator<int[]>() {
//             @Override
//             public int compare(int[] a, int[] b) {
//                 return a[0] - b[0];
//             }
//         });
        
//         int n = intervals.length;
//         int[] prev = intervals[0];
//         int res = 0;
//         for (int i = 1; i < n; i++) {
//             int[] curr = intervals[i];
            
//             if (prev[1] <= curr[0]) {               //  [  ]
//                 prev = curr;                        //        [   ]
//             } else if (prev[1] > curr[0]) {         //  [        ]
//                 if (prev[1] >= curr[1]) {           //     [   ]
//                     res++;
//                     prev = curr;
//                 } else {                            //  [   ]
//                     res++;                          //    [    ]
//                 }
//             }
//         }
        
//         return res;
//     }
// }


// greedy O(nlogn)time O(1)space  (sort by end)
// always delete the interval with bigger endtime if there is a overlapping
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0 ||
            intervals[0] == null || intervals[0].length == 0) {
            return 0;
        }
        
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        
        int n = intervals.length;
        int count = 1;
        int prevEnd = intervals[0][1];
        for (int i = 1; i < n; i++) {
            if (intervals[i][0] >= prevEnd) {
                count++;
                prevEnd = intervals[i][1];
            }
        }
        
        return n - count;
    }
}