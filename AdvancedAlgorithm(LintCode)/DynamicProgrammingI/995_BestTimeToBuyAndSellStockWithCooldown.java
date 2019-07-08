// // DP O(n) space
// public class Solution {
//     /**
//      * @param prices: a list of integers
//      * @return: return a integer
//      */
//     public int maxProfit(int[] prices) {
//         if (prices == null || prices.length == 0) {
//             return 0;
//         }
        
//         int n = prices.length;
        
//         int[] hold = new int[n + 1]; // maximum profit when some stock is held
//         hold[1] = -prices[0];  
//         int[] sell = new int[n + 1];  // maximum profit when there is no stock in hand
//         sell[1] = 0;
        
//         for (int i = 2; i <= n; i++) {
//             hold[i] = Math.max(hold[i - 1], sell[i - 2] - prices[i - 1]);
//             sell[i] = Math.max(sell[i - 1], hold[i - 1] + prices[i - 1]);
//         }
        
//         return sell[n];
//     }
// }


// DP O(1) space
public class Solution {
    /**
     * @param prices: a list of integers
     * @return: return a integer
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        int n = prices.length;
        
        int hold = -prices[0];  // maximum profit when some stock is held
        int sell = 0;  // maximum profit when there is no stock in hand
        int prevSell = 0;
        
        for (int i = 2; i <= n; i++) {
            hold = Math.max(hold, prevSell - prices[i - 1]);
            prevSell = sell;
            sell = Math.max(sell, hold + prices[i - 1]);
        }
        
        return sell;
    }
}