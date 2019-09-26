// // O(n^2)time O(1)space
// class Solution {
//     public int maxProfit(int[] prices) {
//         if (prices == null || prices.length == 0) {
//             return 0;
//         }
        
//         int n = prices.length;
//         int max = 0;
        
//         for (int i = 0; i < n; i++) {
//             max = Math.max(max, halfProfit(prices, 0, i) + halfProfit(prices, i, n - 1));
//         }
        
//         return max;
//     }
    
//     private int halfProfit(int[] prices, int left, int right) {
//         if (left >= right) {
//             return 0;
//         }
        
//         int max = 0;
//         int min = prices[left];
//         for (int i = left; i <= right; i++) {
//             max = Math.max(max, prices[i] - min);
//             if (prices[i] < min) {
//                 min = prices[i];
//             }
//         }
        
//         return max;
//     }
// }


// dp O(n)time O(1)space
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        int n = prices.length;
        int max = 0;
        
        int firstBuy = -Integer.MIN_VALUE;  // negative because of buying
        int firstSell = 0;  // we have 0 money at first
        int secondBuy = -Integer.MIN_VALUE; // negative because of buying
        int secondSell = 0;  // we have 0 money at first
        
        for (int price : prices) {
            firstBuy = Math.max(firstBuy, -price);  // find lowest price to buy in
            firstSell = Math.max(firstSell, price + firstBuy);  // the profit of the first buying
            secondBuy = Math.max(secondBuy, firstSell - price);  // find the lowest price to buy the second time
            secondSell = Math.max(secondSell, price + secondBuy);  // the profit of the second buying 
        }
        
        return secondSell;
    }
}