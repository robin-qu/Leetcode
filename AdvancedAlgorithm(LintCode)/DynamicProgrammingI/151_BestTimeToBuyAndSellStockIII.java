// // Three passes traversals
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
        
//         int[] first = new int[n];
//         int min = prices[0];
//         int max1 = 0;
//         for (int i = 0; i < n; i++) {
//             min = Math.min(min, prices[i]);
//             max1 = Math.max(max1, prices[i] - min);
//             first[i] = max1;
//         }
        
//         int[] second = new int[n];
//         int max = prices[n - 1];
//         int max2 = 0;
//         for (int i = n - 1; i >= 0; i--) {
//             max = Math.max(max, prices[i]);
//             max2 = Math.max(max2, max - prices[i]);
//             second[i] = max2;
//         }
        
//         int res = 0;
//         for (int i = 0; i < n; i++) {
//             res = Math.max(res, first[i] + second[i]);
//         }
        
//         return res;
//     }
// }


// // DP O(n) space
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
        
//         int[] firstBuy = new int[n];
//         int[] firstSell = new int[n];
//         int[] secondBuy = new int[n];
//         int[] secondSell = new int[n];
//         firstBuy[0] = -prices[0];
//         firstSell[0] = 0;
//         secondBuy[0] = -prices[0];
//         secondSell[0] = 0;
        
//         for (int i = 1; i < n; i++) {
//             firstBuy[i] = Math.max(firstBuy[i - 1], -prices[i]); // negative because of bought
//             firstSell[i] = Math.max(firstSell[i - 1], firstBuy[i] + prices[i]);  // compare with the last sale
//             secondBuy[i] = Math.max(secondBuy[i - 1], firstSell[i] - prices[i]);  // second round
//             secondSell[i] = Math.max(secondSell[i - 1], secondBuy[i] + prices[i]); // second round
//         }
        
//         return secondSell[n - 1];
//     }
// }

// DP O(1) space
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
        
        int firstBuy = -prices[0];
        int firstSell = 0;
        int secondBuy = -prices[0];
        int secondSell = 0;
        
        for (int i = 1; i < n; i++) {
            firstBuy = Math.max(firstBuy, -prices[i]); // negative because of bought
            firstSell = Math.max(firstSell, firstBuy + prices[i]);  // compare with the last sale
            secondBuy = Math.max(secondBuy, firstSell - prices[i]);  // second round
            secondSell = Math.max(secondSell, secondBuy + prices[i]); // second round
        }
        
        return secondSell;
    }
}