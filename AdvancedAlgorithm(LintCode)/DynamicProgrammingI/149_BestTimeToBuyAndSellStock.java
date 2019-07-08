// // O(n) brute force
// public class Solution {
//     /**
//      * @param prices: Given an integer array
//      * @return: Maximum profit
//      */
//     public int maxProfit(int[] prices) {
//         if (prices == null || prices.length == 0) {
//             return 0;
//         }
        
//         int n = prices.length;
        
//         int max = 0;
//         for (int i = 0; i < n; i++) {
//             for (int j = i + 1; j < n; j++) {
//                 max = Math.max(max, prices[j] - prices[i]);
//             }
//         }
        
//         return max;
//     }
// }


// O(n) brute force
public class Solution {
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        int n = prices.length;
        
        int maxProfit = 0;
        int buy = prices[0];
        for (int i = 1; i < n; i++) {
            buy = Math.min(buy, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - buy);
        }
        
        return maxProfit;
    }
}